package com.test.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MysqlConnect {
	
	public static void main(String args[]){
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/mydatabase","root","admin@123");  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from user");  
			while(rs.next())  {
				System.out.println(rs.toString());
			System.out.println(rs.getString(1));  
			System.out.println(rs.getString(2)); 
			System.out.println(rs.getInt(3)); 
			}
			con.close();  
			}catch(Exception e){ System.out.println(e);}  
			}  
		
		
		
	
}
