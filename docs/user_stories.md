# User stories

Here we collect user stories that are intended to (un)cover requirements that the system must satisfy. There are different forms of user stories with more or less strict requirements on form, here we do it rather informally, but try to summarize each of them somewhat formally.

**User story 1: Create a new user account**

As a user I want to create a new user account so that I can use the application. The user account should be created with a username and a password. The username must be unique, between 4 and 20 characters long, and must only contain letters and numbers. The password should be stored in a secure way, and the user must confirm the password by entering it twice.

**User story 2: Transaction overview** 

As a user I want to see an overview of all my transactions so that I can see how much money I have spent and how much money I have left. The overview should show the date, the amount, and the category of each transaction. The overview should be sorted by date, and with the most recent transactions first. //(The overview should be paginated, with 10 transactions per page.)

**User story 3: Create a new transaction** 

As a user I want to create a new transaction so that I can record my expenses. The transaction should be created with a date, an amount, and a category. The date should be in the past, and the amount should be a positive number. The category should be one of the categories that I have created.

**User story 4: Choose a category for a new transaction**

As a user I want to choose a category for a new transaction so that I can record my expenses in a meaningful way. The category should be one of the categories that I have created. If I do not have any categories, I should be able to create a new one.

**User story 5: Sorting/ filtering of transactions** 

As a user I want the opportunity to sort and filter my transactions so that I can see the transactions that I am interested in. I should be able to sort the transactions by date, amount, and category. I should also be able to filter the transactions by date, amount and category. // (The filtering should be done by selecting one or more values from a list of values. The list of values should be sorted alphabetically.)

**User story 6: Storage of userdata**

As a user I want my userdata to be stored so that I can access it later. The userdata should be stored in a secure way so that only I can access it. The userdata should be stored in a database.

**User story 7: Password protected accounts**

As a user I want my account to be password protected so that only I can access it. The password should be stored in a secure way. The password should be confirmed by entering it twice.

**User story 8: Add more bank accounts**

As a user I want to be able to add more bank accounts, so that I can see all my transactions in one place. I should be able to add a new bank account with a name and a balance. The name should be unique, and the balance should be a positive number.


**User story 9: Create new budget**

As a user I want to be able to create a new budget, so that I can see how much money I have left to spend. I should be able to create a new budget with a name, a start date, and an end date. The name should be unique, and the start date should be before the end date.

**User story 10: Overview of budgets** 

As a user I want to see an overview of all my budgets, so that I can see how much money I have left to spend. The overview should show the name, the start date, the end date, and the amount of money that I have left to spend. The overview should be sorted by start date, and with the most recent budgets first. 

**User story 11: Compare consumption to budget** 

As a user I want to be able to compare my consumption to my budget, so that I can see how much money I have left to spend. I should be able to see how much money I have left to spend in each budget. I should also be able to see how much money I have spent in each budget. 
