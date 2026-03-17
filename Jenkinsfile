pipeline {
  agent any

  options {
    timestamps()
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
        sh 'java -version'
        sh 'echo $JAVA_HOME'
        sh 'chmod +x ./gradlew'
        sh './gradlew --no-daemon clean bootJar'
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

