# OpenCart Automation Framework

## Project Overview

This project is a Hybrid Test Automation Framework developed using **Java**, **Selenium WebDriver 4**, and **TestNG** for automating the OpenCart E-Commerce application.

The framework follows enterprise-level automation standards using the **Page Object Model (POM)** design pattern and is built with scalability, maintainability, reusability, and parallel execution in mind.

It supports Local and Docker Selenium Grid execution, detailed reporting, cross-browser testing, data-driven testing, retry mechanism, logging, and CI/CD integration.

---

# Technology Stack

- Java 21
- Selenium WebDriver 4
- TestNG
- Maven
- Page Object Model (POM)
- Docker Selenium Grid
- Extent Reports
- Allure Reports
- Log4j2
- Apache POI (Excel)
- Apache Commons IO
- Apache Commons Lang
- Git
- GitHub
- Jenkins
- WebDriverManager

---

# Framework Features

### Framework Architecture

- Hybrid Automation Framework
- Page Object Model (POM)
- Object Repository using Page Factory
- Reusable Base Page
- Reusable Base Test
- Thread Safe Framework using ThreadLocal
- Modular & Scalable Design
- Clean Code Architecture

---

### Browser Execution

- Local Execution
- Docker Selenium Grid Execution
- Cross Browser Testing
  - Chrome
  - Firefox
  - Edge (Framework Ready)
- Parallel Test Execution
- Browser Selection using TestNG Parameters

---

### Test Execution

- Smoke Testing
- Sanity Testing
- Regression Testing
- TestNG Groups
- Retry Failed Test Cases
- Priority Based Execution
- Suite Based Execution
- XML Driven Test Execution

---

### Synchronization

- Explicit Waits
- Custom Wait Utilities
- Element Visibility Wait
- Clickable Wait
- Page Load Wait
- JavaScript Ready State Validation

---

### Reporting

- Extent HTML Reports
- Allure Reports
- TestNG Reports
- Automatic Report Generation
- Automatic Screenshot Capture on Failure
- Timestamp Based Reports

---

### Logging

- Log4j2 Logging
- Console Logging
- File Logging
- Detailed Execution Logs

---

### Data Driven Testing

- Excel Driven Test Data
- Apache POI
- Reusable Data Providers
- External Test Data Support

---

### Configuration Management

- External Configuration using config.properties
- Environment Configuration
- Browser Configuration
- URL Configuration

---

### Utilities

- Screenshot Utility
- Retry Analyzer
- Retry Listener
- JavaScript Utility
- Excel Utility
- Common Utility Methods

---

### Build & Dependency Management

- Maven Build Management
- Dependency Management
- Surefire Plugin
- Clean Project Structure

---

### Version Control

- Git
- GitHub
- Branch Ready Structure

---

### CI/CD Ready

- Jenkins Integration
- Parameterized Build Ready
- Scheduled Execution Ready

---

# Project Structure

```
OpenCartAutomationFramework
│
├── src
│   ├── main
│   │   ├── pageObjects
│   │   └── utilities
│   │
│   └── test
│       ├── testBase
│       ├── testCases
│       ├── listeners
│       ├── utilities
│       ├── resources
│       └── testData
│
├── reports
├── screenshots
├── logs
├── test-output
├── allure-results
├── allure-report
├── pom.xml
├── testng.xml
└── README.md
```

---

# Current Automated Test Scenarios

### Account Module

- User Registration
- Registration Validation

### Login Module

- Valid Login
- Invalid Login Validation

More modules and end-to-end scenarios will be added continuously.

---

# Test Execution

## Local Execution

```bash
mvn clean test
```

---

## Execute Specific TestNG Suite

```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

---

## Docker Selenium Grid Execution

### Start Docker Desktop

Start Docker Desktop before execution.

### Start Selenium Hub

```bash
docker start selenium-hub
```

### Start Chrome Node

```bash
docker start chrome-node
```

### Start Firefox Node

```bash
docker start firefox-node
```

### Execute Tests

```bash
mvn clean test
```

---

# Reporting

After execution, the framework automatically generates:

- Extent HTML Report
- Allure Report
- TestNG Report
- Log File
- Failure Screenshots

---

# Framework Highlights

- Enterprise Level Automation Framework
- Thread Safe Parallel Execution
- Reusable Components
- Easy Maintenance
- Highly Scalable Architecture
- CI/CD Ready
- Docker Ready
- Interview Ready Project

---

# Future Enhancements

- Jenkins Pipeline
- GitHub Actions CI
- Email Report Integration
- REST Assured API Automation
- Database Validation
- BrowserStack Integration
- LambdaTest Integration
- AI Assisted Test Execution
- Performance Testing (JMeter)
- Mobile Automation (Appium)

---
README.md
LICENSE
.gitignore
pom.xml

docs/
    FrameworkArchitecture.png
    ExecutionFlow.png
    DockerSetup.md

reports/        (ignored)
logs/           (ignored)

screenshots/
    HomePage.png
    ExtentReport.png
    AllureReport.png
# Author

**Danish**

Senior Test Engineer

Automation Test Engineer

Java | Selenium | TestNG | Maven | Docker | Jenkins | REST Assured | Appium

---
