pipeline {
  agent { label 'master' }

  parameters {
    string(name: 'environment_name', description: 'Environment to deploy into')
  }

  environment {
    ENVIRONMENT_NAME = "${params.environment_name}"
  }

  stages {
    stage('Print environment name') {
      steps {
        echo "Will deploy to ${params.environment_name}"
      }
    }

    stage('Loading environment config') {
      steps {
        script {
          loadEnv = load('env.groovy');
          environment = loadEnv.load(params.environment_name)
        }
      }
    }
    stage('Checkout env repo') {
      when { expression { environment.env.github } }
      steps {
        checkout([
            $class: 'GitSCM', 
            branches: [[name: environment.env.github.branch]], 
            doGenerateSubmoduleConfigurations: false, 
            extensions: [
                [$class: 'CleanBeforeCheckout'], 
                [$class: 'RelativeTargetDirectory', relativeTargetDir: './env']
            ], 
            submoduleCfg: [], 
            userRemoteConfigs: [
                [credentialsId: environment.env.github.credentials_id, url: environment.env.github.url]
            ]
        ])
      }
    }

    stage('Run automated tests to check for each endpoint existence and connectivity') {
      steps {
        echo 'TODO'
      }
    }
}
}