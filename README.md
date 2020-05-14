# discount-web-service

## Getting Started

To clone repository:

```bash
git clone https://github.com/mohyassine/discount-web-service.git
```

## Introduction
A simple Spring Boot app that exposes one REST API. The endpoint  returns a discount on bills based on the following criteria:
1. If the user is an employee of the store, he gets a 30% discount
2. If the user is an affiliate of the store, he gets a 10% discount
3. If the user has been a customer for over 2 years, he gets a 5% discount.
4. For every $100 on the bill, there would be a $ 5 discount (e.g. for $ 990, you get $ 45
as a discount).
5. The percentage based discounts do not apply on groceries.
6. A user can get only one of the percentage based discounts on a bill.

---

## Frameworks, Tools, and Libraries
Here is a list of frameworks, tools, and libraries used in this exercise

* Spring MVC
* Spring Boot
* Maven
* H2 DB - In memory DB
* JUnit
* MockMvc
* JaCoCo
* SonarQube
* Sketch it Plugin
* PlantUML
* Graphviz

---

## Maven Scripts
The projects includes a Maven wrapper inside .mvn folder. After checking out the repository, you can use maven to build, run, test, generate coverage reports...etc.
Note that, if you're using Windows

### Build
To build the project, run the following command from the root directory of the project.

```shell script
./mvnw clean compile
```
### Run the Server
Running the project will start a REST service on port 8080 and expose URL _'/api/bill-discount'_

```shell script
./mvnw spring-boot:run
```


### Test
The project uses JUnit with MockMvc for web layer unit testing. To run the tests:

```shell script
./mvnw test
```

### Code Coverage
To generate code coverage reports, use the following command, which will generate a readable report using JaCoCo Maven plugin. 
The command will generate an HTML report under `./target/site/jacoco`.

```shell script
./mvnw jacoco:report
```

**Note that you must run the Maven test script before running the JaCoCo report generation command.**

---

## Usage
After running the project, you can use the discount service through any HTTP client. Here is the end point documentation:

### End Points
|HTTP Method|Route                                               |Description                                      |
|:----------|:---------------------------------------------------|:----------------------------------------------- |
|`GET`      |`/api/customers/`                                   |Gets a list of all customers in DB                                    |
|`POST`      |`/api/cusomters`                                  |Adds a customer to DB     |
|`GET`      |`/api/customers/count`                             |Number of customers     |
|`GET`      |`/api/products`                                    |Gets a list of all products in DB     |
|`POST`      |`/api/products`                                   |Adds a customer to DB     |
|`GET`      |`/api/transactions`                                |Gets a list of all transactions in DB     |
|`POST`      |`/api/transactions`                               |Adds a transaction to DB     |
|`GET`      |`/api/transactions/{transactionId}/customer/{customerId}`      |Calculates the discount given a customer and transaction id     |


#### GET `/api/transactions/{transactionId}/customer/{customerId}``

* HTTP Method: **POST**
* Endpoint: `localhost:8080/api/transactions/{transactionId}/customer/{customerId}`
* Accept: \*/\*
* Content-Type: application/json
* Sample Request URL `localhost:8080/api/transactions/1/customer/4`
* Sample Response Body 
    ```json
    {
      "totalValue": 1690.0,
      "discountValue": 297.0,
      "netPayable": 1393.0
    }
    ```
---

## UML Diagram
The following is the UML diagram showing the relationship between different classes in the project.

![UML Diagram](/assets/uml_diagram.png 'UML Diagram')

---

## Code Coverage Report
After the running the Maven JaCoCo plugin command above, an HTML report is generated. Here are the coverage results for my code:

![](/assets/code_coverage_all_packages.png 'JaCoCo')

Here is the coverage for the models package:

![](/assets/code_coverage_models_package.png 'JaCoCo')

---

## SonarQube Report
The code quality was analyzed using SonarQube. Here are some screenshots for the code analysis results of my project:

![](/assets/sonar_report_details.png 'SonarQube Report Details')

![](/assets/sonar_report_summary.png 'SonarQube Report Summary')

To analyze the code:
* Download and extract SonarQube from [here](https://www.sonarqube.org/downloads/).
* From a Terminal (or Command Prompt on Windows), browse to `[SONAR_INSTALLATION_FOLDER]/bin/macosx-universal-64/` using the following command (replace `macosx-universal-64` with the folder corresponding to your machine's OS):
```shell script
./sonar.sh console  
```
* using your browser, open Sonar through the URL `http://localhost:9000/`, then login with admin/admin
* Click on create new project, generate a key for the project.
* In the project, enable Sonar on Maven by adding the following to pom.xml:
```xml
<pluginManagement>
    <plugins>
        <plugin>
            <groupId>org.sonarsource.scanner.maven</groupId>
            <artifactId>sonar-maven-plugin</artifactId>
            <version>3.4.0.905</version>
        </plugin>
    </plugins>
</pluginManagement>
```
* Run the test through maven as mentioned above, then run the following command to analyze the code:
```shell script
./mvnw sonar:sonar -Dsonar.projectKey=Discount-Service -Dsonar.host.url=http://localhost:9000 -Dsonar.login=[GENERATED_CODE]
```
* The code analysis report will be available in the browser.