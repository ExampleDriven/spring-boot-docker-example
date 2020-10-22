[![Build Status](https://travis-ci.org/ExampleDriven/spring-boot-docker-example.svg?branch=master)](https://travis-ci.org/ExampleDriven/spring-boot-docker-example)
# spring-boot-docker-example

This is the source code for the blog post :

https://exampledriven.wordpress.com/2016/06/24/spring-boot-docker-example/

Features :
- Maven docker plugin
- build images without dockerfile
- Docker in a multi module project
- docker-compose definition

How to run this example :

```sh
## build docker images
mvn clean install

##should display three freshly built docker images
docker images

##start up all instances
docker-compose up

##starts a 2nd instance of echo-service
docker-compose scale echo-service=2
```

## Useful docker commands

```sh
##Starting multiple echo services
docker-compose scale echo-service=3

##Replace a running container with the latest version (during development)
mvn install
docker-compose stop echo-service
docker-compose up -d echo-service
```

Once all the services are up, the following URLs will be available:

Address | Description
--- | ---
http://<\<docker-host>\>:8761 | Eureka service
http://<\<docker-host>\>:9090/routes | Zuul route definitions
http://<\<docker-host>\>:9090/api/echo-service/echo | Echo service through Zuul api gateway, looked up from Eureka registry
http://<\<docker-host>\>:9090/api/echo-service/echo/remote-echo | Echo service calling remote echo services
http://<\<docker-host>\>:9090/api/echo-service-by-dns/echo/remote-echo | Echo service through Zuul api gateway, located by DNS entry http://echo-service:9098
