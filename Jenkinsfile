pipeline {
    agent any

    environment {
        REPO_URL = 'https://github.com/rushikpatel08/Demo.git'
        REMOTE_HOST = '100.31.62.50'
        REMOTE_USER = 'ec2-user'
        APP_NAME = 'demo-0.0.1-SNAPSHOT.jar'
        REMOTE_PATH = '/home/ec2-user/demo-0.0.1-SNAPSHOT.jar'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'master', url: "${REPO_URL}"
            }
        }

        stage('Build Spring Boot App') {
            steps {
                sh '''
                mvn clean package -DskipTests
                '''
            }
        }

        stage('Verify Build') {
            steps {
                sh '''
                ls -lh target/
                '''
            }
        }

        stage('Deploy to EC2') {
            steps {
                sh '''
                echo "Stopping existing application on remote server..."
                ssh ${REMOTE_USER}@${REMOTE_HOST} "pkill -f ${APP_NAME} || true"

                echo "Copying JAR to remote server..."
                scp target/*.jar ${REMOTE_USER}@${REMOTE_HOST}:${REMOTE_PATH}

                echo "Starting application on remote server..."
                ssh ${REMOTE_USER}@${REMOTE_HOST} "nohup java -jar ${REMOTE_PATH} > app.log 2>&1 &"
                '''
            }
        }
    }

    post {
        success {
            echo "✅ Deployment SUCCESS to EC2: ${REMOTE_HOST}"
        }

        failure {
            echo "❌ Deployment FAILED"
        }

        always {
            cleanWs()
        }
    }
}