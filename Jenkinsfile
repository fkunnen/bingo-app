stage('Compile') {
	checkout scm
	dir('dev'){
		sh './gradlew compile'
	}
}

stage('Test') {
	dir('dev'){
		sh './gradlew test'
	}
}

stage('Proceed?') {
    milestone label: 'Proceed?'
    input 'Do you want to proceed?'
}

stage('Finished!') {

}

