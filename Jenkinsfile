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
            sh 'docker build -t monutilisateur/monapp:${BUILD_NUMBER} .'

            // Pousser l'image vers Docker Hub (ou autre registre Docker)
            sh 'docker push monutilisateur/monapp:${BUILD_NUMBER}'
        }
    }
}
stage('Déployer sur Nexus') {
    steps {
        script {
            // Déployer l'artefact sur Nexus
            sh 'mvn deploy:deploy-file -Dfile=target/monapp.jar -DrepositoryId=nexus-repository -Durl=http://localhost:8081/repository/maven-releases/'
        }
    }
}
stage('Déployer avec Chef') {
    steps {
        script {
            sh '''
                ssh user@server "chef-client --local-mode /chemin/vers/recette"
            '''
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
