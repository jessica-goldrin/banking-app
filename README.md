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
    `mvn spring-boot:run`
    You'll know it's ready when you see `Started BankingApplication in X seconds` in the terminal

3. API will be available at `http://localhost:8080`

## Testing
1. Create a new account

Send POST request to `http://localhost:8080/accounts` with JSON body:

```
{
  "ownerName": "Alice",
  "initialBalance": 1000
}
```
2. Transfer funds

Send POST request to `http://localhost:8080/accounts/transfer` with JSON body:

```
{
  "fromAccountId": 1,
  "toAccountId": 2,
  "amount": 200
}
```

Make sure both accounts exist before you do this.

3. Get transaction history

Send GET request to `http://localhost:8080/accounts/{id}/transactions` , replacing {id} with the account of interest. For example, for Alice's transactions, you would call `http://localhost:8080/accounts/1/transactions`, since her id == 1.