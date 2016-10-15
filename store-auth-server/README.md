# Synopsis
myRetail Authentication(OAuth2) Server handle all user information.

Following software packages needs to be installed on development machine in order to build and run myRetail Authentication(OAuth2) Server
### Prerequisites : 
* JDK 1.8+ 
* Docker 1.12.x
* Maven  3.3.9+
* Spring Tool Suite(Optional)

### Follow below steps to build and run service on your local machine: 
 
* Checkout code from github 
```
$ git clone -b develop https://github.com/hpatel-git/myRetailStore.git
$ cd myRetailStore/store-auth-server/      
```

* Build Authentication(OAuth2) Server
```
$ mvn package docker:build -P docker
```

* View generated docker images
```
$ docker images
REPOSITORY                   TAG                 IMAGE ID            CREATED             SIZE
hpatel511/store-auth-server   0.0.1               680d7c4c59d9        24 seconds ago      440.5 MB
frolvlad/alpine-oraclejdk8   slim                ea24082fc934        6 weeks ago         167.1 MB
```

* Run docker image 
``` 
$ docker run -p 8181:8181 hpatel511/store-auth-server:0.0.1 
```

* Verify service status
```
$ docker ps
CONTAINER ID        IMAGE                                    COMMAND                  CREATED              STATUS              PORTS               NAMES
4e8a0e1fb16c        hpatel511/store-auth-server:0.0.1   "/docker-entrypoint.s"   5 seconds ago       Up 5 seconds        3306/tcp, 0.0.0.0:8181->8181/tcp   tender_albattani
```

* SSH into Docker Running Container to view log and access database 
```
$ docker exec -it 4e8a0e1fb16c bash
bash-4.3#
```

* Push docker image Docker Hub 
```
$ docker push myretail/store-auth-server:0.0.1
```

### Pull myRetail Authentication(OAuth2) server docker image from Docker Hub and run on other machine :  
 
* Download myRetail Authentication(OAuth2) server from Docker Hub
```
$ docker pull hpatel511/store-auth-server:0.0.1
$ docker run -p 8181:8181 hpatel511/store-auth-server:0.0.1
```

* Run loaded image on other machin 
```
$ docker run -p 8181:8181 myretail/store-auth-server:0.0.1
```

* Externalize Mysql Configuration( If you want to use MySQL on host machine instead of docker) 
```
$ docker run -p 8181:8181 -e MYSQL_HOST=localhost -e MYSQL_PORT=3306 -e MYSQL_USER=dbadmin -e MYSQL_PASSWORD=dbadmin -e MYSQL_DATABASE=my_retail_store_ums  myretail/store-auth-server:0.0.1
```
* Generate Administrator token to update Price information of product ( default user name / password : sysadmin/sysadmin )
```
curl acme:acmesecret@192.168.99.100:8181/store-auth-server/oauth/token -d grant_type=password -d username=sysadmin -d password=sysadmin
``` 



 