node {
	stage('Compile') {
		checkout scm
		dir('dev'){
			sh './gradlew compileJava'
		}
	}

	stage('Test') {
		dir('dev'){
			sh './gradlew clean test'
			step([$class: 'JUnitResultArchiver', testResults: '**/test-results/test/TEST-*.xml'])
		}
	}

	stage('Integration test') {
		dir('dev'){
			sh './gradlew clean integrationTests'
			step([$class: 'JUnitResultArchiver', testResults: '**/test-results/integrationTests/TEST-*.xml'])
		}
	}

	stage('Build docker image'){
		dir('dev'){
			sh './gradlew buildDockerImage'
		}
	}

	stage('Spin up application'){
		dir('dev'){
			sh './gradlew dockerComposeUp'
		}
	}

}

