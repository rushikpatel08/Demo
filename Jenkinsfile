pipeline {
    agent any

    environment {
        EC2_USER = 'ec2-user'
        EC2_HOST = 'ec2-3.81.214.248.compute-1.amazonaws.com'
        APP_PATH = '/home/ec2-user/demo-0.0.1-SANPSHOT.jar'
        REPO_URL = 'https://github.com/rushikpatel08/Demo.git'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'master', url: "${REPO_URL}"
            }
        }

        stage('Build Spring Boot App') {
            steps {
                 sh 'mvn clean package'
            }
        }

         stage('Test Spring Boot App') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Deploy to EC2') {
            steps {
             sh '''
                cp target/*.jar /home/ec2-user/demo-0.0.1-SANPSHOT.jar
                sudo systemctl restart springboot
                '''
            }
        }

    }
}
