🏦 ATM Simulator System
A Java-based ATM Simulator that allows users to perform banking transactions such as deposits, withdrawals, balance inquiries, and mini statements, with secure data handling and a user-friendly GUI.

🚀 Features
✅ User Authentication (Card Number & PIN)
✅ Secure Transactions (Deposit, Withdrawal, Fast Cash)
✅ Balance Inquiry & Mini Statement
✅ PIN Change Functionality
✅ JSON-based Local Storage & MySQL Database Support
✅ Email Notifications for Transactions
✅ Error Handling & User-Friendly Swing UI


🛠️ Technologies Used
Programming Language: Java
GUI Framework: Java Swing
Database: MySQL
Connectivity: JDBC
JSON Handling: Gson
DevOps Tools: Git, GitHub
Email Services: SMTP (via Gmail API)
Build Tool: Maven
Containerization: Docker

⚙️ Installation & Setup
1️⃣ Clone the Repository
git clone https://github.com/Sahilyadav24/ATM_Simulator_System_2.git
cd ATM_Simulator_System_2

2️⃣ Configure Database
Install MySQL and create a database named bankmanagementsystem.
Import the provided SQL schema (bankmanagementsystem.sql).
Update database credentials in connectJDBC.java:

String url = "jdbc:mysql://localhost:3306/bankmanagementsystem";
String user = "root";
String password = "root";


3️⃣ Run the Application
Using Maven:
Ensure Maven is installed on your system. You can check if Maven is installed by running mvn -v in the terminal.

Build the Project:
Run the following command to build the project and resolve dependencies:
mvn clean install

Run the Application:
Once the build is successful, run the application:
mvn exec:java -Dexec.mainClass="Login"

Manually in an IDE:
Open the project in IntelliJ IDEA, VS Code, or Eclipse.
Compile and run the Login.java file.

4️⃣ Docker Setup (Optional)
If you prefer to run the application inside a Docker container, follow these steps:

Dockerfile
This Dockerfile packages the ATM Simulator application into a Docker image.
FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/atm-simulator-1.0-SNAPSHOT.jar /app/atm-simulator.jar

CMD ["java", "-jar", "atm-simulator.jar"]

Steps to Build and Run in Docker:
Build the application using Maven (as described above).
Create a Docker image using the following command

docker build -t atm-simulator .

Run the Docker container:

docker run -d -p 8080:8080 atm-simulator


Project Structure
ATM_Simulator_System_2
│── src
│   ├── Login.java                  # User authentication
│   ├── SignupOne.java              # Registration Step 1
│   ├── SignupTwo.java              # Registration Step 2
│   ├── SignupThree.java            # Registration Step 3 (Card & PIN generation)
│   ├── Deposit.java                # Deposit functionality
│   ├── Withdraw.java               # Withdrawal functionality
│   ├── BalanceEnquiry.java         # Check account balance
│   ├── MiniStatement.java          # Transaction history
│   ├── Transactions.java           # Main transaction menu
│   ├── FastCash.java               # Quick withdrawal options
│   ├── EmailUtility.java           # Email notifications
│   ├── connectJDBC.java            # Database connection
│   ├── FileUtils.java              # JSON file handling
│── target/
│   ├── atm-simulator-1.0-SNAPSHOT.jar   # Maven-built JAR file
│── localStorage.json               # Temporary JSON storage
│── pom.xml                         # Maven build file
│── Dockerfile                      # Docker configuration
│── README.md                       # Project documentation


📝 Additional Information
Maven Configuration (pom.xml)
The project uses Maven for dependency management. Below is the example pom.xml for resolving necessary dependencies like JDBC, Gson, and the required Maven plugins for building and running the application:

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.atsimulator</groupId>
    <artifactId>atm-simulator</artifactId>
    <version>1.0-SNAPSHOT</version>
    <dependencies>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.23</version>
        </dependency>
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.8.8</version>
        </dependency>
    </dependencies>
</project>


🛡️ Security & Data Integrity
OTP for Card Number Retrieval: Users will receive an OTP on their registered email for card number retrieval.
Balance Inquiry: The balance is calculated based on deposit and withdrawal history stored in the database and is shared with the user via email.
Secure Transactions: All financial transactions, including deposits and withdrawals, are securely handled with email notifications to the user.
🧑‍💻 User Interaction & Navigation
The OTPForCardNumberRetrievalWindow class ensures secure card number retrieval through OTP authentication.
Users can view their Balance via the BalanceEnquiry feature, which calculates the balance in real-time and sends an email with the account status.
MiniStatement allows users to view their transaction history.
