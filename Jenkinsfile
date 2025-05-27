pipeline {
    agent any

        stage('Deploy') {
            steps {
                withCredentials([
                    string(credentialsId: 'remote-user', variable: 'REMOTE_USER'),
                    string(credentialsId: 'remote-host', variable: 'REMOTE_HOST'),
                    string(credentialsId: 'remote-dir', variable: 'REMOTE_DIR'),
                    string(credentialsId: 'repo-url', variable: 'REPO_URL')
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
