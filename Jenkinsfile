pipeline {
    agent any


    stages {
       stage('Build') {
          steps {
             sh 'gradle clean compileJava'
             sh './gradlew clean build'
          }
       }
       stage('Deploy'){
                  steps{
                      sh 'cf push shipment-service -p ./build/libs/com.solstice.orderservice-0.0.1-SNAPSHOT.jar --random-route --no-start'
                  }
       }
    }
}
