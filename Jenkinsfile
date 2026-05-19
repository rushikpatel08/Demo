pipeline {
    agent any

    environment {
        EC2_USER = 'ec2-user'
        EC2_HOST = 'ec2-54.172.143.190.compute-1.amazonaws.com'
        APP_PATH = '/home/ec2-user/demo-0.0.1-SNAPSHOT.jar'
        REPO_URL = 'https://github.com/rushikpatel08/Demo.git'
    }

    stages {

        stage('Check Java & Maven') {
            steps {
                sh '''
                java -version
                mvn -v
                '''
            }
        }

        stage('Checkout Code') {
            steps {
                git branch: 'master', url: "${REPO_URL}"
            }
        }

        stage('Build Spring Boot App') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Verify Build') {
            steps {
                sh 'ls -lh target/'
            }
        }

        stage('Deploy to EC2') {
            steps {
                sh '''
                cp target/*.jar /home/ec2-user/demo-0.0.1-SNAPSHOT.jar
                sudo systemctl restart springboot
                '''
            }
        }
    }

    post {
        success {
            echo 'Build and deployment successful!'
        }

        failure {
            echo 'Pipeline failed!'
        }

        always {
            cleanWs()
        }
    }
}