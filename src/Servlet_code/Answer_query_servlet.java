package Servlet_code;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Java_code.Database_interactor;

/**
 * Servlet implementation class Answer_query_servlet
 */
@WebServlet("/Answer_query_servlet")
public class Answer_query_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Answer_query_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		
		// get parfameters from request : we may use to extract string written by user.
		//code String bookId = request.getParameter("bookId");
		
		//should check the string extracted here
		/*
		  if (bookId == null) { // a bookId parameter was not defined for the request
			
			}

			try {
			 // call the needed functions
			
			...
			}
			catch (Exception e) { // handle any exceptions returned
			...
			}
		*/
		
		
		// Dynamically create HTML response to send back to browser
		/*
		response.setContentType("text/html");
		response.setBufferSize(8192);
		PrintWriter out = response.getWriter();
		out.println( "<html>" + "<head><title>" + "QueryResults" +"</title></head>");
		
		out.println( "<html>" + "<body>" + "Hi you query is running on Quantum computer.<br> Please be patient." +"</body>");
		*/
		
		// old implement without spark //
		/*
		String sql_string = request.getParameter("sql_string");
		
		
		Database_interactor db = new Database_interactor();
		db.Create_connection();
		
		ArrayList <String>sql_results=db.Query_results(sql_string);
		
		if(sql_results.size()==0)//query not successful
			sql_results.add("Database query not successful");
		
		request.setAttribute("sql_results",sql_results); 
		
		if(sql_string.length()>0)
			request.setAttribute("sql_string_serv",sql_string);
		
        request.getRequestDispatcher("/QueryResults.jsp").forward(request, response);    
        */
		
		// new implement with spark server involved
//		 System.out.println(this.getClass().getResource("/"));
		 
//		Properties properties = new Properties();
//		try {
//			properties.load(new FileInputStream("/home/spark/Eclipse_Workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/Log_Query_Interface/WEB-INF/classes/s.properties"));
//			properties.load(new FileInputStream(this.getClass().getResource("/")+"s.properties"));
//		} catch (IOException e1) {e1.printStackTrace();}
				
	
		String sql_string = request.getParameter("sql_string");
		if(sql_string.length()==0)
			sql_string = "SELECT * FROM tMsg LIMIT 5";		
		
		request.setAttribute("sql_string_serv",sql_string);
//			request.setAttribute("sql_string_serv",HTTP_GET(properties.getProperty("Spark_URL"),sql_string));
		request.setAttribute("sql_results",HTTP_POST("http://localhost:4567/",sql_string)); 
		
		request.getRequestDispatcher("/QueryResults.jsp").forward(request, response);    

		
	}

	
	/*
	 * Sending HTTP request(body) using GET method to Spark server and return the body of response. 
	 */
	private String HTTP_POST(String url, String request) {
		String result="";

		URL httpUrl = null;
		try {
			httpUrl = new URL(url);
		} catch (MalformedURLException e) {e.printStackTrace();}
        //build the connection
        URLConnection connection = null;
		try {
			connection = httpUrl.openConnection();
		} catch (IOException e) {e.printStackTrace();}
        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent", "CS-211@UCLA");
        connection.setRequestProperty("content-type", "application/SQL-lines");  
        connection.setRequestProperty("Content-Lenth",String.valueOf(request.length()));
        connection.setDoOutput(true);
        connection.setDoInput(true);
    	OutputStreamWriter outputStream = null;
		try {
			outputStream = new OutputStreamWriter(connection.getOutputStream());
		} catch (IOException e1) {e1.printStackTrace();}
		try {
			outputStream.write(request);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e1) {e1.printStackTrace();}


//        System.out.println("Before Connected.");
//        try {
//			connection.connect();
//		} catch (IOException e) {e.printStackTrace();}        
////        System.out.println("After Connected.");

        // handle the response.

        BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		} catch (IOException e) {e.printStackTrace();}
        String line;
        try {
			while ((line = bufferedReader.readLine()) != null) result += line;
		} catch (IOException e) {e.printStackTrace();}
        try {
			bufferedReader.close();
		} catch (IOException e) {e.printStackTrace();}
//        System.out.println("There should be an result.");
        System.out.println(result);
		return result;
		
	}
	
	
	
	
	
}
