package com.example.easynotes.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.easynotes.model.Note;  

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
@RestController
@RequestMapping("/mysql")
public class NoteControllerMysql {
 
	private Connection connectToDB(String username, String password) {
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        String url = "jdbc:mysql://mysql:3306/";
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
    public Note createSchema() {
    	Connection con = connectToDB("quickekart","quickekart");
    	/* Note that con is a connection to database, and not the server.
    	if You have a connection to the server, the first command in the dumpfile should be the
    	USE db_name; */
    	String q = "";
    	ClassLoader classLoader = getClass().getClassLoader();
    	File f = new File(classLoader.getResource("backup_db_quick_mysql.sql").getFile()); 
    	try {
    	    BufferedReader bf = new BufferedReader(new FileReader(f));
    	        String line = null;
    	        line = bf.readLine();
    	        while (line != null) {
    	            q = q + line + "\n";
    	            line = bf.readLine();
    	        }
    	        bf.close();
    	    } catch (Exception ex) {
    	        ex.printStackTrace();
    	    }
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
			con.close();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return null;
    }
	
	@GetMapping("/getData")
    public String getData() throws SQLException {
    	Connection con = connectToDB("quickekart","quickekart");

    	 Statement statement;
    	 ResultSet rs=null;
    	 StringBuilder sb=new StringBuilder();
		try {
			statement = con.createStatement();
		
    	 
    	  rs=statement.executeQuery("select lookup_name from cmn_lookup_mst ");
    	  
    	  while(rs.next()) {
    		  sb.append(rs.getString("lookup_name"));
    	  }
    	  
    	  return sb.toString();
		} catch (SQLException e) { 
			e.printStackTrace();
			throw e;
		} 
    }

    
}
