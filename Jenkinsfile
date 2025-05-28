pipeline {
    agent any

    environment {
        REPO_URL = 'https://github.com/ibis-miguel/ibis-poc-server.git'
    }

    stages {
        stage('Deploy') {
            steps {
                withCredentials([
                    sshUserPrivateKey(credentialsId: 'my-ssh-key-id', keyFileVariable: 'SSH_KEY', usernameVariable: 'REMOTE_USER'),
                    string(credentialsId: 'REMOTE_HOST', variable: 'REMOTE_HOST'),
                    string(credentialsId: 'REMOTE_DIR', variable: 'REMOTE_DIR')
                ]) {
                    bat """
                    "C:\\\\Users\\\\Miguel.Lachman\\\\AppData\\\\Local\\\\Programs\\\\Git\\\\bin\\\\bash.exe" -c '
                    chmod 600 "${SSH_KEY}"
                    ssh -i "${SSH_KEY}" -o StrictHostKeyChecking=no ${REMOTE_USER}@${REMOTE_HOST} "
                        mkdir -p ${REMOTE_DIR} &&
                        cd ${REMOTE_DIR} &&
                        if [ ! -d .git ]; then
                            git clone ${REPO_URL} .;
                        else
                            git pull origin master;
                        fi &&
                        docker-compose up --build -d
                    "
                    '
                    """
                }
            }
        }
    }
}
