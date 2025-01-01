package com.app.materiel.Controllers;

import com.app.materiel.Dto.TypeSummaryDto;
import com.app.materiel.Entity.Mouvement;
import com.app.materiel.Entity.Stock;
import com.app.materiel.Entity.Type;
import com.app.materiel.Repository.MouvementRepository;
import com.app.materiel.Repository.StockRepository;
import com.app.materiel.Repository.TypeRepository;
import com.app.materiel.Service.MouvementService;
import com.app.materiel.Service.StatusService;
import com.app.materiel.Service.StockService;
import com.app.materiel.Service.TypeService;
import com.app.materiel.Dto.StockDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private MouvementService mouvementService;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private MouvementRepository mouvementRepository;
    @Autowired
    private TypeRepository typeRepository;

    @GetMapping("/article/new")
    public String showAddStockForm(Model model) {

        model.addAttribute("stock", new StockDto());
        model.addAttribute("types", typeService.findtypes());
        model.addAttribute("statuses", statusService.findstatus());
        return "article-new";
    }

    @PostMapping("/addStock")
    public String addStock(@ModelAttribute("stock") StockDto stockDto, RedirectAttributes redirectAttributes) {

        stockService.saveStock(stockDto);


        redirectAttributes.addFlashAttribute("successMessage", "Article ajouté au stock avec succès !");

        return "redirect:/article/new";
    }


    @GetMapping("/article/liste")
    public String listStocks(
            @RequestParam(value = "typeId", required = false) Long typeId,
            @RequestParam(value = "search", required = false) String searchTerm,
            @RequestParam(value = "page", defaultValue = "0") int page,
            Model model) {


        Page<Stock> stockPage;
        if (typeId == null) {

            stockPage = stockService.findAllStocks(searchTerm, page);
        } else {
            stockPage = stockService.findStocksByType(typeId, searchTerm, page);
        }

        model.addAttribute("stockPage", stockPage);
        model.addAttribute("types", typeService.findtypes());
        model.addAttribute("typeId", typeId);
        model.addAttribute("searchTerm", searchTerm);

        return "article-list";
    }



    @GetMapping("/etat-stock-global")
    public String getGlobalStockEtat(Model model) {

        List<TypeSummaryDto> typeSummary = stockService.getTypeSummary();
        model.addAttribute("typeSummary", typeSummary);
        return "global-stock-etat";
    }

    @GetMapping("/stocks/etat")
    public String getStockItemsByTypeAndStatus(
            @RequestParam("type") String type,
            @RequestParam("status") String status,
            Model model) {


        List<Stock> stockItems = stockService.findStocksByTypeAndStatus(type, status);

        model.addAttribute("stockItems", stockItems);
        model.addAttribute("type", type);
        model.addAttribute("status", status);

        return "stock-items-list";
    }



    @GetMapping("/article/{id}/mouvements")
    public String listMouvements(@PathVariable("id") Long stockId,
                                 @RequestParam(value = "page", defaultValue = "0") int page,
                                 @RequestParam(value = "size", defaultValue = "10") int size,
                                 Model model) {


        Stock stock = stockService.findStockById(stockId);
        Page<Mouvement> mouvementPage = mouvementService.findMovementsByStockOrderByDateDesc(stock, page, size);

        model.addAttribute("mouvementPage", mouvementPage);
        model.addAttribute("stock", stock);

        return "mouvement-history";
    }

    @GetMapping("/article/export")
    public ResponseEntity<byte[]> exportToExcel() throws IOException {



        List<Stock> stockList = stockService.findAllStocks(); // Fetch all stocks


        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Stock Data");


        Font boldFont = workbook.createFont();
        boldFont.setBold(true);

        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setFont(boldFont);
        titleStyle.setAlignment(HorizontalAlignment.CENTER); // Center the title horizontally


        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("État Global Du Stock");
        titleCell.setCellStyle(titleStyle);


        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4)); // Merged across first 5 columns


        Row headerRow = sheet.createRow(1);
        String[] headers = {"Designation", "Type", "N°Serie", "Date", "Status"};

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(titleStyle);
        }


        int rowNum = 2;
        for (Stock stock : stockList) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(stock.getDesignation());
            row.createCell(1).setCellValue(stock.getType().getLibelle());
            row.createCell(2).setCellValue(stock.getNserie());
            row.createCell(3).setCellValue(stock.getDatee().toString());
            row.createCell(4).setCellValue(stock.getStatus().getLibelle());
        }


        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();


        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = today.format(formatter);


        String fileName = "etat_stock_" + formattedDate + ".xlsx";


        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(outputStream.toByteArray());
    }

    @GetMapping("/stock/tree")
    public String showTree(Model model) {


        return "stock-tree";
    }


    @GetMapping("/api/stock/tree")
    public ResponseEntity<List<Map<String, Object>>> getStockTree() {
        List<Type> types = typeRepository.findAll();
        List<Map<String, Object>> treeData = new ArrayList<>();

        Map<String, Object> stockNode = new HashMap<>();
        stockNode.put("name", "Stock");
        List<Map<String, Object>> children = new ArrayList<>();

        for (Type type : types) {
            Map<String, Object> typeNode = new HashMap<>();
            typeNode.put("name", type.getLibelle());

            List<Stock> stocks = stockRepository.findByType(type);
            List<Map<String, Object>> stockItems = new ArrayList<>();

            for (Stock stock : stocks) {
                Map<String, Object> stockItem = new HashMap<>();
                stockItem.put("name", stock.getDesignation() + " (N°Serie: " + stock.getNserie() + ")");
                stockItems.add(stockItem);
            }

            typeNode.put("children", stockItems);
            children.add(typeNode);
        }

        stockNode.put("children", children);
        treeData.add(stockNode);

        return ResponseEntity.ok(treeData);
    }

    @PostMapping("/stock/update")
    public String updateStock(@RequestParam Long id, @RequestParam String designation, @RequestParam String nserie, @RequestParam String observation, @RequestParam("typeId") Long typeId, RedirectAttributes redirectAttributes) {

        Stock stock = stockRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid stock ID"));
        stock.setDesignation(designation);
        stock.setNserie(nserie);
        stock.setObservation(observation);
        Type selectedType = typeRepository.findById(typeId).orElseThrow(() -> new IllegalArgumentException("Invalid type ID"));
        stock.setType(selectedType);

        stockRepository.save(stock);
        redirectAttributes.addFlashAttribute("successMessage", "Article modifier avec succès !");
        return "redirect:/article/liste";
    }

    @PostMapping("/stock/delete")
    public String deleteStock(@RequestParam Long id, RedirectAttributes redirectAttributes) {

        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid stock ID"));

        mouvementRepository.deleteByStockId(stock.getId());

        stockRepository.delete(stock);
        redirectAttributes.addFlashAttribute("successMessage", "Article supprimé avec succés!");
        return "redirect:/article/liste";
    }

    @GetMapping("/checkNserie")
    @ResponseBody
    public boolean checkNserieExists(@RequestParam("nserie") String nserie) {
        return stockService.existsByNserie(nserie);
    }







}

