Loan Management System Backend

This is a backend application for managing loans, built using Java, Spring Boot, Hibernate/JPA, and MySQL/PostgreSQL. The system allows managing customers, loan applications, repayments, and generates loan status reports.

Setup Instructions

Prerequisites
- Java 8 or above: Download Java (https://www.oracle.com/java/technologies/javase-jdk8-downloads.html)
- Maven: Download Maven (https://maven.apache.org/download.cgi)
- MySQL/PostgreSQL: Install and configure your database.

1. Clone the Repository
git clone https://github.com/saloni0706/loanManagement.git
cd loan-management-system

2. Configure the Database
Set up a PostgreSQL database and update src/main/resources/application.properties with your database credentials.

For PostgreSQL:
spring.datasource.url=jdbc:postgresql://localhost:5432/loan_management
spring.datasource.username=username
spring.datasource.password=password

3. Install Dependencies
Run the following command to install dependencies:
mvn install

4. Run the Application
Run the application using Maven:
mvn spring-boot:run

5. Access the Application
Once the application is running, you can access the backend API at:
http://localhost:9595

Testing the Endpoints (Sample)

- Add Customer: POST /addCustomer  
  Request body:
  {
    "name": "John Doe",
    "email": "john.doe@example.com",
    "password": "password123",
    "phone": "1234567890",
    "address": "123 Main St",
    "role": "USER"
  }
  
  
- Login: POST /login  
  Request body:
  {
    "username": "john.doe@example.com",
    "password": "password123"
  }

  ✅ After login, the response will contain a JWT token.

- Get Customer by ID: GET /customers/{id}

- Update Customer: PUT /customers/{id}  
  Request body:
  {
    "name": "John Smith",
    "phone": "9876543210"
  }

- Apply for Loan: POST /loans/apply  
  Request body:
  {
    "loanAmount": 50000,
    "interestRate": 5.5,
    "durationMonths": 12,
    "loanPurpose": "Home Renovation"
  }

- Make a Repayment: POST /repayments/make  
  Request body:
  {
    "loanId": 1,
    "amount": 5000,
    "paymentDate": "2025-05-08"
  }

- Get Loan History for a Customer:  
  GET /loans/customers/{id}/loan-history

- Get Pending Loans:  
  GET /loans/pending

- Get Overdue Loans:  
  GET /loans/overdue

- Get Dashboard Stats (for Admin):  
  GET /admin/dashboard

- Get Loans by Status:  
  GET /loans/status/{status}

🔐 For protected routes, pass the JWT token in the Headers tab with:  
  Key = Authorization  
  Value = Bearer <space> generated token here  
  You will get this token in the response after a successful login.

Assumptions & Limitations

- Interest is calculated using simple interest formula.
- JWT authentication is basic and for demo purpose (no refresh token).
- Overdue detection logic assumes daily repayment schedule (custom logic can be added).
- Admin/User roles are determined via Customer.role field.

Database Schema

Customer
- id (Long)
- name (String)
- email (String, unique)
- password (String)
- phone (String)
- address (String)
- role (String)

Loan
- id (Long)
- customer (ManyToOne)
- loanAmount (Double)
- interestRate (Double)
- durationMonths (Integer)
- loanPurpose (String)
- status (String: pending/approved/rejected/repaid/overdue)
- approvedDate (Date)

Repayment
- id (Long)
- loan (ManyToOne)
- amount (Double)
- paymentDate (Date)
