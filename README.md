# Banking Transactions API

A simple Spring Boot REST API for managing user accounts and transactions between accounts.

## Features
- Create a new user account with an initial balance
- Transfer funds between accounts
- Retrieve transaction history for a given account

## Requirements
- Java 17+
- Maven

## How to Run
1. Clone the repository
2. Build and run the project:
   mvn spring-boot:run
3. API will be available at http://localhost:8080

## Testing
1. Create a new account

Send POST request to http://localhost:8080/accounts with JSON body:

{
  "ownerName": "Alice",
  "initialBalance": 1000
}

2. Transfer funds

Send POST request to http://localhost:8080/accounts/transfer with JSON body:

{
  "fromAccountId": 1,
  "toAccountId": 2,
  "amount": 200
}

(make sure you have created at least two accounts before this)

3. Get transaction history

Send GET request to http://localhost:8080/accounts/{id}/transactions , replacing {id} with the account of interest. For example, for Alice, id == 1, as shown by the API's response.