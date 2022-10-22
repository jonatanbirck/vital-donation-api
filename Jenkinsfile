pipeline {
    agent any

    stages {
        stage("Build") {
            agent {
                docker {
                    image "gradle:7.5-jdk17"
                }
            }
            steps {                
                sh "gradle build --no-daemon"
            }
        }
        stage("Build Release") {
            steps {
                sshagent(credentials: ['vm-univates-key']) {
                    sh '''
                        [ -d ~/.ssh] || mkdir ~/.ssh && chmod 0700 ~/.ssh
                        ssh-keyscan -t rsa, dsa 177.44.248.85 >> ~/.ssh/know_hosts
                        ssh univates@177.44.248.85
                    '''
                }
            }
        }
    }
}