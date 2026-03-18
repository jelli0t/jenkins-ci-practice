def app

pipeline {
  agent any

  options {
    timestamps()
  }

  environment {
    // Keeps Gradle caches inside the workspace (helpful on ephemeral agents too)
    GRADLE_USER_HOME = "${WORKSPACE}/.gradle"
    JAVA_HOME = "/opt/java/openjdk"
    PATH = "${JAVA_HOME}/bin:${env.PATH}"
    DOCKER_IMAGE = 'yelless/jenkins-ci-practice'
    DOCKER_TAG = 'v0.1.0'
    DOCKER_REGISTRY_CREDENTIALS = 'dokcerhub-yelless-creds'
  }

  stages {
    stage('Checkout') {
      steps {
//         checkout scm
        git url: 'https://github.com/jelli0t/jenkins-ci-practice.git', branch: 'develop'
      }
    }

    stage('Setup') {
      steps {
        sh 'java -version'
        sh 'echo $JAVA_HOME'
        sh 'chmod +x ./gradlew'
      }
    }

    stage('Test') {
      steps {
        sh './gradlew --no-daemon clean test'
      }
    }

    stage('Package') {
      steps {
        sh './gradlew --no-daemon bootJar'
      }
    }

    stage('Build Docker Image') {
      steps {
//         sh 'docker build -t yelless/jenkins-ci-practice .'
        app = docker.build("${DOCKER_IMAGE}:${DOCKER_TAG}")
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

