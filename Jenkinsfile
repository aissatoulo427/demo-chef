pipeline {
    stages {
        stage('Checkout') {
            steps {
                // Récupère le code depuis le dépôt Git
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
    }

stage('Analyse SonarQube') {
    steps {
        script {
            // Lance l'analyse SonarQube
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

            // Pousser l'image vers Docker Hub (ou autre registre Docker)
            sh 'docker push aichalo/demo-chef:${BUILD_NUMBER}'
        }
    }
}
 stage('Deploy to Nexus') {
            steps {
                script {
                    sh """
                    mvn deploy:deploy-file \
                    -DgroupId=com.example \
                    -DartifactId=demo-chef \
                    -Dversion=1.0.0 \
                    -Dpackaging=jar \
                    -Dfile=target/demo-chef-1.0.0.jar \
                    -DrepositoryId=nexus-repository \
                    -Durl=http://localhost:8081/repository/maven-releases/
                    """
                }
            }
        }
    

    post {
        success {
            echo 'Le build a réussi !'
        }
        failure {
            echo 'Le build a échoué.'
        }
    }
}
