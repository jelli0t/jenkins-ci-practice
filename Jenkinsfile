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
    CONTAINER_NAME = 'jenkins-ci-practice'
    DOCKER_IMAGE = 'yelless/jenkins-ci-practice'
    IMAGE_TAG = 'v0.1.0'
    DOCKER_REGISTRY_CREDENTIALS = 'dokcerhub-yelless-creds'
    APP_PORT = 18081
    DOCKER_APP_NET = 'reverse-proxy'
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
        script {
            docker.build("${DOCKER_IMAGE}:${IMAGE_TAG}")
        }
      }
    }

    stage('Push Image -> Docker Hub') {
        // This step requires Docker Hub credentials to be configured in Jenkins
        steps {
            script {
                docker.withRegistry('https://registry.hub.docker.com', DOCKER_REGISTRY_CREDENTIALS) {
                    docker.image("${DOCKER_IMAGE}:${IMAGE_TAG}").push()
                }
            }
        }
    }

    stage('Run Docker Container') {
        steps {
            script {
                // Stop and remove any existing container with the same name
                sh "docker rm -f ${CONTAINER_NAME} 2>/dev/null || true"

                // Run the newly built Docker image, mapping port 8080
                sh "docker run --rm --name ${CONTAINER_NAME} -p ${APP_PORT}:8080 --network ${DOCKER_APP_NET} ${DOCKER_IMAGE}:${IMAGE_TAG}"
            }
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

