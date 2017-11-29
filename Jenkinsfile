VERSION_NUMBER = ''

node {
	stage('Cleanup'){
		sh 'docker rm -f $(docker ps -aq)'
	}
	stage('Compile') {
		version = determineRegularBuildVersionNumber()
		checkout scm
		dir('dev'){
			sh './gradlew -PapplicationVersion=' + VERSION_NUMBER + ' compileJava'
		}
	}

	stage('Test') {
		dir('dev'){
			sh './gradlew -PapplicationVersion=' + VERSION_NUMBER + ' clean test'
			step([$class: 'JUnitResultArchiver', testResults: '**/test-results/test/TEST-*.xml'])
		}
	}

	stage('Integration test') {
		dir('dev'){
			sh './gradlew -PapplicationVersion=' + VERSION_NUMBER + ' clean integrationTests'
			step([$class: 'JUnitResultArchiver', testResults: '**/test-results/integrationTests/TEST-*.xml'])
		}
	}

	stage('Build docker image'){
		dir('dev'){
			sh './gradlew -PapplicationVersion=' + VERSION_NUMBER + ' buildDockerImage'
		}
	}

	stage('Spin up application'){
		dir('dev'){
			sh './gradlew -PapplicationVersion=' + VERSION_NUMBER + ' dockerComposeUp'
		}
	}

	stage('Smoke test'){
		dir('dev'){
			sh './gradlew -PapplicationVersion=' + VERSION_NUMBER + ' smokeTest'
		}
	}

}

def determineRegularBuildVersionNumber() {
    def properties = readProperties file: 'dev/gradle.properties'
    def versionPrefix = properties['versionPrefix']
    if(versionPrefix == null || versionPrefix == '') {
        error 'No internalVersionPrefix property found in the gradle.properties file. Please make sure this property is present with a correct value e.g. internalVersionPrefix=1.0'
    }

    def patchVersion = getPatchVersion(versionPrefix)

    VERSION_NUMBER = "${versionPrefix}.${patchVersion}"

    println "Building with version number: ${VERSION_NUMBER}"
}

def getPatchVersion(versionPrefix) {
    def patchVersion

    try {
        patchVersion = executeCommand("git rev-list --count ${versionPrefix}.0...HEAD")
    } catch(e) {
        printError(e)
        patchVersion = '0'
    }

    println "Patch version is [${patchVersion}]"

    return patchVersion
}

def executeCommand(String command) {
    sh "${command} > commandOutput.txt"
    def commandOutput = readFile("commandOutput.txt").trim()
    sh "rm commandOutput.txt"
}




