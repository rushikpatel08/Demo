pipeline {
    agent any

    environment {
        EC2_USER = 'ec2-user'
        EC2_HOST = 'ec2-100.31.62.50.compute-1.amazonaws.com'
        APP_NAME = 'demo'
        JAR_NAME = 'demo-0.0.1-SNAPSHOT.jar'
        REMOTE_PATH = '/home/ec2-user/app'
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
                sh 'chmod +x mvnw'
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Test (Optional)') {
            steps {
                sh './mvnw test || true'
            }
        }

        stage('Prepare Artifact') {
            steps {
                sh """
                    mkdir -p deploy
                    cp target/${JAR_NAME} deploy/
                """
            }
        }

        stage('Deploy to EC2') {
            steps {
                sshagent(['ec2-key-pair']) {
                    sh """
                        ssh -o StrictHostKeyChecking=no ${EC2_USER}@${EC2_HOST} '
                            mkdir -p ${REMOTE_PATH}
                        '

                        scp -o StrictHostKeyChecking=no deploy/${JAR_NAME} \
                        ${EC2_USER}@${EC2_HOST}:${REMOTE_PATH}/${JAR_NAME}

                        ssh -o StrictHostKeyChecking=no ${EC2_USER}@${EC2_HOST} '
                            pkill -f ${JAR_NAME} || true
                            nohup java -jar ${REMOTE_PATH}/${JAR_NAME} > app.log 2>&1 &
                        '
                    """
                }
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline completed successfully!'
        }

        failure {
            echo '❌ Pipeline failed. Check logs.'
        }
    }
}