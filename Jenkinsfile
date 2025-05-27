pipeline {
    agent any

    environment {
        REMOTE_USER = 'your-remote-username'
        REMOTE_HOST = 'your.server.ip.or.hostname'
        REMOTE_DIR  = '/path/to/deploy/dir'
        REPO_URL    = 'git@github.com:your-org/your-repo.git'
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building...'
            }
        }

        stage('Test') {
            steps {
                echo 'Testing...'
            }
        }

        stage('Deploy') {
            steps {
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
