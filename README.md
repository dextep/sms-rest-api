# 🏃 Sport meeting system - REST API

Sport meeting system server side. 
Allows to organization spontaneous sport events in your neighbourhood.

Live [preview](https://sportmeetingsystem.netlify.app), hosted by [Heroku](https://www.heroku.com/) and [Netfly](https://www.netlify.com/). 
__*Please note due of hosting limits the first load can take a while.*__

If your interested about frontend check my [sms-webapp](https://github.com/dextep/sms-webapp) repository.

This project was developed to learn and practice my skills.

![app-preview](https://i.ibb.co/zXDFfms/5.png)


## Table of contents

* [Technologies](#technologies)
* [Getting Started](#getting-started)
* [Setup](#setup)
* [Entity Relationship Diagram](#entity-Relationship-diagram)
* [Summary of endpoints](#summary-of-endpoints)
* [Preview](#preview)

## Technologies

Backend of project is creating with:

* Java 12
* Spring Boot 2.2.0
* Spring Security
* Spring Data
* Hibernate
* Maven
* PostgreSQL 12.2

## Getting Started

You can create your own user account or use existing one.

Sample user credentials:

- Login: **user@gmail.com**
- Password: **admin**

## Setup

Clone this repo to your local machine using https://github.com/dextep/sms-rest-api.git

To run this project, build it locally using mvn:
```
$ cd ../sms-rest-api
$ mvn spring-boot:run
```

## Entity Relationship Diagram

![erd](https://i.ibb.co/xGV2zMg/1.png)

## Summary of endpoints

![summary of endpoints](https://i.imgur.com/WhhM8uJ.jpg)

## Use case diagram

![use case diagram](https://i.ibb.co/J7d7KDK/2.png)

## Functional requirements diagram

![functional requirements diagram](https://i.ibb.co/n8ts1hs/3.png)

## Preview

### Registration panel

![registration panel](https://i.ibb.co/0KL2hvd/Screenshot-2020-04-26-at-16-11-01.png)

### Login panel

![login panel](https://i.ibb.co/GWb9MqX/Screenshot-2020-04-26-at-16-09-54.png)

### Adding a new event

![new event](https://i.ibb.co/5KnBxGy/3.png)

### Preview of an organized event

![organized event](https://i.ibb.co/HYTJd7b/4.png)

### Preview of an active event

![active event](https://i.ibb.co/zXDFfms/5.png)
