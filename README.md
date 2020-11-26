RUN WITH SPRING-BOOT:

* Requirements: 

Maven 3
Java 8

* Run application:

mvn spring-boot:run

---------------------------------------------------
RUN WITH DOCKER:

* Requirements:

Docker
Maven

* Build (by console):

In project folder run: 

mvn clean package -Dmaven.test.skip=true

docker build -t test/ml .

* Run application:

docker run -p 8080:8080 test/ml

---------------------------------------------------
TEST (WITH SWAGGER)

Go http://localhost:8080/swagger-ui/

---------------------------------------------------
* REST API ENDPOINTS:

Get and save ip information:

POST	/trace	
{
  "ip": "1.0.127.255"
}

Get ips statistics:

GET		/stats

---------------------------------------------------
* SOME IPS FOR TEST:

arg:
190.229.108.194

jpn:
1.0.127.255

cu:
152.206.0.0

rus:
31.25.24.0

ind:
14.102.224.0

bel:
91.241.79.0

gbr:
37.122.192.0

