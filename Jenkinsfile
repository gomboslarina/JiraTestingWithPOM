pipeline {
    agent any
    environment {
    USERNAME=user7;
    INCORRECT_USERNAME=user;
    PASSWORD=CoolCanvas19.;
    EMAIL=user7@user.com;
    INCORRECT_PASSWORD=pwd;
    COMPURL=https://jira.codecool.codecanvas.hu/projects/PP1?selectedItem=com.codecanvas.glass:glass
    COMPNAME=TestComponent
    COMPURL=https://jira.codecool.codecanvas.hu/projects/PP1?selectedItem=com.atlassian.jira.jira-projects-plugin:components-page
    PASSWORD=CoolCanvas19.
    BASEURL=https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa
    USERNAME=user8
    INCORRECT_USERNAME=incuser
    EMAIL=user8@user.com
    NODEURL=https://seleniumhub.codecool.codecanvas.hu/wd/hub
    INCORRECT_PASSWORD=pwd

    }
    stages {
        stage('Initialize') {
            steps {

                git branch: 'ci-integration-with-env', url: 'https://github.com/gomboslarina/JiraTestingWithPOM'
            }
        }
        stage('Run tests') {
            steps {
                sh 'mvn test'
            }
        }
    }
}