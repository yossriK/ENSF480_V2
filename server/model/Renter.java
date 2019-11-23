package server.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import server.model.Datab;
public class Renter extends Person{
	
	Connection myConnection;
	
	
	
	
	public Renter(Name n, Address ad, BirthDate bd, int id) throws SQLException {
		super(n, ad, bd,id);
		 myConnection=DriverManager.getConnection("jdbc:mysql://localhost:3306/ensf480_testshcema", "yossri", "student");

		// TODO Auto-generated constructor stub
	}

	public String listAll() throws SQLException {

		System.out.println("in renter list all");
		String s="";
		
		Statement myStmt= myConnection.createStatement();
		ResultSet myRs = myStmt.executeQuery(" select * from properties");
		
		while (myRs.next()) {
			
			s += "property number: "+myRs.getInt("property_number")+ ", listing price: " + myRs.getInt("list_price")+", location: " + myRs.getString("location")+" \0";
			System.out.println(s);
		
		}
		
		return s;
		
	}
	/*
	 * 
	 * subscribes the renter as an observer to get notified
	 */
	public void subscribe() {
		
	}
	/*
	 * deletes their subscription
	 */
	
	
	public void unsubscribe() {
		
		
		
	}
	
	public String Search(String recieved) throws SQLException {
		PreparedStatement myStmt= myConnection.prepareStatement("SELECT * FROM properties where propertyType LIKE ? AND nomBedroom LIKE ? AND nomBathroom LIKE ? AND furnished LIKE ? AND cityQuadrent LIKE ?");
		
		String sendBack="";
		String [] temp=recieved.split(","); // 1 propertytype, 2 bedrom, 3bath, 4 furnish, 5cityquad, 
	
		if(temp[1]!="*") {
			myStmt.setString(1, "%"+temp[1]+"%");
		}
		else
			myStmt.setString(1, "%");
		if(temp[2]!="*") {
			myStmt.setString(2, temp[2]);
		
		}
		else		
			myStmt.setString(2, "%");


		if(temp[3].compareTo("*")!=0) {
			myStmt.setString(3, temp[3]);
		}
		else
			myStmt.setString(3, "%");

		if(temp[4]!="*") {
			myStmt.setString(4, temp[4]);
		}
		else
			myStmt.setString(4, "%");

		if(temp[5]!="*") {
			myStmt.setString(5, temp[5]);
		}
		else
			myStmt.setString(5, "%");

		//TODO  delete statement
		System.out.println(myStmt);
		ResultSet myRs=myStmt.executeQuery();
		
		
		while (myRs.next()) {
			
			sendBack += "property number: "+myRs.getInt("property_number")+ ", listing price: " + myRs.getInt("list_price")
					+ ", cityQuadrent: " + myRs.getString("cityQuadrent")+", propertyType: " + myRs.getString("propertyType")
							+ ", nomBedroom: " + myRs.getString("nomBedroom")+", nomBathroom: " + myRs.getString("nomBathroom")+
							", furnished: " + myRs.getString("furnished")+" \0";
			
		
		}
		System.out.println(sendBack);
		
		return sendBack;
	}
	 /**
	  * 
	  * note for youstina to gt more info biut the property like location and stuff so user knows whats up
	  * @param PropertyID
	  * @return
	  * @throws SQLException
	  */
	public int searchPropertyOwner(int PropertyID) throws SQLException {
		int id=0;
		PreparedStatement myStmt= myConnection.prepareStatement("SELECT p.landlordID  FROM properties AS p WHERE p.propertyID = ?");
		myStmt.setInt(1, PropertyID);
		
		ResultSet rS=myStmt.executeQuery();
		while(rS.next()) {
			id=rS.getInt("Landlord_ID");
		}
		
		
		return id;
	}
	
	public void sendEmail(String emailToSend) throws SQLException {
		String [] temp=emailToSend.split(";");
		int propertyID=Integer.parseInt(temp[1]);
		int landlordID=searchPropertyOwner(propertyID);
		PreparedStatement myStmt= myConnection.prepareStatement("INSERT INTO messagestable(user_ID, message, propertyID) VALUES (?,?,?)");
		myStmt.setString(2, temp[2]);
		myStmt.setInt(1,landlordID);
		myStmt.setInt(3, propertyID);
		
		myStmt.executeUpdate();
		
		
	}

}
