package Servlet_code;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
        
		
	}

}
