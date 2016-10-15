# Synopsis
myRetail Store API Gateway is aggregator for product and price information available (Product information from third party server and price information from internal pricing service).

Following software packages needs to be installed on development machine in order to build and run myRetail Store API Gateway
### Prerequisites : 
* JDK 1.8+ 
* Docker 1.12.x
* Maven  3.3.9+
* Spring Tool Suite(Optional)

### Follow below steps to build and run service on your local machine: 
 
* Checkout code from github 
```
$ git clone -b develop https://github.com/hpatel-git/myRetailStore.git
$ cd myRetailStore/store-api/      
```

* Build Authentication(OAuth2) Server
```
$ mvn package docker:build
```

* View generated docker images
```
$ docker images
REPOSITORY                   TAG                 IMAGE ID            CREATED             SIZE
hpatel511/store-api          0.0.1               d0db2c47a5ea        26 seconds ago      248.4 MB
frolvlad/alpine-oraclejdk8   slim                ea24082fc934        6 weeks ago         167.1 MB
```

* Run docker image 
``` 
$ docker run -p 8182:8182 hpatel511/store-api:0.0.1 
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
$ docker push hpatel511/store-api:0.0.1
```

### Pull myRetail API Gateway docker image from Docker Hub and run on other machine :  
 
* Download myRetail Authentication(OAuth2) server from Docker Hub
```
$ docker pull hpatel511/store-api:0.0.1
$ docker run -p 8182:8182 hpatel511/store-api:0.0.1
```

* Run loaded image on other machin 
```
$ docker run -p 8181:8181 myretail/store-auth-server:0.0.1
```

* Health Check for API Gateway
```
curl -i http://192.168.99.100:8182/eui-store-api/health

{"status":"UP"}
``` 



 