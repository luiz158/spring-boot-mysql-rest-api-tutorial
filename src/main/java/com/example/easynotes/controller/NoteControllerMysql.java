package com.example.easynotes.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;  

 
@RestController
@RequestMapping("/mysql")
public class NoteControllerMysql {
 
	private Connection connectToDB(String username, String password,String db) {
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        String url = "jdbc:mysql://172.30.129.40:3306/"+db;
	        Properties objProperties = new Properties();
	        objProperties.put("user", username);
	        objProperties.put("password", password);
	        objProperties.put("useUnicode", "true");
	        objProperties.put("characterEncoding", "utf-8");

	        Connection con = DriverManager.getConnection(url, objProperties);
	        return con;
	    } catch (Exception ex) {
	        System.out.println("Connection to sql database failed.");
	        ex.printStackTrace();
	        return null;
	    }
	}
    

	@GetMapping("/createSchema")
    public String  createSchema() {
		Connection con = null;
    	 
    	String q = "";
    	String r = "";
    	
 
    	try {    		 
                ClassPathResource resource = new ClassPathResource("backup_db_quick_mysql.sql");
                BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
                q = reader.lines().collect(Collectors.joining("\n"));
                reader.close();
             
    		con=connectToDB("quickekart","quickekart","");
    	     
    	// Now we have the content of the dumpfile in 'q'.
    	// We must separate the queries, so they can be executed. And Java Simply does this:
    	String[] commands = q.split(";");

    	try {
    	    Statement statement = con.createStatement();
    	    for (String s : commands) {
    	        statement.execute(s);
    	    }
    	} catch (Exception ex) {
    		ex.printStackTrace(); 
    	}
    	try {
    		if(con!=null)
			con.close();
		} catch (SQLException e) { 
			e.printStackTrace();
			r+=e.getMessage()+"\n";
		}
    	}catch (Exception e) { 
			e.printStackTrace();
			r+=e.getMessage()+"\n";
		}
		return r + "\n\n\n\n=========================================\n\n\n\n" + q;
    }
	
	@GetMapping("/getData")
    public String getData() throws SQLException {
    	

    	 Statement statement;
    	 ResultSet rs=null;
    	 StringBuilder sb=new StringBuilder("Blank");
		try {
			Connection con = connectToDB("quickekart","quickekart","quick/");
			statement = con.createStatement();
		
    	 
    	  rs=statement.executeQuery("select lookup_name from cmn_lookup_mst ");
    	  
    	  while(rs.next()) {
    		  sb.append(rs.getString("lookup_name"));
    	  }
    	  
    	  return sb.toString();
		} catch (Exception e) { 
			e.printStackTrace();
			return e.getMessage();
		} 
    }

	@GetMapping("/getData1")
    public String getData1() throws SQLException {
    	

    	 Statement statement;
    	 ResultSet rs=null;
    	 StringBuilder sb=new StringBuilder("Blank");
		try {
			Connection con = connectToDB("quickekart","quickekart","quick");
			statement = con.createStatement();
		
    	 
    	  rs=statement.executeQuery("select lookup_name from cmn_lookup_mst ");
    	  
    	  while(rs.next()) {
    		  sb.append(rs.getString("lookup_name"));
    	  }
    	  
    	  return sb.toString();
		} catch (Exception e) { 
			e.printStackTrace();
			return e.getMessage();
		} 
    }
    
}
