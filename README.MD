# play-mysql

This is a experiment project to work with scala, play, slick, and mysql. The purpose of this is only for education.
It will create a table called user, and it will contain `first_name` (`String`), `last_name`(`String`), `mobile` (`Long`), `email`(`String`).
This project is to get first overview on JSON-Based REST CRUD Service.

## Requirement
1. Make sure MySQL is installed locally
2. Create database `play_mysql`
3. Make sure `sbt` is installed

## Quick Start
1. `sbt run`
2. Insert the user
```
curl -X POST http://localhost:9000/users -H 'Content-Type: application/json' \
    -d '{"firstName":"John", "lastName":"Doe", "mobile": 123456788, "email": "jdoe@mail.co"}'
```
3. Get the user
```
curl http://localhost:9000/users
```

## More Examples
### CREATE
```
curl -X POST  http://localhost:9000/users   \
    -H 'Content-Type: application/json'     \
    -d '{"firstName":"John", "lastName":"Doe", "mobile": 123456788, "email": "jdoe@mail.co"}'
```
### READ
```
curl http://localhost:9000/users
```
### UPDATE
```
curl -X PUT  http://localhost:9000/users/1   \
    -H 'Content-Type: application/json'     \
    -d '{"firstName":"John", "lastName":"Doe", "mobile": 123456788, "email": "jdoe@mail.co"}'
```
### DELETE
```
curl -X DELETE http://localhost:9000/users/1
```