pipeline {
    agent any

    stages {
        stage('Deploy') {
            steps {
                withCredentials([
                    REMOTE_USER = credentials('REMOTE_USER'),
                    REMOTE_HOST = credentials('REMOTE_HOST'),
                    REMOTE_DIR = credentials('REMOTE_DIR'),
                    REPO_URL = credentials('REPO_URL')
                ]) {
                    sshagent(['your-ssh-credentials-id']) {
                        sh """
                        ssh -o StrictHostKeyChecking=no ${REMOTE_USER}@${REMOTE_HOST} '
                            mkdir -p ${REMOTE_DIR} &&
                            cd ${REMOTE_DIR} &&
                            if [ ! -d ".git" ]; then
                                git clone ${REPO_URL} .;
                            else
                                git pull origin master;
                            fi &&
                            docker-compose up --build -d
                        '
                        """
                    }
                }
            }
        }
    }
}
