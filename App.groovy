properties([[$class: 'JiraProjectProperty'], parameters([string(defaultValue: 'v1', description: 'Please provide a version number', name: 'APP_VERSION', trim: false)])])
node {
    stage("pull repo") {
        git 'https://github.com/Mahsuda/Nodejs_app.git'

    }

    stage("Build, tag image ") {
        sh "docker build  -t first_repo:${APP_VERSION} . "

    }
    stage("login to ECR") {
        sh '''$(aws ecr get-login --no-include-email --region eu-west-1)'''

    }
    stage("docker push") {
        sh "docker push 541244714219.dkr.ecr.eu-west-1.amazonaws.com/first_repo:${APP_VERSION}"

    }
    stage("Notify") {
        sh "echo Hello"

    }
}
