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
