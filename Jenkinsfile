pipeline {
    agent any

    environment {
        APP_PATH = '/home/ec2-user/demo-0.0.1-SNAPSHOT.jar'
        REPO_URL = 'https://github.com/rushikpatel08/Demo.git'
    }

    stages {

        stage('Check Environment') {
            steps {
                sh '''
                hostname
                java -version
                mvn -v
                free -m
                df -h
                '''
            }
        }

        stage('Checkout') {
            steps {
                git branch: 'master', url: "${REPO_URL}"
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests -e'
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                cp target/*.jar ${APP_PATH}
                sudo systemctl restart springboot || true
                '''
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}