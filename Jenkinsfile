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
			step([$class: 'JUnitResultArchiver', testResults: '**/test-results/test/TEST-*.xml'])
		}
	}

	stage('Integration test') {
		dir('dev'){
			sh './gradlew integrationTests'
			step([$class: 'JUnitResultArchiver', testResults: '**/test-results/integrationTests/TEST-*.xml'])
		}
	}

}

