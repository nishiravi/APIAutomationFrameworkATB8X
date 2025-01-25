### AUTHOR-NISHI RAVI
### API AUTOMATION FRAMEWORK WITH THE CURD OF RESTFUL BOOKER
# API Automation RestAssured (in Java)


API Automation Framework with the CRUD of Restful Booker

`mvn test -Dsurefire.suiteXmlFiles=testng.xml`

<img width="1130" alt="Screenshot 2023-10-31 at 12 25 55 PM" src="https://github.com/PramodDutta/APIAutomationRestAssured/assets/1409610/69f398b3-8798-4fba-a091-3b1e321dcc7d">



## Tech Stack

1. Java ( JDK > 21)
2. Rest Assured
3. Apache POI, TestNG, Maven.
4. AssertJ (Advance assertions)
5. Jackson and GSON
6. Log4j
7. Allure Report
8. Full Folder Structure(Hybrid) Framework.
9. Jenkins File

#### API Framework Important Components
![Screenshot 2024-06-29 at 12 44 29](https://github.com/PramodDutta/APIAutomationFramworkATB6x/assets/1409610/98bbc62d-7837-4bdc-900b-b214c675af6d)

#### Running via CI/CD
<img width="1262" alt="Screenshot 2023-10-31 at 12 26 07 PM" src="https://github.com/PramodDutta/APIAutomationRestAssured/assets/1409610/2d58bf82-0ffb-4fcb-a2d9-cf26920fa7b5">


Run

### Basic Create Test
* Install Maven
* add config to run the suite or testng

```<build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>3.3.0</version>
        <configuration>
          <suiteXmlFiles>
            <suiteXmlFile>${suiteXmlFile}</suiteXmlFile>
          </suiteXmlFiles>
        </configuration>
      </plugin>
    </plugins>
  </build>
```
to **pom.xml**

```mvn clean test -DsuiteXmlFile=testng.xml ```


### Parallel Execution

To run tests in parallel, add the parallel attribute to your testng.xml file:

```<suite name="All Test Suite" parallel="methods" thread-count="2">```


### Integration Test (Create BookinG and Create Token , Update and Delete Booking)

``` mvn clean test -DsuiteXmlFile=testng-integration.xml```

### Allure Report Generated.


```allure serve allure-results/```

![image](http://192.168.1.153:64873/index.html#suites/5638de12b2515268ac235c49fc75cf1c/ab3f5007a427fc99/)

