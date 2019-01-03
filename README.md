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

# References
CxSAST Jenkins Plugin [[1]]  


[1]:https://checkmarx.atlassian.net/wiki/spaces/KC/pages/11337790/CxSAST+Jenkins+Plugin "CxSAST Jenkins Plugin"
