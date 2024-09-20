package com.app.materiel.Controllers;

import com.app.materiel.Dto.TypeSummaryDto;
import com.app.materiel.Entity.Mouvement;
import com.app.materiel.Entity.Stock;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
            @RequestParam(value = "search", required = false) String searchTerm,
            @RequestParam(value = "page", defaultValue = "0") int page,
            Model model) {

        Page<Stock> stockPage = stockService.findAllStocks(searchTerm, page);

        model.addAttribute("stockPage", stockPage);
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

        // Fetch stock items by type and status
        List<Stock> stockItems = stockService.findStocksByTypeAndStatus(type, status);

        // Add stock items to the model
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
            row.createCell(3).setCellValue(stock.getDatee().toString()); // Adjust date format as needed
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

}

