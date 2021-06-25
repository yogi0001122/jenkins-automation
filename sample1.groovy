pipeline {
    agent { label 'master' }
    parameters {
        string(name: 'environment_name', description: 'Environment to deploy into')
    }
    stages {
        stage('execute') { // for display purposes
            steps {
                sh "echo 'Hello this sample job'"
            }
        }
         stage('test') { // for display purposes
            steps {
                sh "echo 'Hello this sample job'"
            }
        }
    }
}
