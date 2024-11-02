node {

  stage("Git") {
    git branch: 'master', credentialsId: '8c2db6e0-77ae-4361-97f9-75a61b84a168', url: 'http://193.0.0.244:3000/taouaf/app-materiel-dinfo'
  }

  stage("Backup") {
    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
      sh "sudo rm -f /applications/matdinfo/backup/*.jar"
      sh "sudo mv -f /applications/matdinfo/*.jar /applications/matdinfo/backup/backup.jar 2>/dev/null; true"
    }
  }
  
  stage("Stop previews jar") {
    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
      sh "sudo kill -9 `sudo lsof -t -i:9200`"
    }
  }
  
  stage("Start") {
    sh "sudo cp prod/*.jar /applications/matdinfo/app.jar"
    sh "sudo nohup java -jar '-Dspring.profiles.active=deploy' /applications/matdinfo/app.jar &"
  }

}