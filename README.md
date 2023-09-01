## The idea with, and reasons for why to use, an ORM-mapper

Using the ORM-mapper enables you to map your source code to the corresponding tables
in your database and exchange data between them.

## The meaning of the terms JPA, Hibernate and Spring Data JPA and how they are connected

JPA is a Java specification that defines a set of standards for object relational mapping.
Hibernate is an implementation of the JPA, that builds upon the capabilities.
JPA build for the Spring framework.

## How to create simple Java entities and map them to a database via the Spring Data API

To create a simple Java entity you create a target Class in the 'entity' package of your project
an use the annotation @Entity to map it to the SQL database.

## How to control the mapping between individual fields in an Entity class and their matching columns in the database

You can further specify constraints and information and control the mapping using other annotations
such as @Table and @Column. E.g. having the class 'Car', you can specify the table name in the database
using @Table(name = "Bil") and manipulate the column values using @Column.

## How to auto generate IDs, and how to ensure we are using  a specific database's preferred way of doing it (Auto Increment in our case for  MySQL)

To auto generate ID's in the database you will have to use the annotation @Id with your id field and then
the @GeneratedValue annotation. For MySQL you will have to use the annotation
@GeneratedValue(strategy = GenerationType._IDENTITY_)

## How to use and define repositories and relevant query methods using Spring Data JPAs repository pattern

To define a repository, create an interface in your repository package and have it extend the 'JpaRepository'
interface. It provides CRUD methods such as .saveAll, .count and .deleteAll.

## How to write simple "integration" tests, using H2 as a mock-database instead of MySQL

In your project add the 'H2 database' as a dependency which will be used as an in memory database
annotated with @DataJpaTest in the test class which ensures the use of the H2 and safeguards against
changes in the database.

## How to add (dev) connection details for you local MySQL database

To add connection details for the project you will have to add the connection details such as database URL
username, password in the 'application.properties' file. These can also be added as environment variables
to ensure the protection of login credentials.