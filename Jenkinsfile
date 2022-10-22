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
                sh "echo 'Build Release'"
            }
        }
    }
}