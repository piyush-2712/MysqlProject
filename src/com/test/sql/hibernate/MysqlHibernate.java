package com.test.sql.hibernate;
import java.util.List; 
import java.util.Date;
import java.util.Iterator; 
 
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.test.sql.vo.User;


public class MysqlHibernate { 
	 private static SessionFactory factory; 
	public static void main(String args[]){
		try{
	         factory = new Configuration().configure().buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		MysqlHibernate obj= new  MysqlHibernate();
		Integer useridID1 = obj.addUser("piyush",12345,"delhi");
		System.out.println(useridID1);
		
		System.out.println("Object has been added");
		
		
		//For the display part
		
		obj.showUsers();
		//obj.deleteUser(15);
		System.out.println("no 15 deleted");
		obj.updateUser(17,"ram");
		System.out.println("no 17 updated");
		obj.showUsers();
		
		factory.close();
	}
	
	
	
	public void updateUser(int id, String updatedname){	
	
		 Session session = factory.openSession();
		 Transaction tx = null;
		// Session session = factory.openSession();
		 
		 try{
			 tx = session.beginTransaction();
			User user=(User) session.get(User.class,id);
			System.out.println(user.getAddress()+"  "+user.getId()+"  "+user.getName());
		      user.setName(updatedname);
		      session.update(user);
	  
		      tx.commit();
		 }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		 
	
	
	}
	
	
	public void deleteUser(int id){
		 Session session = factory.openSession();
		 Transaction tx = null;
		// Session session = factory.openSession();
		 
		 try{
			 tx = session.beginTransaction();
			User user=(User) session.get(User.class,15);
		      session.delete(user);
		      
		        
	  
		      tx.commit();
		 }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		 
	
	}
	
	
	
	public void showUsers(){
		 Session session = factory.openSession();
		 Transaction tx = null;
		 
		 try{
		       tx = session.beginTransaction();
		        
		     List resultset = session.createQuery("from com.test.sql.vo.User").list();
		   
		     
		     for (Iterator iterator = 
		    		 resultset.iterator(); iterator.hasNext();){
      User user = (User) iterator.next(); 
     System.out.println(user.getAddress());
     System.out.println(user.getId());
     System.out.println(user.getName());
     
   }
		       //  System.out.println(resultset.size());
		         
		         tx.commit();
		      }catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      }finally {
		         session.close(); 
		      }
		    
		
		
	}
	
	
	 public Integer addUser(String name,int phone, String address){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      Integer userID = null;
	      try{
	       tx = session.beginTransaction();
	         User user = new User();
	         user.setName(name);
	         user.setPhone(phone);
	         user.setAddress(address);
	         user.setId(111);
	         userID = (Integer) session.save(user); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return userID;
	   }

}
