## What are the benefits of using a RESTful API
Using a RESTful API ensure that the way we use the API has a uniform and consistent interface.
RESTful API's are also always stateless, where the request from the client always contains all the information
and do not rely on server side state, probably using a JSON format.
## What is JSON, and why does JSON fit so well with REST?
JSON, JavaScript Object Notation, is a data format, that are used to transfer data between servers and web applications.
JSON are represented as strings, which makes them language independent. The language independent nature of this and the 
readability makes it fit very well with REST.
## How you have designed simple CRUD endpoints using spring boot and DTOs to separate api from data  -> Focus on your use of DTO's
Data Transfer Objects are used to pass information from the client to and between the different layers of the application.
This ensures that there is a clean separation between the layers and that we do not pass data that we have no use for,
or do not want to pass. In this program the service layer is used for the business logic, and passing information
specific to that operation.
## What is the advantage of using DTOs to separate api from data structure when designing rest endpoints
Using DTOs makes a clear separation of concerns between layers, makes the code modular and easy to refactor, test or maintain.
As mentioned earlier it also controls data exposure.
## Explain shortly the concept mocking in relation to software testing
Mocking is the making of a 'mock' object, a false object that looks and behaves like the real world one. In this project, so far,
JUnit testing is done with an H2 in-memory database.
## How did you mock database access in your tests, using an in-memory database and/or mockito → Refer to your code
In this project the H2 in-memory database is used. Both the CarServiceH2Test and the MemberServiceH2Test uses the @JpaDataTest annotation
and the setUp method for creating objects in the H2 mock database.
## Explain the concept Build Server and the role Github Actions play here
The build server tests the code before it is deployed and ensures that no faulty code is pushed to the real server.
Using Github Actions, it creates a new build server when code is pushed using Github. If the build is successful the next job
is deploying the project. These actions are specified in the projects .yml file.
## Explain maven, relevant parts in maven, and how maven is used in our CI setup. Explain where maven is used by your GitHub Actions Script(s)
Maven is the build tool we use for our Java projects. The most relevant parts of Maven so far are the test and build commands,
using the Maven wrapper in the terminal on our Java projects. In this CI setup it is used to build the project, which is
visible in the .yml file under jobs: as 'build'.
## Understand and chose cloud service models (IaaS, PaaS, SaaS, DBaaS)for your projects -> Just explain what you have used for this handin
For this hand in we use the DBaaS or database as a service for our Azure MySQL database and PaaS or platform as a service for
our Azure Web App.