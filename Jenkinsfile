pipeline{
    agent {
        label 'agent1'
    }
    stages{
        stage('Build'){
            steps{
                sh './gradlew build -x test'
            }
        }
    }
}