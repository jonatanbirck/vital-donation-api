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
            when {
                branch "main"
            }
            steps {
                sh "echo 'Build Release'"
            }
        }
    }
}