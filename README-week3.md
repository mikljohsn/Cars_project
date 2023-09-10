## Where and why you have used a @OneToMany annotation
The @OneToMany annotation describes a relationship where one table can have multiple instances of the other table. E.g.
in the Member.java entity class in line 31, where it is describes at one member can have many reservations
## Where and why you have used a @ManyToOne annotation
The @ManyToOne annotation describes the relationship where one entity can have multiple instances of the other. E.g.
going back to the example shown earlier, the corresponding Reservation.java class has a @ManyToOne annotation in line 23
of the member type, describing the relationship as reservations having one singular member.
## The purpose of the CascadeType, FetchType and mappedBy attributes you can use with one-to-many
The CascadeType attribute, cascades changes from one table to the other table, if the parent table is deleted, the child will also be deleted.
The FetchType describes how data is fetched, either by LAZY or EAGER, where EAGER pulls all data immediately and LAZY pulls data
when necessary.
The mappedBy attribute describes in our code to which entity the relationship is mapped. 
## How/where you have (if done) added user defined queries to you repositories
In line 11 of CarRepository.java there is a query to find all given cars of a certain brand and model.
## A few words, explaining what you had to do on your Azure App Service in order to make your Spring Boot App connect to your Azure MySqlDatabase
In the 'Configuration' tab and under i the 'Application settings' I had to set the database URL and login credentials for the MySQL database.
## A few words about where you have used inheritance in your project, and how it's reflected in your database
In this project inheritance is used for the UserWithRoles and Member class in the Single Table strategy, where on table is created
for the class hierarchy.
## What are the pros & cons of using the Single Table Strategy for inheritance?
The pros of using Single Table are e.g. simplicity and performance. A single table is a simple overview of your data and 
because only a single table is created and invoked it is more performance light.
A con of using Single Table can for example be data integrity. If the different subclasses have different fields or requirements
you can be forced to use null values where you do not want the field to be nullable.
## How are passwords stored in the database with the changes suggested in part-6 of the exercise
The passwords in the database are hashed and salted where the hashing creates a string of characters which are stored instead
of the password in plain text and the salt is a string of random characters unique to the user, which are added to the password before hashing it
to make sure the data integrity of the passwords of different users in the database,.
