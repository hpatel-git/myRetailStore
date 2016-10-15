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
$ git clone -b develop git@git.letv.cn:bin.gong/euicloudservice.git
$ cd euicloudservice/      
```

* Build EUI cloud micro service code 
```
$ mvn package docker:build -P docker
```

* View generated docker images
```
$ docker images
REPOSITORY                   TAG                 IMAGE ID            CREATED             SIZE
leeco/eui-cloud-service      0.0.1-SNAPSHOT      738a2bacf4d9        22 minutes ago      417.1 MB
frolvlad/alpine-oraclejdk8   slim                ea24082fc934        6 weeks ago         167.1 MB
```

* Run docker image 
``` 
$ docker run -p 8080:8080 leeco/eui-cloud-service:0.0.1-SNAPSHOT 
```

* Verify service status
```
$ docker ps
CONTAINER ID        IMAGE                                    COMMAND                  CREATED              STATUS              PORTS               NAMES
5636ee8659c6        leeco/eui-cloud-service:0.0.1-SNAPSHOT   "/docker-entrypoint.s"   About a minute ago   Up About a minute   3306/tcp          reverent
```

* SSH into Docker Running Container to view log and access database 
```
$ docker exec -it 5636ee8659c6 bash
bash-4.3#
```

* Save running docker image as tar file 
```
$ docker save leeco/eui-cloud-service:0.0.1-SNAPSHOT > eui-cloud-service-0.0.1-SNAPSHOT.tar
```
### Import docker image and run on other machine :  
 
* Download eui-cloud-service-0.0.1-SNAPSHOT from http://git.letv.cn/bin.gong/euicloudservice/blob/develop/eui-cloud-service-0.0.1-SNAPSHOT.tar
```
$ curl -O http://git.letv.cn/bin.gong/euicloudservice/blob/develop/eui-cloud-service-0.0.1-SNAPSHOT.tar
% Total       % Received % Xferd  Average Speed   Time    Time     Time  Current
                                  Dload  Upload   Total   Spent    Left  Speed
 100    98    0    98    0     0    262      0 --:--:-- --:--:-- --:--:--   262
$ ls
eui-cloud-service-0.0.1-SNAPSHOT.tar
```

* Load copied image to development machine 
```
$ docker load -i eui-cloud-service-0.0.1-SNAPSHOT.tar
```

* Run loaded image on other machin 
```
$ docker run -p 8080:8080 leeco/eui-cloud-service:0.0.1-SNAPSHOT
```

* Externalize Mysql Configuration 
```
$ docker run -p 8080:8080 -e MYSQL_HOST=10.79.5.87 -e MYSQL_PORT=3306 -e MYSQL_USER=euidev -e MYSQL_PASSWORD=euidev -e MYSQL_DATABASE=eui_service_dev  leeco/eui-cloud-service:0.0.1-SNAPSHOT
``` 
 