pipeline{
    agent any
    tools{
        maven 'maven'
    }
    stages{
        stage('Build JAR File'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/LeoVergaraS/proyecto1-tingeso']]])
                bat 'mvn clean install -DskipTests'
            }
        }
        stage('Test'){
            steps{
                bat 'mvn test'
            }
        }
        stage('Build Docker Image'){
            steps{
                bat 'docker build -t leovergaras/aplicacion1tingeso .'
            }
        }
        stage('Push docker image'){
            steps{
                withCredentials([string(credentialsId: 'docker-hub-password', variable: 'dckpass')]) {
                    bat "docker login -u leovergaras -p $dckpass"
                }
                bat 'docker push leovergaras/aplicacion1tingeso'
            }
        }
    }
    post{
        always{
            script{
                bat 'docker logout'
            }
        }
    }
}