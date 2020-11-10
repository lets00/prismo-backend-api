# prismo-backend-api

This API implements the test passed by prismo to evaluate the candidate's programming skills. 
It's developed on Java using Springboot framework and PostgresSQL.

## How to run

You can choose docker containers or run locally your application.

### docker containers

First, you must guarantee docker and docker-compose are installed and work on your machine. After that, clone this repo and execute:

```sh
$ docker-compose up -d
```

By default, the app will be up on port 8080. 

Access _src/main/resources/application.properties_ to change app variables.

### running locally

To running locally, you must guarantee postgres(server or client), java and maven installed on your pc. After that, clone this repo and executes:

```sh
$ ./mvnw package -DskipTests
$ java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/prismo-backend-api -Dspring.datasource.username=postgres -Dspring.datasource.password=p@ssw0rd -jar target/bank-0.0.1-SNAPSHOT.jar
```

Change localhost, username and password to your local configurations.

## Routes

### Accounts (/accounts)

#### GET (http://localhost:8080/accounts/:id)

Get an account by ID.

**Request**

**Parameters**:
* **id**: Account ID (Must be an integer).


**Response**
**Body**:

* **accountId**: Account ID.
* **documentNumber**: Document Identification Number (string).

**Status code**

* 200: Ok
* 404: NotFound
* 400: Bad request (ID isn't a number)

---

#### POST (http://localhost:8080/accounts/)

Create a new account.

**Request**

**Body**:
* **documentNumber**: Document Identification Number (string).

**Response**

**Status code**

* 201: Created
* 400: Bad request (Account already created)


### Transactions (/transactions)

#### POST (http://localhost:8080/transactions/)

Create a new transaction.

**Request**

**Body**:
* **accountId**: A valid accountId.
* **operationTypeId**: A valid operationType ID.
* **amount**: Money value (must be a double).

**Response**

**Status code**

* 201: Created
* 422: AccountId or OperationTypeId invalid (not registred)
