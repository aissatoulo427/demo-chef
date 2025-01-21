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
