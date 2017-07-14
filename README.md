# Log_Query_interface_CS211
This is course project of CS 211: Log Query interface
Log Query Interface is an interactive web application that allows users to query the very large data logs of MobileInsight easily and efficiently. With this interface, users no longer need to talk to the database through command line queries, nor to install the MobileInsight client locally to fetch data. Users can simply select/type the query message through our web based system which queries the database very efficiently and responds back to user. While testing on 6GB of datasets our system takes less than 1 seconds to respond back, the similar queries on traditional MySql database takes more than 60 seconds. The system gives user the capability to execute all the queries using sql query language. User can perform complex join operations on very large tables. The query response time is hugely improved by the server side Spark clusters, which stores the big datasets in a distributed system and execute the query in parallel on multiple 

## (Report)[https://github.com/sandeep-iitr/Log-query-interface-web-app-using-Spark-CS211/blob/master/Spark_Log_query.pdf]

## Setting up system:
1) Download eclipse java EE version. <br>
2) Download Apache tomcat 1.7 (it will work with other version, it is preffered to use this. Other version may give conflict)<br>
3) Download repository using git clone, and import the project into eclipse. Use file->import->exisiting project ...<br>
4) To run the project on mysql you need to set up the Mysql database.<br>
5) Mysql database, in the project I have used username and password "root". If you have any other password, update it in the Database_connector class.<br>

## About Spark:
1) If you want to connect to spark, change the "Run_Spark" into true (a private static final variable in Answer_query_servlet). <br>
2) Default spark server IP: localhost:4567. <br> 

## System Components

To make all users’ access to the data convenient and to deal with the large-data storage and process, we present our work, a web based query application to provide a friendly user interface and accelerate the query speed into milliseconds.

### Web based front end
Web based frontend. When users type in the SQL command (e.g. “SELECT * FROM tMsg”), the JQuery module will handle this message and send the request back to the core service server. This is an essential part for accessing the data conveniently and efficiently. Obviously we don’t want the users to interact with the remote server database directly. It is really troublesome to type commands in the terminal to connect the server and input raw SQL statements to do queries on database. These need users to be familiar with SSH and SQL queries. With our friendly front-end, the users can just enter what they want in the website and will get the results shown in good formats.

### Core service server
 We use java for our core service server development. When the query request from web based frontend arrives, the core service server should be able to check the format of the query message, and send valid query messages to MySQL server for  further queries. So far, we can make the access to data conveniently, but still not efficiently. Thus we introduce the Spark server part. When the java server receives the query from the front-end, it forwards the query to the Spark server to run it on Spark cluster. More specifically, the Spark master runs a Spark-shell and will continuously accept the queries. Then it assigns the tasks to all of its slave nodes.

### Spark server
Spark server is our project’s core part. It improves our project’s performance a lot. Firstly We deploy our Spark clusters using standalone – a simple cluster manager included with Spark that makes it easy to set up a cluster. When the query request arrives java server for the first time, the java server creates a process to run an interactive Spark shell against the cluster. This Spark shell will be always open and thus the job environment is held. For all the queries coming later, the java server just runs these sql queries on the existing Spark shell process, using Spark sql. Through this interactive Spark shell, we successfully assign all of the queries to the clusters for executing. Once the query result is generated, it will be returned to java server and the java server will  parse and display it on the frontend.

### Implementation
We implemented our log query system based on three modes: MySql, Spark on one machine and Spark on a cluster. And we tested their performance separately by running some queries on tFile and tMsg dataset. The tests show that the system running with Spark cluster can make the data querying more than 1000 times faster than running with MySQL and Spark on one machine.

