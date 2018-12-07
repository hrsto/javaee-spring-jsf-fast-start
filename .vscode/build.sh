#!/bin/bash

opts=""

set -e

function getFirstTagValue {
    cat pom.xml | sed -En "0,/version/s/<$1>(.*)<\/$1>/\1/p" | sed 's/^[ \t]*//;s/[ \t]*$//'
}

#options for main(String[] args)
runtimeOpts=""
tomcatDir=/c/_commonShare/apache-tomcat-8.0.53/webapps

projectName=$(getFirstTagValue artifactId)-$(getFirstTagValue version)

tomcatServerAuth='admin:admin'
tomcatServerAddr='http://localhost:8080'

case $1 in
    "build")
        mvn $opts package -Dmaven.test.skip=true 
        rsync -av --delete target/${projectName}/ ${tomcatDir}/${projectName}
        curl --user ${tomcatServerAuth} ${tomcatServerAddr}/manager/text/reload?path=/${projectName}
    ;;
    "justrun")
    
    ;;
    "test")
        mvn $opts test
    ;;
    "clean")
        mvn $opts clean
    ;;
    "verify")
        mvn $opts verify
    ;;
    "execute")
        
    ;;
    *) echo "Supply build|justrun|test|clean|verify|execute"
    ;;
esac
