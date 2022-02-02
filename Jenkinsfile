pipeline {
	agent any
    environment {
        CI = 'true'
    }
   stages { 
        stage('Build') {
            steps {
                sh '/opt/gradle/gradle-6.2.1/bin/gradle test'
            }
        }
   }
}
