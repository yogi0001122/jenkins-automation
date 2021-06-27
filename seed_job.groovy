def baseFolderName = 'test'
def defaultRepo = 'jenkins-automation'
def defaultGitUrl = 'github.com'
def defaultBranch = 'master'
def defaultOrg = 'yogi0001122'
def defaultNumToKeep = '5'
def defaultNumArtifacts = '1'
def defaultGitCredentials = 'git-user'
def defaultTrigger = null
def defaultJobDescription = 'Generic job description'

def jobs = [
    'sameple1': [
      description: 'This is sample1 job',
      triggers: "cron('@hourly')",
      groovyFile: 'sample1.groovy'
    ],
    'sample2': [
      description: 'This is sampl2Job',
      groovyFile: 'sample2.groovy'
    ]
  ]


// Create the base folder for all the jobs
//folder(baseFolderName) {
  //description("Dev, jobs")
//}

// These jobs are generate by the array at the top

jobs.each { job, jobConfig ->
  String jobDesc = jobConfig.description ?: defaultJobDescription
  String jobGitUrl = jobConfig.gitUrl ?: defaultGitUrl
  String jobGithubOrg = jobConfig.gitOrg ?: defaultOrg
  String jobGithubRepo = jobConfig.gitRepo ?: defaultRepo
  String jobGithubBranch = jobConfig.branch ?: defaultBranch
  String jobBaseFolder = jobConfig.folderName ?: baseFolderName
  String jobTriggers = jobConfig.triggers ?: defaultTrigger
  int jobNumToKeep = jobConfig.numberToKeep ?: defaultNumToKeep
  int jobNumArtifacts = jobConfig.numberOfArtifacts ?: defaultNumArtifacts
  String jobGroovy = jobConfig.groovyFile ?: job + ".groovy"

    pipelineJob('/' +  env.BRANCH_NAME + '/' + job) {
    description("These job are for Dev")

    definition {
      cpsScm {
        scriptPath jobGroovy
        scm {
          git {
            remote {
              url 'https://' + defaultGitUrl + '/' + jobGithubOrg + '/' + jobGithubRepo
              credentials defaultGitCredentials
            }
            branch jobGithubBranch
          }
        }
      }
    }
    logRotator {
      numToKeep(jobNumToKeep)
      artifactNumToKeep(jobNumArtifacts)
    }
    if (jobTriggers?.trim()) {
      properties{
        pipelineTriggers {
          triggers {[jobTriggers]}
        }
      }
    }
  }
}
