# Job management System
A Prototype implementation for Job Management System Case Study

### Case Study 
A simple Job Management Service must be developed. The goal of this system is to handle the
execution of multiple types of Jobs. The actions performed by these Jobs are not important; possible
examples of these Jobs could be performing a data-load into a DWH, performing indexing of some file-based content or sending emails.
Features of this system are:

* Flexibility
<br/> The types of possible actions performed by the Jobs are not known to the Job
Management System. In the future, new Jobs should be supported without re-developing
the Job Management System (optional).
* Reliability
<br/> Each Job should either complete successfully or perform no action at all. (I.e. there should be
no side-effects created by a Job that fails.)
* Internal Consistency
<br/> At any one time a Job has one of four states: QUEUED, RUNNING, SUCCESS, FAILED. Following
the execution of a Job, it should be left in an appropriate state.
* Priority (Optional)
<br/> Each Job can be executed based on its priority relative to other Jobs
Scheduling
A Job can be executed immediately or according to a schedule.

### Project Architecture and Design 

![Project Architecture](./src/main/resources/doc/architecture.png)

The project consists of following part/ tech stack 
<br/> (can be defines in separate modules):
* Job Management Service : spring boot service for register and retrieving jobs information.
    * Job Registration
    * Retrieving job by id
    * Retrieving all jobs
* Job type Definition
    * Retrieving job types
    * Add new job type(s) [can declare in class path]
* Job Executor
   * load and execute a job
* Job priority Scheduler
    * use ThreadPoolTaskScheduler, PriorityBlockingQueue and GuavaEventBus to providing a simple prior producer/consumer system
     
### Improvements and Enhancements
* Basic:
    * Class path jar file job that implemented JobTypeDefinition interface can loaded in job-types 
    for support add new job without re develop The Job Management System.
        * pre requirement : create separate  maven module  for JobTypeDefinition
    * provide more unit test and component test for achieve more code coverage and better maintainability.
    * replace in-memory databases with a no sql databases such as MongoDB for persistent/consistency and better performance.
    * using Cache system for caching job types 
* Features (suggestion):
    * Implementing Authentication/ Authorization for job registration / report or define new job type
    * Provide an api to declare and register new job type definition 
* Producer/Consumer provider :
    * Using a Messing Queue Broker such as Kafka or RabbitMq instead of Guava EventBus/PriorityBlockingQueue depends
 on maintenance , performance (clustering) or decoupling need issues
* Microservices:
    * Create on Microservices Architecture: so separate microservices between priority job queue consumer and producer to
 providing better performance scaling and resilience services
    * using Docker as container for each micro services
    * Depends our needs to Container Orchestration and Clustering or Load balancing and ect , can deploy containers in Docker Swarm or kubernetes 
* Monitoring and Log
    * using Elastic search  & Application Performance Monitoring (APM) for better monitoring performance.
<br/> also can use file-beat(a Lightweight shipper for logs) to gathering and analyzing microservices logs 


### Build / Run
This spring boot project can run with both docker or directly compile with maven and run it.
Anyway, you can build and run it with any IDE.

#### Docker
 * **Docker Image Build** : ```docker build -t job_mng_image .```
 * **Docker Run** : ```docker run -p 9090:9090 job_mng_image```
 * **Test Via Swagger** http://localhost:9090/swagger-ui.html
 
#### Maven     
 * **Build** : ```./mvnw clean package``` | ```mvnw.cmd clean package```
 * **Run** : ```java -jar target\job-management-0.0.1-SNAPSHOT.jar```
 * **Test Via Swagger** http://localhost:9090/swagger-ui.html