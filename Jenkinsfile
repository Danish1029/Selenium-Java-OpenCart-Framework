pipeline {

    agent any

    options {
        timestamps()
        buildDiscarder(logRotator(
                daysToKeepStr: '30',
                numToKeepStr: '50'))
        timeout(time: 30, unit: 'MINUTES')
    }

   parameters {

    choice(
        name: 'BROWSER',
        choices: ['chrome','edge','firefox'],
        description: 'Select Browser'
    )

    choice(
        name: 'ENV',
        choices: ['qa','uat','prod'],
        description: 'Select Environment'
    )

    booleanParam(
        name: 'HEADLESS',
        defaultValue: false,
        description: 'Headless Execution'
    )

}

    environment {

        MAVEN_OPTS = '-Xmx1024m'

    }

    stages {

        stage('Checkout Source') {

            steps {

                echo '========== CHECKOUT =========='

                cleanWs()

                checkout scm

            }
        }

        stage('Build Project') {

            steps {

                echo '========== BUILD =========='

                bat 'mvn clean compile'

            }

        }

        stage('Execute Tests') {

            steps {

                echo '========== TEST EXECUTION =========='

               bat """
mvn clean test ^
-Dbrowser=${params.BROWSER} ^
-Denv=${params.ENV} ^
-Dheadless=${params.HEADLESS}
"""

            }

        }

        stage('Publish Allure Report') {

            steps {

                allure(
                    includeProperties: false,
                    jdk: '',
                    results: [[path: 'allure-results']]
                )

            }

        }

        stage('Publish HTML Report') {

            steps {

                publishHTML(target: [

                    allowMissing: true,

                    alwaysLinkToLastBuild: true,

                    keepAll: true,

                    reportDir: 'reports',

                    reportFiles: '*.html',

                    reportName: 'Extent Report'

                ])

            }

        }

        stage('Archive Reports') {

            steps {

                archiveArtifacts artifacts: '''
                reports/**/*.html,
                screenshots/**/*.png,
                logs/**/*.log,
                allure-results/**
                ''',

                fingerprint: true

            }

        }

    }

    post {

        success {

            echo 'BUILD SUCCESS'

        }

        failure {

            echo 'BUILD FAILED'

        }

        unstable {

            echo 'BUILD UNSTABLE'

        }

        always {

            echo 'Cleaning Workspace'

            cleanWs()

        }

    }

}