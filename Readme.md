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

**4. Build and run the app using maven**

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

    GET: /transport/{distance}?passengers={number}&parking={days}

    GET: /gates

    GET: /gates/{gateCode}
    
    GET: /gates/{gateCode}/to/{targetGateCode}

You can test them using postman or any other rest client.
You can use the supplied postman collection and environment files in directory `postman/`

