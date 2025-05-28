pipeline {
    agent any

    stages {
        stage('Deploy') {
            steps {
                withCredentials([
                    sshUserPrivateKey(credentialsId: 'my-ssh-key-id', keyFileVariable: 'SSH_KEY', usernameVariable: 'REMOTE_USER'),
                    string(credentialsId: 'REMOTE_HOST', variable: 'REMOTE_HOST'),
                    string(credentialsId: 'REMOTE_DIR', variable: 'REMOTE_DIR'),
                    string(credentialsId: 'REPO_URL', variable: 'REPO_URL')
                ]) {
                    sh """
                    ssh -i ${SSH_KEY} -o StrictHostKeyChecking=no ${REMOTE_USER}@${REMOTE_HOST} '
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
