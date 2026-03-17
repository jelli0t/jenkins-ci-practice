pipeline {
  agent any

  options {
    timestamps()
    ansiColor('xterm')
  }

  environment {
    // Keeps Gradle caches inside the workspace (helpful on ephemeral agents too)
    GRADLE_USER_HOME = "${WORKSPACE}/.gradle"
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build & Test') {
      steps {
        sh 'chmod +x ./gradlew'
        sh './gradlew --no-daemon clean test bootJar'
      }
    }
  }

  post {
    always {
      junit allowEmptyResults: true, testResults: '**/build/test-results/test/*.xml'
      archiveArtifacts allowEmptyArchive: true, artifacts: 'build/libs/*.jar'
    }
  }
}

