pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                // Récupérer le code depuis le dépôt Git
                git 'https://github.com/aissatoulo427/demo-chef.git'
            }
        }

        stage('Build') {
            steps {
                // Construire le projet avec Maven
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                // Exécuter les tests unitaires
                sh 'mvn test'
            }
        }

        stage('Analyse SonarQube') {
            steps {
                script {
                    // Lancer l'analyse SonarQube
                    withSonarQubeEnv('SonarQube') {
                        sh 'mvn sonar:sonar'
                    }
                }
            }
        }

        stage('Construire et pousser Docker') {
            steps {
                script {
                    // Construire l'image Docker
                    sh 'docker build -t aichalo/demo-chef:${BUILD_NUMBER} .'

                    // Pousser l'image vers Docker Hub
                    sh 'docker push aichalo/demo-chef:${BUILD_NUMBER}'
                }
            }
        }

        stage('Deploy to Nexus') {
            steps {
                script {
                    // Déployer l'artefact sur Nexus
                    sh """
                    mvn deploy:deploy-file \
                    -Dfile=target/demo-chef-1.0.0.jar \
                    -DrepositoryId=nexus-repository \
                    -Durl=http://localhost:8081/repository/maven-releases/
                    """
                }
            }
        }
        tools {
            maven 'Maven 3.9'
        }
    }

    post {
        success {
            echo 'Le pipeline a réussi avec succès !'
        }
        failure {
            echo 'Le pipeline a échoué.'
        }
    }
}
