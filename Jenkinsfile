node {
	stage('Compile') {
		checkout scm
		dir('dev'){
			sh './gradlew compileJava'
		}
	}

	stage('Test') {
		dir('dev'){
			sh './gradlew test'
		}
	}

	stage('Integration test') {
		dir('dev'){
			sh './gradlew integrationTests'
		}
	}

}

