import java.sql.*;
import java.util.ArrayList;



public class DBConnection {
	
	private static DBConnection dbc = null;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/surgery";
	private Connection conn;
	// Database credentials
	static final String USER = "root";
	static final String PASS = "";
	   
	private DBConnection() {
	   Connection conn = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");
	      //STEP 3: Open a connection
	      System.out.println("Connecting to a selected database...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      System.out.println("Connected database successfully...");
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
	 
	}//end of read

    public static DBConnection getInstance(){
		   if(dbc == null)
		   {
			   dbc = new DBConnection();
		   }
		   return dbc;
	 }
   
   @SuppressWarnings("deprecation")
public ArrayList<Doctor> getDoctors(){
	   ArrayList<Doctor>  dList = new ArrayList<>();
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      Class.forName("com.mysql.jdbc.Driver");
	      
	      System.out.println("Connecting to a selected database...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      System.out.println("Connected database successfully...");
	      
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      
	      //create Doctors
	      
	      String sql = "SELECT * FROM doctor;";
	      ResultSet rsd = stmt.executeQuery(sql);

	      while(rsd.next()){
	      Doctor d = new Doctor(rsd.getInt("doctorId"),rsd.getString("doctorName"),rsd.getString("doctorPassword"));	      
	      dList.add(d);
	      }
	      rsd.close();
	      
	      

	      

	   }catch(SQLException se){
	 
	      se.printStackTrace();
	   }catch(Exception e){
	    
	      e.printStackTrace();
	   }finally{
	   
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){
	      }
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
	   }

//////////////////////////////////////////////////////////////////////////  add patients
	   
	   try{
		      Class.forName("com.mysql.jdbc.Driver");
		      
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      System.out.println("Connected database successfully...");
		      
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      
		      //create Patients
		      
		      String sql1 = "SELECT * FROM patient;";
		      ResultSet rsp = stmt.executeQuery(sql1);
		      
		      while(rsp.next()){
		      Patient p = new Patient(
		    		  
		    		  
		    		  
		    		  
		    		  rsp.getInt("pId"),
		    		  rsp.getInt("doctorId"),
		    		  rsp.getString("pName"),
		    		  rsp.getString("pAddress"),
		    		  rsp.getInt("pPhone"),
		    		  new java.util.Date(Integer.parseInt(rsp.getDate("pDOB").toString().substring(0,4)),
		    				  Integer.parseInt(rsp.getDate("pDOB").toString().substring(5,7)),
		    				  Integer.parseInt(rsp.getDate("pDOB").toString().substring(8,10))));
		      System.out.println(rsp.getDate("pDOB").toString().substring(0,4)+" "+
    				  rsp.getDate("pDOB").toString().substring(5,7)+" "+
    				  rsp.getDate("pDOB").toString().substring(8,10));
		      for(Doctor d: dList){
		    	  if (d.getDoctorId() == rsp.getInt("doctorId")){
		    		  d.addPatient(p);
		    	  }
		      }
		      }
		      rsp.close();

		      
		      

		      

		   }catch(SQLException se){
		 
		      se.printStackTrace();
		   }catch(Exception e){
		    
		      e.printStackTrace();
		   }finally{
		   
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
/////////////////////////////////////////////////////////////////////////////////////////// add histories
		   try{
			      Class.forName("com.mysql.jdbc.Driver");
			      
			      System.out.println("Connecting to a selected database...");
			      conn = DriverManager.getConnection(DB_URL, USER, PASS);
			      System.out.println("Connected database successfully...");
			      
			      System.out.println("Creating statement...");
			      stmt = conn.createStatement();
			      
			      //create Patients
			      
			      String sql2 = "SELECT * FROM patientHistory;";
			      ResultSet rsh = stmt.executeQuery(sql2);
			      
			      while(rsh.next()){
			      PatientHistory h = new PatientHistory(
			    		  rsh.getInt("hId"),
			    		  rsh.getInt("doctorId"),
			    		  rsh.getInt("pId"),
			    		  rsh.getString("description"),
			    		  rsh.getString("medicine"),
			    		  rsh.getString("procedures"),
			    		  new Date(Integer.parseInt(rsh.getDate("visitDate").toString().substring(0,4)),
			    				  Integer.parseInt(rsh.getDate("visitDate").toString().substring(5,7)),
			    				  Integer.parseInt(rsh.getDate("visitDate").toString().substring(8,10))));

			      for(Doctor d: dList){
			    	  if(d.getDoctorId()==rsh.getInt("doctorId")){
			    		  d.getPatient(rsh.getInt("pId")).addPatientHistory(h);
			    	  }
			      }
			      }
			      rsh.close();

			      
			      

			      

			   }catch(SQLException se){
			 
			      se.printStackTrace();
			   }catch(Exception e){
			    
			      e.printStackTrace();
			   }finally{
			   
			      try{
			         if(stmt!=null)
			            conn.close();
			      }catch(SQLException se){
			      }
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }
			   }
			   return dList;
	   
	}
   public int getMaxDoctorId(){
	   int maxId = 0;
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      Class.forName("com.mysql.jdbc.Driver");
	      
	      System.out.println("Connecting to a selected database...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      System.out.println("Connected database successfully...");
	      
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      
	      //create Doctors
	      

	      ResultSet rs = stmt.executeQuery("SELECT max(doctorId) as maxId FROM doctor;");
	      while(rs.next()){
	      maxId = rs.getInt("maxId");
	      }
	      

	   }catch(SQLException se){
	 
	      se.printStackTrace();
	   }catch(Exception e){
	    
	      e.printStackTrace();
	   }finally{
	   
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){
	      }
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
	   }
	   return maxId;
	   
	}
   public int getMaxPatientId(){
	   int maxId = 0;
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      Class.forName("com.mysql.jdbc.Driver");
	      
	      System.out.println("Connecting to a selected database...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      System.out.println("Connected database successfully...");
	      
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      
	      //create Doctors
	      
	      String sql = "SELECT max(pId) as maxId FROM patient;";
	      ResultSet rs = stmt.executeQuery(sql);

	      while(rs.next()){
	      maxId = rs.getInt("maxId");
	      }
	      

	   }catch(SQLException se){
	 
	      se.printStackTrace();
	   }catch(Exception e){
	    
	      e.printStackTrace();
	   }finally{
	   
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){
	      }
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
	   }
	   return maxId;
	   
	}	
   public int getMaxPatientHistoryId(){
	   int maxId = 0;
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      Class.forName("com.mysql.jdbc.Driver");
	      
	      System.out.println("Connecting to a selected database...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      System.out.println("Connected database successfully...");
	      
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      
	      //create Doctors
	      
	      String sql = "SELECT max(hId) as maxId FROM patientHistory;";
	      ResultSet rs = stmt.executeQuery(sql);

	      while(rs.next()){
	      maxId = rs.getInt("maxId");
	      }
	      

	   }catch(SQLException se){
	 
	      se.printStackTrace();
	   }catch(Exception e){
	    
	      e.printStackTrace();
	   }finally{
	   
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){
	      }
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
	   }
	   return maxId;
	   
	}	
   public void insert(String sql) {
	   // JDBC driver name and database URL
	   
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      System.out.println("Connecting to a selected database...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      System.out.println("Connected database successfully...");
	      
	      //STEP 4: Execute a query
	      System.out.println("Inserting records into the table...");
	      stmt = conn.createStatement();
	      
	      stmt.executeUpdate(sql);
	      System.out.println("Inserted records into the table...");

	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){
	      }// do nothing
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
	
	}

}
