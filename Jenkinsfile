pipeline {
    agent any

    environment {
        REPO_URL = 'https://github.com/rushikpatel08/Demo.git'
        EC2_USER = 'ec2-user'
        EC2_HOST = '98.87.165.26'
        APP_PATH = '/home/ec2-user/app/app.jar'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'master', url: "${REPO_URL}"
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Deploy to EC2') {
            steps {
                sh '''
                scp target/*.jar ${EC2_USER}@${EC2_HOST}:${APP_PATH}
                ssh ${EC2_USER}@${EC2_HOST} "sudo systemctl restart springboot"
                '''
            }
        }
    }

    post {
        success {
            echo "Deployment Successful"
        }

        failure {
            echo "Deployment Failed"
        }
    }
}