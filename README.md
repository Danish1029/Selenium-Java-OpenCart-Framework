# OpenCart Automation Framework

## Project Overview

This project is a Hybrid Selenium Automation Framework developed using Java for automating the OpenCart e-commerce application.

The framework is designed using industry-standard automation practices such as Page Object Model (POM), TestNG, Maven, Docker Selenium Grid, Extent Reports, and Allure Reports. It supports both local and remote execution with parallel cross-browser testing.

---

## Technology Stack

* Java
* Selenium WebDriver 4
* TestNG
* Maven
* Page Object Model (POM)
* Docker Selenium Grid
* Extent Reports
* Allure Reports
* Log4j2
* Apache Commons
* Git
* GitHub
* jenkins

---

## Framework Features

* Hybrid Automation Framework
* Page Object Model Design Pattern
* Local and Remote (Docker Grid) Execution
* Cross Browser Testing

  * Chrome
  * Firefox
* Parallel Test Execution
* Retry Failed Test Cases
* Automatic Screenshot Capture on Failure
* Extent HTML Reporting
* Allure Reporting
* Log4j2 Logging
* Maven Build Management
* External Configuration using config.properties
* TestNG Groups (Smoke, Sanity, Regression)
* Data Driven Testing
* Reusable Utility Classes
* Explicit Wait Implementation
* JavaScript Executor Utilities

---

## Project Structure

```
src
 ├── main
 │     └── pageObjects
 │
 ├── test
 │     ├── testCases
 │     ├── testBase
 │     ├── utilities
 │     └── resources
 │
reports
screenshots
logs
test-output
pom.xml
```

---

## Execution

### Local Execution

```
mvn clean test
```

---

### Docker Selenium Grid Execution

1. Start Docker Desktop

2. Start Selenium Hub

```
docker start selenium-hub
```

3. Start Chrome Node

```
docker start chrome-node
```

4. Start Firefox Node

```
docker start firefox-node
```

5. Execute Tests

```
mvn clean test
```

---

## Reporting

After execution:

* Extent Report is generated automatically.
* Allure Report is generated automatically.
* Screenshots are captured for failed test cases.

---

## Current Automation Coverage

* Account Registration
* Login Validation

Additional test cases will be added continuously.

---

## Future Enhancements

* Jenkins CI/CD Integration
* GitHub Actions
* Email Report Integration
* API Automation
* Database Validation
* Performance Testing
* Cloud Execution (BrowserStack / LambdaTest)

---

## Author

Danish

Automation Test Engineer

---

