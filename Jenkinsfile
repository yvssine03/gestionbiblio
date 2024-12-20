pipeline {
    agent any
    tools {
        jdk 'jdk21'
        maven 'maven'
    }
    environment {
        REPO_URL = 'https://github.com/yvssine03/gestionbiblioteque.git'
        SONARQUBE_CREDENTIALS_ID = 'sonar'
    }
    stages {
        stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }
        stage('Checkout from GitHub') {
            steps {
                git branch: 'main',
                credentialsId: 'github',
                url: REPO_URL
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Quality Analysis') {
            steps {
                withSonarQubeEnv(installationName: 'sonar', credentialsId: SONARQUBE_CREDENTIALS_ID) {
                    bat 'mvn sonar:sonar'
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'Déploiement simulé réussi'
            }
        }
    }
    post {
        success {
            mail(
                to: 'yassineifghane1234@gmail.com',
                subject: 'Build Success',
                body: 'Le build a été complété avec succès.'
            )
        }
        failure {
            mail(
                to: 'yassineifghane1234@gmail.com',
                subject: 'Build Failed',
                body: 'Le build a échoué.'
            )
        }
    }
}
