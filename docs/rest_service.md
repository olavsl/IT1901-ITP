# Documentation of REST server and API

## Methods
- **GET** /personal_finance | Get a *PersonalFinanceModel*
- **GET** /personal_finance/users/{user} | Get a *User*
- **PUT** /personal_finance/users/{user} | Update a *User*
- **POST** /personal_finance/users/{user} | Create a new *User*
- **DELETE** /personal_finance/users/{user} | Remove a *User*

## Spring boot
The REST server is created with Spring Boot. In the *rest*-module, the spring-boot server parts are:

- Application - initializes the server with jackson module.
- Controller - handles calls to the server
- Service - handles storing of user information

There is also a remote access class in the *fxutil*-module, which converts user actions to HTTP requests, and updates user info in the database if the HTTP request is accepted.