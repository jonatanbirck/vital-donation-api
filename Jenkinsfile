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
                    sh 'ssh -o StrictHostKeyChecking=no -l univates 177.44.248.85 \'cd projects/vital-donation-api && ./deploy.sh\''
                }
            }
        }
    }
}