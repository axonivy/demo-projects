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
       defaultValue: 'https://product.ivyteam.io'
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
          try {
            sh "docker network create ${networkName}"
            docker.build("jsonserver", '-f build/json/Dockerfile .')
              .withRun("--network ${networkName} --network-alias jsonplaceholder") {
                docker.image("selenium/standalone-firefox:4.10").withRun("-e START_XVFB=false --shm-size=2g --name ${seleniumName} --network ${networkName}") {
                docker.build('maven-selenium').inside("--name ${ivyName} --network ${networkName}") {
                  def workspace = pwd()
                  def phase = isReleasingBranch() ? 'deploy' : 'verify'
                  maven cmd: "clean ${phase} -Dmaven.test.failure.ignore=true  " +
                            "-Dengine.directory=${workspace}/html-dialog-demos/html-dialog-demos/target/ivyEngine " +
                            "-Divy.engine.version='[10.0.0, 10.1.0)' " +
                            "-Divy.engine.list.url=${params.engineListUrl} " +
                            "-DaltDeploymentRepository=nexus.axonivy.com::https://nexus.axonivy.com/repository/maven-snapshots/ " +
                            "-Dselenide.remote=http://${seleniumName}:4444/wd/hub " +
                            "-Dtest.host.name=${ivyName}"

                  archiveArtifacts '**/target/*.iar,**/target/*.zip'
                  archiveArtifacts artifacts: '**/target/selenide/reports/**/*', allowEmptyArchive: true

                  recordIssues tools: [eclipse()], qualityGates: [[threshold: 1, type: 'TOTAL']]
                  recordIssues tools: [mavenConsole()], qualityGates: [[threshold: 1, type: 'TOTAL']], filters: [
                    excludeMessage('.*An illegal reflective access operation has occurred.*'), // in rule engine test
                  ]

                  junit testDataPublishers: [[$class: 'StabilityTestDataPublisher']], testResults: '**/target/*-reports/**/*.xml'
                }
              }
            }
          } finally {
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
