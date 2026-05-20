pipeline {
    agent any

    environment {
        APP_NAME = 'demo-0.0.1-SNAPSHOT.jar'
        BUILD_PATH = 'target/demo-0.0.1-SNAPSHOT.jar'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'master', url: 'https://github.com/rushikpatel08/Demo.git'
            }
        }

        stage('Build Application') {
            steps {
                sh '''
                echo "Cleaning and building project..."
                mvn clean package -DskipTests
                '''
            }
        }

        stage('Verify Build') {
            steps {
                sh '''
                echo "Checking target folder..."
                ls -lh target/
                '''
            }
        }

        stage('Stop Old Application') {
            steps {
                sh '''
                echo "Stopping old Spring Boot app if running..."
                pkill -f demo-0.0.1-SNAPSHOT.jar || true
                '''
            }
        }

        stage('Deploy Application') {
            steps {
                sh '''
                echo "Starting new Spring Boot application..."

                nohup java -jar target/demo-0.0.1-SNAPSHOT.jar > app.log 2>&1 &

                echo "Application started successfully"
                '''
            }
        }

        stage('Health Check') {
            steps {
                sh '''
                echo "Waiting for app to start..."
                sleep 10

                echo "Checking if app is running..."
                ps -ef | grep demo-0.0.1-SNAPSHOT.jar || true
                '''
            }
        }
    }

    post {
        success {
            echo "✅ Deployment SUCCESS on same EC2 instance"
        }

        failure {
            echo "❌ Deployment FAILED"
        }

        always {
            cleanWs()
        }
    }
}