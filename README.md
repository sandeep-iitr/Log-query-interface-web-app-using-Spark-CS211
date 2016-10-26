# Log_Query_interface_CS211
This is course project of CS 211: Log Query interface

#Setting up system:
1) Download eclipse java EE version. <br>
2) Download Apache tomcat 1.7 (it will work with other version, it is preffered to use this. Other version may give conflict)<br>
3) Download repository using git clone, and import the project into eclipse. Use file->import->exisiting project ...<br>
4) To run the project on mysql you need to set up the Mysql database.<br>
5) Mysql database, in the project I have used username and password "root". If you have any other password, update it in the Database_connector class.<br>


# Some solutions for potential problems. (Added By Jacky)

1) The project by default using Apache tomcat v8.0 actually, add your own Apache tomcat library to the build path if you are using other vision.<br> 
   1 - Create a server view panel by clicking Window->Show View->Servers)<br>
   2 - Create a new server in the server panel<br>
   3 - Go into Log_Query_Interface->Build Path->Configure Build Path->Libraries->Add Library...->Server Runtime, and choose the runtime server built in step 2 to add to the classpath. <br>
   
2) The project may not use the same JRE version as yours, just use the quick fix function to modify your JRE library path. <br>

3) There may be some warning saying “Target runtime Apache Tomcat v8.0 is not defined”, though you are able to run the server without any problem. Anyway you can open the file within project directory (Log_Query_Interface/.settings/org.eclipse.wst.common.project.facet.core.xml) and delete the line  <runtimename="Apache Tomcat v8.0"/> if you don't want a red cross on your project name. <br>

4) Download your own mySQL JDBC driver version and include it into the Library, it should be like "mysql-connector-java-version-bin.jar".<br>

5) You also need to include the mysql-connector-java-version-bin.jar and slf4j-simple-version.jar(used for log system) into the build path of Spark Server.<br> 

6) Spark server runs on the top of jetty intead of Tomcat, just compile and run the Spark.java, it will automatically download some dependencies for running jetty.<br>

7) By default the jetty server will run at port 4567, you can change it in the spark.properties, and also you should change the URL in Answer_query_servlet as well.(I have tried to make it into the properties file too, but fail) <br>