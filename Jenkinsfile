pipeline {
  agent any

  triggers {
    cron '@midnight'
  }

  options {
    buildDiscarder(logRotator(numToKeepStr: '60', artifactNumToKeepStr: '2'))
  }

  parameters {
    string(
       name: 'engineListUrl',
       description: 'Engine to use for build',
       defaultValue: 'https://jenkins.ivyteam.io/job/core_product/job/master/lastSuccessfulBuild/'
    )
  }
  stages {
    stage('build') {
      steps {
        script {   
          def random = (new Random()).nextInt(10000000)
          def networkName = "build-" + random
          def seleniumName = "selenium-" + random
          def ivyName = "ivy-" + random
          sh "docker network create ${networkName}"
          try {          
            docker.image("selenium/standalone-firefox:3").withRun("-e START_XVFB=false --shm-size=2g --name ${seleniumName} --network ${networkName}") {     
              docker.build('maven-selenium').inside("--name ${ivyName} --network ${networkName}") {
                def workspace = pwd()
                def phase = env.BRANCH_NAME == 'master' ? 'deploy' : 'verify'
                maven cmd: "clean ${phase} -Dmaven.test.failure.ignore=true  " + 
                          "-Dengine.directory=${workspace}/html-dialog-demos/html-dialog-demos/target/ivyEngine " +
                          "-Divy.engine.version='[9.2.0,]' " +
                          "-Divy.engine.list.url=${params.engineListUrl} " + 
                          "-DaltDeploymentRepository=nexus.axonivy.com::https://nexus.axonivy.com/repository/maven-snapshots/ " +
                          "-Dselenide.remote=http://${seleniumName}:4444/wd/hub"

                archiveArtifacts '**/target/*.iar,**/target/*.zip'
                archiveArtifacts artifacts: '**/target/selenide/reports/**/*', allowEmptyArchive: true

                recordIssues tools: [eclipse()], unstableTotalAll: 1
                recordIssues tools: [mavenConsole()], unstableTotalAll: 1, filters: [
                  excludeMessage('.*An illegal reflective access operation has occurred.*'), // in rule engine test
                ]

                junit testDataPublishers: [[$class: 'StabilityTestDataPublisher']], testResults: '**/target/*-reports/**/*.xml'          
              }
            }
          }
          finally {
            sh "docker network rm ${networkName}"
          }
        }
      }
    }
    stage('check editorconfig') {
      steps {
        script {          
            docker.image('mstruebing/editorconfig-checker').inside {
              sh 'ec -no-color'
            }          
        }
      }
    }
  }
}
