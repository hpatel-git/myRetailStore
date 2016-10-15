FROM frolvlad/alpine-oraclejdk8:slim

MAINTAINER Hardikkumar Patel<hardik.patel@le.com>

RUN apk add --no-cache bash gawk sed grep bc coreutils

# Install mysql and mysql client on docker
RUN apk add --update \
    mysql-client \
    && rm -rf /var/cache/apk/*
    
RUN apk update 

RUN apk add mysql 
 
EXPOSE 3306

VOLUME /tmp

ADD eui-cloud-service-dashboard-0.0.1-SNAPSHOT.jar app.jar

RUN sh -c 'touch /app.jar'

#VOLUME /var/lib/mysql

COPY docker-entrypoint.sh /
COPY init-data.sql /

RUN chmod 777 /docker-entrypoint.sh

ENTRYPOINT ["/docker-entrypoint.sh"]
 