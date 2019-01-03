# Jenkins Pipeline to push JIRA ticket with SAST findings
* Author:   Pedric Kng  
* Updated:  02 Jan 2019

This article describes the steps to capture the SAST findings as part of the risk management via JIRA.

The workflow described as below;
1. As part of the Jenkins Pipeline, execute SAST scan using Checkmarx plugin.
2. Should the plugin threshold be exceeded leading to a failed/unstable build, capture the scan results summary and push as a ticket to JIRA for action.

***
# Setup

## Dependencies
JIRA Pipeline Steps

## Configuration

1. In Jenkins, go to Manage Jenkins → Configure System. Under Global Pipeline Libraries, add a library with the following settings:

- Name: pipeline-library-demo
- Default version: Specify a Git reference (branch or commit SHA), e.g. master
- Retrieval method: Modern SCM
- Select the Git type
- Project repository: https://github.com/monodot/pipeline-library-demo.git
- Credentials: (leave blank)

2. Then create a Jenkins job with the following pipeline (note that the underscore _ is not a typo):

```groovy
@Library('pipeline-library-demo')_

stage('Demo') {
  echo 'Hello World'
  sayHello 'Dave'
}
```

```groovy
stage('JIRA') {
    withEnv(['JIRA_SITE=LOCAL']) {
      def testIssue = [fields: [ project: [id: '10300'],
                                 summary: 'New JIRA Created from Jenkins.',
                                 description: 'New JIRA Created from Jenkins.',
                                 issuetype: [id: '10003']]]

      response = jiraNewIssue issue: testIssue

      echo response.successful.toString()
      echo response.data.toString()
    }
  }
```


```groovy
@Library('pipeline-library-demo') _

pipeline {
  agent any

  stages{
      stage('Hello') {
          steps {
            echo 'Hello World'
            parseXMLReport 'Dave'
          }
      }
      stage('JIRA') {
          environment {
              JIRA_SITE = 'LOCAL'
          }
          steps {
              script {
                def testIssue = [fields: [ project: [id: '10300'],
                    summary: 'New JIRA Created from Jenkins.',
                    description: 'New JIRA Created from Jenkins.',
                    issuetype: [id: '10003']]]

                response = jiraNewIssue issue: testIssue
                echo response.successful.toString()
                echo response.data.toString()      
              }
          }
      }
  }
}
```

# References
CxSAST Jenkins Plugin [[1]]  
Jenkins Pipeline Jira Steps Plugin [[2]]  
Extending with Shared Libraries [[3]]  

[1]:https://checkmarx.atlassian.net/wiki/spaces/KC/pages/11337790/CxSAST+Jenkins+Plugin "CxSAST Jenkins Plugin"
[2]:https://jenkinsci.github.io/jira-steps-plugin/ "Jenkins Pipeline Jira Steps Plugin"
[3]:https://jenkins.io/doc/book/pipeline/shared-libraries/ "Extending with Shared Libraries"
