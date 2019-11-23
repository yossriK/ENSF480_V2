package server.model;

public  class Person {
	private int personID;
	private Name name;
	private Address addy;
	private BirthDate BD;
	
	
	Person(Name n,Address ad, BirthDate bd, int id){
		
		this.name=n;
		this.addy=ad;
		this.BD=bd;
		personID=id;
	}


	public void addProperty() {
	}
	public void addListing() {
	}
	
	
}
