pipeline {
    agent any
    stages {
        stage('Initialize') {
            steps {

                git branch: 'ci-integration-with-env', url: 'https://github.com/gomboslarina/JiraTestingWithPOM'
            }
        }
        stage('Run tests') {
            steps {
                sh 'mvn test -DGLASSCOMPURL=https://jira.codecool.codecanvas.hu/projects/PP1?selectedItem=com.codecanvas.glass:glass -DCOMPNAME=TestComponent -DCOMPURL=https://jira.codecool.codecanvas.hu/projects/PP1?selectedItem=com.atlassian.jira.jira-projects-plugin:components-page -DPASSWORD=CoolCanvas19. -DBASEURL=https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa -DUSERNAME=user7 -DINCORRECT_USERNAME=incuser -DEMAIL=user7@user.com -DNODEURL=https://selenium:CoolCanvas19.@seleniumhub.codecool.codecanvas.hu/wd/hub -DINCORRECT_PASSWORD=pwd'
            }
        }
    }
}