# Intersetellar Route Planner
Learn how the `Intersetellar Route Planner` operate:
https://github.com/TheKeyholdingCompany/tech-challenge-backend-dev

## Spring Boot, MySQL, JPA, Rest API

Restful API for a simple application "Interstellar Route Planner" using Spring Boot, MySQL, JPA.

## Requirements

1. Java - 17

2. Maven - 3.x.x

3. MySQL - 9.x.x

## Steps to Setup

**1. Clone the application**

```
git clone https://github.com/franceslou1818/interstellar-route-planner
```

**2. Create MySQL database**
Run the query in `create-local-db.sql` using your local MySQL.

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

+ Comment out the property `spring.datasource.url` for deployment. Use the local property.

**4. Open and change `pom.xml`**

+ Comment out the `<build><plugin>` for deployment. Use the local plugins.

**5. Build and run the app using maven**

```
mvn package
java -jar target/interstellar.jar
```

Alternatively, you can run the app without packaging it using -

```
mvn spring-boot:run
```

## Rest APIs

The app defines following CRUD APIs.

    GET: {{baseUrl}}/transport/{distance}?passengers={number}&parking={days}

    GET: {{baseUrl}}/gates

    GET: {{baseUrl}}/gates/{gateCode}
    
    GET: {{baseUrl}}/gates/{gateCode}/to/{targetGateCode}

where `{{baseUrl}}` is `https://xdzx3lpsdk.execute-api.eu-north-1.amazonaws.com/dev`

Example of a public endpoint: `https://xdzx3lpsdk.execute-api.eu-north-1.amazonaws.com/dev/gates`

You can test them using postman or any other rest client.
You can use the supplied postman collection and environment files in directory `postman/`

