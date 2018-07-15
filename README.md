# SpringRestServicesWIthRestTemplate

This project is to expose APIs and integrate using Rest Template to get transaction details and filter it based on transaction type from downstream system.

REST APIs

Sr. No.	API Name	Method Type	Description	Authentication Required
1	/getTransactions	GET	Returns all transactions.	YES
2	/getDetailsForTransactionType 	POST	Returns transactions matching requested transaction type.	YES
3	/getAmountForTransactionType 	POST	 Returns total transaction amount for requested transaction type.	YES

Prerequisites

Eclipse : To setup code for development
Maven : To Build and package the code with Junit coverage report
Tomcat 8.0 : To deploy application

Installing

Importing in Eclipse :
Import as existing project
Build using eclipse maven plugin or from command line

Build :
Build code using below maven command

mvn clean package

Deploy :
1.	Start tomcat server 
2.	Select WAR file to upload (Choose WAR  file from target folder in project working directory)  
3.	Deploy
4.	Confirm application deployment from server console
 
5.	Access the RESTAPIs with below path
  a.	http://localhost:<tomcat port>/<application deployment name>/getTransactions
  b.	http://localhost: <tomcat port>/<application deployment name>/getDetailsForTransactionType
  c.	http://localhost:<tomcat port>/<application deployment name>/getAmountForTransactionType
               e.g. http://localhost:8080/contextRoot/getAmountForTransactionType
 
6.	Accessing RESTAPIs : SOAP-UI or POSTman
  a.	Choose basic authentication
  b.	Select username : <See configuration>
  c.	Select password : <See configuration>
  d.	For post methods use below request and type “application/json”-
       {
	      "payload": {
		      "transactionType" : "<payment-type>"
	       }
      }

Code Coverage

After mvn clean package command, open index.html file at below path
<WorkDirectory>/BackBaseOpenBank/BackBaseOpenBankIntegration/target/site/jacoco/index.html
 

