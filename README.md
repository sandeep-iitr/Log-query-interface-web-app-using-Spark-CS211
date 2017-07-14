# Log_Query_interface_CS211
This is course project of CS 211: Log Query interface
Log Query Interface is an interactive web application that allows users to query the very large data logs of MobileInsight easily and efficiently. With this interface, users no longer need to talk to the database through command line queries, nor to install the MobileInsight client locally to fetch data. Users can simply select/type the query message through our web based system which queries the database very efficiently and responds back to user. While testing on 6GB of datasets our system takes less than 1 seconds to respond back, the similar queries on traditional MySql database takes more than 60 seconds. The system gives user the capability to execute all the queries using sql query language. User can perform complex join operations on very large tables. The query response time is hugely improved by the server side Spark clusters, which stores the big datasets in a distributed system and execute the query in parallel on multiple 

## Team Members
1) [Sandeep Singh Sandha](https://sites.google.com/view/sandeep-/home)
2) Xin Xu
3) Yue Xin
4) Zhehan Li

## [Project Report](https://github.com/sandeep-iitr/Log-query-interface-web-app-using-Spark-CS211/blob/master/Spark_Log_query.pdf)

## [Project Presentation](https://github.com/sandeep-iitr/Log-query-interface-web-app-using-Spark-CS211)

## Architecture

![System Design](https://github.com/sandeep-iitr/Log-query-interface-web-app-using-Spark-CS211/blob/master/Arch.png)

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

### Related work
We apply Spark Apache, which is a fast and general engine for large-scale data processing, to store and process our huge amount of log data. Actually Spark is used at a wide range of organizations to process large datasets.
The teams from Bing, Microsoft need to monitor and analyze user engagement, act upon revenue opportunities in markets around the world. So they use Apache Spark Streaming to collect logs and signals associated every single search query, process and enrich the data in near real-time.
AirStream is a realtime stream computation framework built on top of Spark Streaming and Spark SQL. It allows engineers and data scientists at Airbnb to easily leverage Spark Streaming and SQL to get real-time insights and to build real-time feedback loops. There have been a few production use cases such as real-time ingestion pipelines for data warehouse, and computing derived data for online data products. 
Baidu’s deep learning technology uses Spark to drive deep learning training and prediction using Paddle, the deep learning library developed by Baidu IDL. This enables multiple Baidu’s production offline processing to do data ingestion, preprocessing, feature extraction and model training in one Spark cluster. they also address the resource heterogeneity to support multi-tenancy using Spark.
Spark Streaming solves the real-time data processing problem, but to build large scale data pipeline we need to combine it with another tool that addresses data integration challenges. Thus the Apache Kafka project[20] introduced a new tool, Kafka Connect, to make data import/export to and from Kafka easier and bridge the gap between other data systems and stream processing framework.
The teams from Trifacta built a new engine that casts data profiling as an OLAP problem and leverages Spark to quickly generate query results. Its low latency enables ‘pay-as-you-go’ profiling, empowering users to explore their data iteratively, summarizing columns only as needed and executing focused drill-down queries too expensive to apply broadly. We can see 10x-100x speedups with Spark and faster still in pay-as-you go cases.
Spark Apache is also widely applied in research areas to help solve problems. 
VAST Research lab from UCLA presented Blaze, an accelerator-aware runtime system that enables rapid warehouse-scale accelerator deployment for the Hadoop/Spark ecosystem. Blaze provides accelerator management for data-intensive scalable computing (DISC) systems in a cluster with heterogeneous accelerator platforms, including GPUs and FPGAs.
The Scientific Computing team at the University of Washington’s Institute for Health Metrics and Evaluation is extending the landmark Global Burden of Disease study to forecast what the future of the world’s health might look like under a variety of scenarios. In order to do so, they created massively detailed simulations of the world, using Spark to distribute a workload that can produce up to a petabyte of outputs per run.



### Conclusion
We presented the design, development and testing of Log Query Interface which is web based system to query very large datasets. Log Query Interface allows users to query the large dataset easily and efficiently. From our final results we saw that the query time can be improved by factor of 100 time using simple machine to form a cluster than using one machine. The main factor driving the development was to process the queries in realtime. We also compared and tested the system using in-memory processing and without using in-memory processing. In our testbed, the in-memory processing improved the performance by 10 times. 


