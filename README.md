# Accounting Ledger Application

A command-line accounting ledger application that tracks financial transactions with deposit/payment recording, ledger viewing, and reporting capabilities.

## Features

- **Transaction Management**
  - Record deposits (credits)
  - Record payments (debits)
  - All transactions saved to `transactions.csv`

- **Ledger Viewing**
  - View all transactions (newest first)
  - Filter by deposits only
  - Filter by payments only

- **Advanced Reporting**
  - Month-to-Date report
  - Previous Month report
  - Year-to-Date report
  - Previous Year report
  - Vendor search
  - Custom search with multiple filters

## Screenshots

### Home Menu
==================================
======== LEDGER ACCOUNT ==========
==================================
D) Add Deposit
P) Make Payment (Debit)
L) Ledger
X) Exit


### Ledger Menu
==================================
============ LEDGER ==============
==================================
A) Show All Transactions
D) Show Deposits Only
P) Show Payments Only
R) Reports
H) Go Back to Home


### Sample Transaction Entry
2023-11-15|14:30:45|Office Supplies|Amazon|-125.99
2023-11-15|15:12:00|Client Payment|Acme Corp|1500.00



Key Features Implemented
File I/O operations for persistent storage

Date/time handling with Java Time API

Menu navigation with loop control

Input validation

Clean transaction formatting
