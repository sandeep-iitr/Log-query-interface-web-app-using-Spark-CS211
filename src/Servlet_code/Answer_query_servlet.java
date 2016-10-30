package Servlet_code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.NEW;

import Java_code.Database_interactor;
import Java_code.Spark_Interface;

/**
 * Servlet implementation class Answer_query_servlet
 */
@WebServlet("/Answer_query_servlet")
public class Answer_query_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private static final boolean Run_Spark = true; 
	private static final boolean Run_Spark = false; 
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Answer_query_servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// get parfameters from request : we may use to extract string written
		// by user.
		// code String bookId = request.getParameter("bookId");

		// should check the string extracted here
		/*
		 * if (bookId == null) { // a bookId parameter was not defined for the
		 * request
		 * 
		 * }
		 * 
		 * try { // call the needed functions
		 * 
		 * ... } catch (Exception e) { // handle any exceptions returned ... }
		 */

		// Dynamically create HTML response to send back to browser
		/*
		 * response.setContentType("text/html"); response.setBufferSize(8192);
		 * PrintWriter out = response.getWriter(); out.println( "<html>" +
		 * "<head><title>" + "QueryResults" +"</title></head>");
		 * 
		 * out.println( "<html>" + "<body>" +
		 * "Hi you query is running on Quantum computer.<br> Please be patient."
		 * +"</body>");
		 */

		String sql_string = request.getParameter("sql_string");
		ArrayList<ArrayList<String>> sql_results = new ArrayList<ArrayList<String>>();
		
		if(Run_Spark){
			sql_results = Spark_Interface.ParseJson(Spark_Interface.HTTP_POST("http://localhost:4567/",sql_string));
		}
		else {			
			Database_interactor db = new Database_interactor();
			db.Create_connection();
			sql_results = db.Query_results(sql_string);
		}
		request.setAttribute("sql_results",sql_results); 		
		if (sql_results.size() == 0) {// query not successful
			String errMsg = "Database query not successful";
		}
		if (sql_string.length() > 0)
			request.setAttribute("sql_string_serv", sql_string);

		request.getRequestDispatcher("/index.jsp").forward(request, response);

	}
	
}