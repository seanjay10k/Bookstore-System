# Bookstore-System
Project Title 
Bookstore System. 

Bookstore can manage their book inventory through CRUD operations on database. Customer can query the database 
for available books, and make a purchase. Every purchase raises the customer's invoice. In any case of exceptions,
database is rolledback protecting data integrity. 

Layers of application:
. Data Access Objects 
. Services
. Domain
. Aspect- PerformanceTiming Advice
. Integration Testing


Note:
The project could be made of production quality if all the unneccesary files are simply deleted and comments are cleaned up.
This project is a great resource for understanding different concepts and techniques in Spring. Since it is implemented entirely using interfaces, and resources are injected or wired through xml to avoid coupling, it is perfectly scalable project as well. There are 4 different versions of xml configuration file for either using hard-wired beans, autowired configuration, annotated beans with JPA and annotated beans with hibernate. Wherever needed it is heavily commented to clarify the concepts and usage of certain Spring tools. All neccesary jar files are included in lib folder.




