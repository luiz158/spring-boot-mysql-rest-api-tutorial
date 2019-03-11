pipeline {
 agent any
 tools {
  maven 'maven'
  //jdk 'java1.8.0'
 }
 stages {
  stage('build') {
   steps {
    sh "mvn -B -DskipTests clean package"
   }
  }
  stage('UnitTest') {
   steps {
    sh "mvn test"
   }
   post {
    always {
     junit 'target/surefire-reports/*.xml'
    }
   }
  }
  stage('Deploy') {
   steps {
    sh "java -jar target/spring-boot-parent*.war"
   }
  }
 /* stage('publish') {
   steps {
    sh 'curl -X PUT -u admin:APB4oSbMxjG67dX7gZdt2oPHD4m -T target/my-app-1.0-SNAPSHOT.jar "http://52.42.121.49:8081/artifactory/libs-snapshot/spring-boot-parent.jar"'
   }
  }*/
 }
}
