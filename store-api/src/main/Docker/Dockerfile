FROM frolvlad/alpine-oraclejdk8:slim

MAINTAINER Hardikkumar Patel<hardik.patel@le.com>

RUN apk add --no-cache bash gawk sed grep bc coreutils

# Install mysql and mysql client on docker
RUN apk add --update \
    mysql-client \
    && rm -rf /var/cache/apk/*
    
RUN apk update 

ADD store-api-0.0.1.jar app.jar

RUN sh -c 'touch /app.jar'
  
COPY docker-entrypoint.sh /

RUN chmod 777 /docker-entrypoint.sh

ENTRYPOINT ["/docker-entrypoint.sh"]
 