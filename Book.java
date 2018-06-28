/**
 * @author Jia Gao 26858031
 * Course: Comp 249 section: S
 * Assignment 1
 * Question 1 part 1
 * Due date: Jan 31, 2018
 * @version 1.0
 * Purpose: Create a Book class to classify all the books and write some relevant attributes and methods which are necessary
 *   
 */

// ----------------------------------------------------------
// Assignment 1
// Question: 1 part 1
// Written by: Jia Gao
// ----------------------------------------------------------
public class Book {

	private String title;
	private String name;
	private long ISBN;
	private double price;
	private static int findNumberOfCreatedBooks;

	public Book()
	{   
		title = "No title";
		name = "No name";
		ISBN = 0;
		price = 0;
		findNumberOfCreatedBooks++;
	}

	/**
	 * @param Initialtitle is the title of the new book
	 * @param Initialname is the author of the new book
	 * @param InitialISBN is the ISBN number of the new book
	 * @param Initialprice is the price of the new book, we have to check the price if it is reasonable
	 * */
	public Book(String Initialtitle,String Initialname,long InitialISBN,double Initialprice)
	{
		title=Initialtitle;
		name=Initialname;
		if(InitialISBN>0&&Initialprice>=0){
			ISBN=InitialISBN;
			price=Initialprice;}
		else{
			System.out.println("Fatal Error! ISBN and price cannot less than 0!");
			System.exit(0);
		}
		findNumberOfCreatedBooks++;
	}
	/**
	 * @param aBook copy constructor , it is the same book that we create*/
	public Book(Book aBook){//copy constructor
		if(aBook==null){
			System.out.println("Fatal error. This book doesnot exist.System terminated.");//check if the book is exist
			System.exit(0);
		}
		title=aBook.title;
		name=aBook.name;
		ISBN=aBook.ISBN;
		price=aBook.price;
		findNumberOfCreatedBooks++;
	}

	public String gettitle()
	{
		return title;
	}
	public String getname(){
		return name;
	}
	public long getISBN(){
		return ISBN;
	}
	public double getprice(){
		return price;
	}
	public void settitle(String newtitle){
		title=newtitle;
	}
	public void setname(String newname){
		name=newname;
	}
	public void setISBN(long newISBN){
		ISBN=newISBN;
	}
	public void setprice(double newprice){
		price=newprice;
	}
	public String toString(){
		return "Author: "+name+"\nTitle: "+title+"\nISBN: "+ISBN+"\nPrice: $"+price+"\n";
	}
	public static int findNumberOfCreatedBooks(){
		return findNumberOfCreatedBooks;
	}
	/**
	 * 2 books are considered equal if they have same ISBN number and price*/
	public boolean equals(Object otherObject)
	{
		if (otherObject == null)
			return false;
		else if (getClass() != otherObject.getClass())
			return false;
		else
		{
			Book otherBook = (Book)otherObject;
			return (ISBN==otherBook.ISBN && price==otherBook.price);
		}
	}

	/**
	 * @param inventory an array of the book
	 * @param authorName the name of the author to be searched in the array
	 * @return An array with the books that have same author
	 * */
	public static int[] findBooksBy(Book[] inventory, String authorName)
	{
		int counter = 0;
		int[] numberofbook = null;
		for(int i=0; i<inventory.length; i++)
			if(inventory[i]!=null && inventory[i].name.equals(authorName))
				counter++;
		if(counter == 0)
			System.out.println("There is no book in this inventory is wriiten by this author!");
		else
		{
			numberofbook = new int[counter];
			for(int i=0,j=0; i<inventory.length; i++)
				if(inventory[i]!=null && inventory[i].name.equals(authorName))
				{
					numberofbook[j] = i;
					j++;
				}
		}
		return numberofbook;
	}
	public static int[] findCheaperThan(Book[] inventory, double price)
	{
		int counter = 0;
		int[] bookNumber = null;
		for(int i=0; i<inventory.length; i++)
			if(inventory[i]!=null && inventory[i].price<price)
				counter++;
		if(counter == 0)
			System.out.println("There is no book in this inventory that under this price!");
		else
		{
			bookNumber = new int[counter];
			for(int i=0,j=0; i<inventory.length; i++)
				if(inventory[i]!=null && inventory[i].price<price)
				{
					bookNumber[j] = i;
					j++;
				}
		}
		return bookNumber;
	}

}
