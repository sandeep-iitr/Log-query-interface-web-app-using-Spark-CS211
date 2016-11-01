<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="Servlet_code.Answer_query_servlet" language="java" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Log Query</title>

        <!-- Bootstrap Core CSS -->
        <link href="https://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css" rel="stylesheet">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		
        <!-- Fonts from Font Awesome and Google Fonts-->
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
        <link href='https://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>

        <!-- JQuery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>

        <!-- Bootstrap Core JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link rel="stylesheet" type="text/css" href="assets/css/base.css">
    </head>
	<body>
		<nav class="navbar navbar-default" style="position:fixed; top:0; width:100%">
		  <div class="container-fluid">
		    <!-- Brand and toggle get grouped for better mobile display -->
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		      <a class="navbar-brand" href="#">Log Query</a>
		    </div>
		
		    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav navbar-nav">
		        <li class="active" ><a href="#">Homepage</a></li>
		      </ul>
		      <form class="navbar-form navbar-left">
		        <div class="form-group">
		          <input type="text" class="form-control" placeholder="Search">
		        </div>
		        <button type="submit" class="btn btn-default">Submit</button>
		      </form>
		    </div><!-- /.navbar-collapse -->
		  </div><!-- /.container-fluid -->
		</nav>
	
		<br>
		<br>
		<br>
		<br>
		<h3>Log Query</h3>
		<br>
		<form method="post" action="Answer_query_servlet" class="form-group"> 
			<label>Select template query</label>
			<select class="form-control" id="sampleQuery" onchange="updateQuery('sampleQuery')">
				<option value="">---Select Sample Query---</option>
				<option value="SELECT * FROM tFile;">SELECT * FROM tFile;</option>
				<option value="SELECT * FROM tMsg;">SELECT * FROM tMsg;</option>
				<option value="SELECT Filepath,Phone,Carrier,Timestamp FROM tFile;">SELECT Filepath, Phone, Carrier, Timestamp FROM tFile;</option>
				<option value="SELECT Filepath,Timestamp,MsgType,MsgHash,MsgPath,LineNo FROM tMsg;">SELECT Filepath,Timestamp,MsgType,MsgHash,MsgPath,LineNo FROM tMsg;</option>
				<option value="SELECT * FROM tFile LIMIT 10;">SELECT * FROM tFile LIMIT 10;</option>
				<option value="SELECT * FROM tMsg LIMIT 10;">SELECT * FROM tMsg LIMIT 10;</option>
				<option value="SELECT Phone,Carrier FROM tFile LIMIT 10;">SELECT Phone,Carrier FROM tFile LIMIT 10;</option>
			</select>
			<br>
			<label>Customize query message</label>
			<textarea class="form-control" name="sql_string" rows="6" id="queryMsg"></textarea> 
			 <br>
			 <br>
			 <button type="submit" name="button" value="button1" class="btn btn-primary">Submit Query</button>
			 <hr>
			 <br>
		</form>
		
		<script type="text/javascript">
			function updateQuery(id){
				document.getElementById("queryMsg").value = document.getElementById(id).value;
			}
		
		</script>
		
		
		<%
			ArrayList<ArrayList<String>> queryRst = (ArrayList<ArrayList<String>>) request.getAttribute("sql_results");
			if(queryRst==null)
				out.println("<br>");
			else{
				String sql_string_serv = (String)request.getAttribute("sql_string_serv");
				
				out.println("<div class=\"panel panel-default\" style=\"width:80%; margin-left: auto; margin-right:auto; margin-bottom:200px\">\n" + 
								"<div class=\"panel-heading\"><h4 class=\"panel-title\">Query Result</h4></div>" + 
								"<div class=\"panel-body\"><p>Result for query message\"" + sql_string_serv +"\"</p></div>\n" + "<br>" +
								"<table class=\"table table-striped table-hover\" style=\"table-layout:fixed; width:100%\">\n" +
									"<thead class=\"thead-default\">\n"
										+ "<tr>\n");
							ArrayList<String> fieldNames = queryRst.get(0);
							for(int i=0; i<fieldNames.size(); i++)
								out.println("<th class=\"\">"+ fieldNames.get(i)+"</th>"); 
							out.println("</tr></thead>\n"); /* end of table head */
							out.println("<tbody>\n");
							/*table content*/
							for(int i=1; i<queryRst.size(); i++){
								out.println("<tr>");
								for(int j=0; j<queryRst.get(i).size(); j++){
									out.println("<td style=\"word-wrap:break-word;\">" + queryRst.get(i).get(j) +"</td>");
								}
								out.println("</tr>");
							}
							out.println("</tbody></table></div>");
						}
					%>
		
		<div id="footer">
	        <p>Contributed by Ariel, Sandeep, Jacky, Xin @UCLA CS211</p>
	    </div>
	</body>
</html>