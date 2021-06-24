pipeline {
    agent { label 'master' }
    parameters {
        string(name: 'command', defaultValue: 'get nodes', description: 'kubectl command to run, will be kubectl $command, will print out.txt if it is created')
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
