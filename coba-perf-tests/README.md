## CoBA Gatling Perf Test

Gatling is a load testing tool for web applications, REST API, designed for DevOps and Continuous Integration.

This project contains the reusable test component which can be used to test the load testing of web application or REST
API.

## Built With

We need following tools/technologies to use all the above features.

* [Gatling](https://gatling.io/)
* [Maven](https://maven.apache.org/)
* [Gradle Maven Plugin](https://gatling.io/docs/current/extensions/maven_plugin/)
* [Scala](https://www.scala-lang.org/)

<!-- GETTING STARTED -->

## Getting Started

### Prerequisites

1. Install Scala Plugin on IntelliJ
2. For IntelliJ to detect Scala, Goto 'File' >> 'Project Structure' >> Global Library Settings and select Scala, download and set to 'Scala 2.13.10'
3. Install Maven and set path
4. Install JDK 1.7 and set path

### Installation

1. Clone the repo
   ```sh
   git clone https://lbg-cloudfirst@dev.azure.com/lbg-cloudfirst/coba/_git/coba-perf-tests
   ```
2. Build project
   ```sh
   mvn clean compile
   ```
3. Run the test
   ```sh
   mvn gatling:test -Denv=local.properties -Dusers=100 -Dduration=5 -Dgatling.includes='tests.*'
   ```
   Example
4. mvn gatling:test "-Denv=local.properties" -Dusers=5 "-Dgatling.includes=tests.PeakSimulation"

By passing the 'users' or 'duration' values in the parameter, this can be used in the Simulation injection setup
through as an example:
scn2.inject(rampUsers(users).during(30.seconds))
maxDuration(duration)

<!-- USAGE EXAMPLES -->

## Usage

This project is specifically designed to perform load testing for web applications and REST API's:

1. Perform load test for given application.
2. Simulation to start the load testing

#### Gatling HTML Reports

A more detailed test result in HTML can be found in target/results as below

<!-- Indicators -->

![Gatling Report](https://lion.box.com/shared/static/vt7do5opxro6zxywnv96pdsp0tz5gxz2.png?raw=true)

This chart shows how response times are distributed among standard ranges. The right panel show number of OK/KO
requests.

![Gatling Report](https://lion.box.com/shared/static/y1mrfj6g02ez60an1nouisg4v3t7tuke.png?raw=true)

The top panel shows some standard statistics such as min, max, average, standard deviation and percentiles globally and
per request. And the bottom panel shows some details on the failed requests.

<!-- Active users over time -->

![Gatling Report](https://lion.box.com/shared/static/8ky97uau01el8rt3bua8x37adt06vdet.png?raw=true)

This chart displays the active users during the simulation : total and per scenario.

<!-- Response time distribution -->

![Gatling Report](https://lion.box.com/shared/static/u6b9893r8sgedbiqqh9ks402sve26z7s.png?raw=true)

This chart displays the distribution of the response times.

<!-- Response time percentiles over time -->

![Gatling Report](https://lion.box.com/shared/static/bcjhnzruwo7ale4cvve4ygowlx8ugq01.png?raw=true)

This chart displays a variety of response time percentiles over time, but only for successful requests.

<!-- Requests per second over time -->

![Gatling Report](https://lion.box.com/shared/static/zk7jo4ysoqs7888yxl6e69o6or395p3o.png?raw=true)

This chart displays the number of request received per second over time

<!-- Response per second over time -->

![Gatling Report](https://lion.box.com/shared/static/vnq5j0sgy628jyaw3830498a1fp6bd5a.png?raw=true)

This chart displays the number of responses received per second over time : total, successes and failures.
