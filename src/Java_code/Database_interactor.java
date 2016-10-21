package Java_code;
import java.sql.*;  
import java.util.ArrayList;


/*
 * Class provide to do database queries
 * */

public class Database_interactor {

	//To use Mysql we need below details:
	/*
	 * a) JDBC driver  (This is a jar file)
	 * b) Connect function (need MySql db details: name, host, username, password etc)
	 * b) Execute Query functions.
	 * c) Disconnect functions
	 * */

	// JDBC driver name and database URL
    static final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
    static final String DB_URL="jdbc:mysql://localhost/logquery?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    		//"jdbc:mysql://localhost:3306/logquery";
    
//  Database credentials
    static final String USER = "root";
    static final String PASS = "root";
    
    Connection conn;
    
    public Connection Create_connection()
    {
    	if(conn==null)
    	{
    		try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	// Open a connection
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	}

       return conn;
         
    	
    }//end of create connection
	
    
    public ArrayList<String> Query_results()
    {
    	// Execute SQL query
        Statement stmt;

        ArrayList<String> Results = new ArrayList();
        
		try {
			if(conn!=null)
			{
			stmt = conn.createStatement();
			 String sql;
		        sql = "SELECT * FROM tMsg";
		        ResultSet rs = stmt.executeQuery(sql);

		        // Extract data from result set
		        while(rs.next()){
		           //Retrieve by column name
		          
		           String first = rs.getString("Filepath");
		           Results.add(first);
                  
		           
		        }
			}//end if
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    	
		return Results;
    	
    }
    
}//end of class
