package server.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;


public class Landlord  extends Person{
	Connection myConnection;


	
	public Landlord(Name n, Address ad, BirthDate bd, int id) {
		super(n, ad, bd,id);
		 try {
			myConnection=DriverManager.getConnection("jdbc:mysql://localhost:3306/ensf480_testshcema", "yossri", "student");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated constructor stub
	}

	/*
	// when opted, it creates new property, gotta have arguments
	 * this will be called from controller and populated using gui stuff	
	*/
	public Property createProperty(String recieved) {
		
		
		System.out.println("in create"+recieved);

		String [] temp=recieved.split(","); // 1 propertytype,2 cityQuad, 3 listprice  4 bedrom, 5bath, 5 furnish, , 
		String propT=temp[1]; // property type
		 String cityq=temp[2];// city quad
		 int listPrice=Integer.parseInt(temp[3]);
		 int numbed=Integer.parseInt(temp[4]);
		int numw=Integer.parseInt(temp[5]);
		String fur=temp[6];
		
		Property p= new Property(propT, numbed,  numw,  fur, cityq,listPrice);
		
		return p;
		
	}
	
	/*
	 * taking the data of property instance and add it to data base.
	 * 
	 */
	public void registerProperty(Property p) {
		System.out.println("in register"+p.getPrice());
		Random rand = new Random();
		int randoms=rand.nextInt(10000);

		// Obtain a number between [0 - 49].
	 try {
		
		
		PreparedStatement myStmt= myConnection.prepareStatement("INSERT INTO properties (property_number,list_price,cityQuadrent,propertyType,nomBedroom,nomBathroom,furnished) VALUES (?,?,?,?,?,?,?) ");
		//Statement myStmt= myConnection.createStatement();
		//myStmt.executeUpdate(("INSERT INTO properties (property_number,list_price,cityQuadrent,propertyType,nomBedroom,nomBathroom,furnished) VALUES (randoms,p.getPrice(),p.getCity(),p.getPropType(),  p.getNomBed(),p.getNomBath(),p.getfur()) "));
		((PreparedStatement) myStmt).setInt(1, rand.nextInt(10000));
		((PreparedStatement) myStmt).setInt(2, p.getPrice());
		((PreparedStatement) myStmt).setString(3, p.getCity());
		((PreparedStatement) myStmt).setString(4, p.getPropType());
		((PreparedStatement) myStmt).setInt(5, p.getNomBed());
		((PreparedStatement) myStmt).setInt(6, p.getNomBath());
		((PreparedStatement) myStmt).setString(7, p.getfur());
		
		myStmt.executeUpdate();
		
	 }catch(SQLException e) {
		 e.printStackTrace();
	 }
		
	
	
		notifyAllSubscribedRenters();
	}
	/*
	 * sends a message to all subscribers once a listing been posted
	 */
	public void notifyAllSubscribedRenters() {
		
		
	}
	public void makePayment() {
		
		
		
		
	}
public void printtest() {
		
		System.out.println(" in Landlord");
	}

}
