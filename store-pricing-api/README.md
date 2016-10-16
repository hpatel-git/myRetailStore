# Synopsis
myRetail Store Pricing API. Provide a way to retrieve and update price

Following software packages needs to be installed on development machine in order to build and run myRetail Store Pricing Server
### Prerequisites : 
* JDK 1.8+ 
* Docker 1.12.x
* Maven  3.3.9+
* Spring Tool Suite(Optional)

### Pull myRetail Pricing Server docker image from Docker Hub and run on local machine :  
 
* Download myRetail Pricing  server from Docker Hub
```
$ docker pull hpatel511/store-pricing-api:0.0.1
$ docker run -p 8183:8183 hpatel511/store-pricing-api:0.0.1
```

* Run loaded image 
```
$ docker run -p 8183:8183 -e MONGODB_HOST=10.0.0.9 -e MONGODB_PORT=27017 -e MONGODB_DATABASE=my_retail_product_price_db hpatel511/store-pricing-api:0.0.1
```

* Health Check for API Gateway
```
curl -i http://192.168.99.100:8183/store-pricing-api/health

{"status":"UP"}
``` 

### Follow below steps to build from source code and run service on your local machine: 
 
* Checkout code from github 
```
$ git clone -b develop https://github.com/hpatel-git/myRetailStore.git
$ cd myRetailStore/store-pricing-api/      
```

* Build Pricing Server
```
$ mvn package docker:build -P docker -Dmaven.test.skip=true
```

* View generated docker images
```
$ docker images
REPOSITORY                    TAG                 IMAGE ID            CREATED             SIZE
hpatel511/store-pricing-api   0.0.1               8b69c8891fe4        5 seconds ago       244.8 MB
frolvlad/alpine-oraclejdk8    slim                ea24082fc934        6 weeks ago         167.1 MB
```

* Run docker image 
``` 
$ docker run -p 8183:8183 -e MONGODB_HOST=10.0.0.9 -e MONGODB_PORT=27017 -e MONGODB_DATABASE=my_retail_product_price_db hpatel511/store-pricing-api:0.0.1 
```

* Verify service status
```
$ docker ps
CONTAINER ID        IMAGE                                    COMMAND                  CREATED              STATUS              PORTS               NAMES
4e8a0e1fb16c        hpatel511/store-auth-server:0.0.1   "/docker-entrypoint.s"   5 seconds ago       Up 5 seconds        3306/tcp, 0.0.0.0:8181->8181/tcp   tender_albattani
```

* Push docker image Docker Hub 
```
$ docker push hpatel511/store-pricing-api:0.0.1
```




 