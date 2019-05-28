def containerId=""
pipeline {
    agent none
    stages {
        stage('Build IncidentManagement') {
            agent {
                    docker {
                        image 'maven:3-alpine'
                        args '-v /root/.m2:/root/.m2'
                    }
                  }
            steps {
                    sh 'mvn -X clean install -DskipTests'
                  }
            }

        stage('Staging IncidentManagement Image') {
            agent any
            steps{
                    script{
                        containerId = sh (
                        script :'docker ps -aqf "name=incident-management"',
                        returnStdout: true
                        ).trim()
                        if("${containerId}"!= ""){
                          sh 'docker stop incident-management'
                          sh 'docker rm incident-management'
                          sh 'docker rmi $(docker images --filter=reference=incident-management --format "{{.ID}}")'
                        }
                    }
                    sh 'docker build -t incident-management:1.0 .'
                }
              }
        stage('Containerising IncidentManagement') {
          agent any
           steps {
                   sh 'sh dockercompose.sh'
                 }
         }
    }
 }