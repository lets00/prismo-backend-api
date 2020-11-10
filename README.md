# prismo-backend-api

This API implements the test passed by prismo to evaluate the candidate's programming skills. 
It's developed on Java using Springboot framework and PostgresSQL.

## How to run

You can choose docker containers or run locally your application.

### docker containers
TO DO
### running locally
TO DO

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
