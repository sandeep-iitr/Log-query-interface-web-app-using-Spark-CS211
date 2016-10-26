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
	
    
    public ArrayList<ArrayList<String>> Query_results(String sql_string)
    {
    	// Execute SQL query
        Statement stmt;

        ArrayList<ArrayList<String>> Results = new ArrayList();
        
		try {
			if(conn!=null){
				stmt = conn.createStatement();
				String sql;
			 
				 sql = "SELECT * FROM tMsg LIMIT 10";
				 
				 if(sql_string.length()>0)
					sql = sql_string;
			       
			        ResultSet rs = stmt.executeQuery(sql);
			        ResultSetMetaData rsmd = rs.getMetaData();
			        
			        int colNums = rsmd.getColumnCount();
			        ArrayList<String> fieldNames = new ArrayList<String>();
			        for(int i=1; i<=colNums; i++){
			        	fieldNames.add(rsmd.getColumnName(i));
			        }
			        System.out.println(fieldNames);
			        Results.add(fieldNames);
			        
			        // Extract data from result set
			        while(rs.next()){
			           //Retrieve by column name
			        	ArrayList<String> txn = new ArrayList<String>();
			        	for(int i=1; i<=colNums; i++){
			        		String colName = rsmd.getColumnName(i);
			        		String colData = rs.getString(colName);
			        		txn.add(colData);
			        	}
			        	Results.add(txn);
			        }
			        System.out.println(Results);
				}//end if
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       
			return Results;
    }
    
}//end of class
