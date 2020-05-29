# BigData/ML, Microservice, Financial, Fraud Detection
![microservice image][microservice-image]
### Functionality
  * Uploading **millions of financial data points** to DB while running **ML fraud analysis**. 
  * Calls a sister [**_Python microservice API_**][project-link] to run the fraud detection.
  * Uploads financial transactions from CSV, performs fraud detection on transactions, uploads to MySql DB with Spring Batch
  * Each microservice recieves its config files from a Springboot config server linked to a Github repository
  * Accessing microservices through a Discovery Server- Eureka
  * Uses Spring Security with JDBC Authentication
### Technology
#### **Java**: 
* Springboot, Hystrix, Eureka, Tomcat, Maven, Spring Batch, Github--through config server
### Current Benchmarks
 * Avg Batch Runtime: 9 min 56 seconds—6 million transaction, batch upload only
### Quotes
> “It takes fastidiousness to write code that doesn’t just do the right thing but also says the right thing.” Eric Evans

[project-link]: https://github.com/isaaccolson/microservice-python
[microservice-image]: https://abeyon.com/wp-content/uploads/2019/02/Microservice-1030x399.png
