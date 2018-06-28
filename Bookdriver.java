/**
 * @author Jia Gao 26858031
 * Course: Comp 249 section: S
 * Assignment 1
 * Question 1 part 2
 * Due date: Jan 31, 2018
 * @version 1.0
 * Purpose: helping the boss to track the books in the store.
 *   
 */

// ----------------------------------------------------------
// Assignment 1
// Question: 1 part 2
// Written by: Jia Gao
// ----------------------------------------------------------

import java.util.Scanner;
public class Bookdriver {
	public static void main(String[]args){
		Scanner keyboard=new Scanner(System.in);
		final String password = "password";
		System.out.println("------------------------------------------------------------");//Welcome message
		System.out.println("\tWelcome to use Jia's Program");
		System.out.println("------------------------------------------------------------");

		System.out.println("Please enter the maximum number of books in your bookstore: ");
		int maxbooks=keyboard.nextInt();
		//check if the input is valid
		while(maxbooks<=0){
			System.out.println("Please enter a positive number. Try again: ");
			maxbooks = keyboard.nextInt();
		}
		Book[] inventory = new Book[maxbooks];
		int passwordAttempts = 0;//This is the counter for password failure.
		int remainingSpace = maxbooks;// If there is no remaining space the user will not be able to add new books
		boolean goToMainMenu = true;//When it is true it will go back to the main menu.

		//While loop is used here to control if go back to the main menu
		//Several ways to accomplish such as set goToMainMenu to true or use continue etc.
		while(goToMainMenu){
			System.out.println("What do you want to do?\n" + 
					"   1. Enter new books (password required)\n" + 
					"   2. Change information of a book (password required)\n" +
					"   3. Display all books by a specific author\n" + 
					"   4. Display all books under a certain a price.\n" + 
					"   5. Quit\n" + 
					"Please enter your input > ");
			int input =keyboard.nextInt();
			//Check if the input number is valid
			while(input<1 || input>5){
				System.out.println("Please enter a number between 1 and 5. Try again: ");
				input = keyboard.nextInt();
			}
			if (input==1){//Discuss the situation when input is 1
				//First check if there is enough space to add new books
				int booknumber=0;//The book number is the index in the array inventory
				for(booknumber=0; booknumber<maxbooks; booknumber++)
					if(inventory[booknumber]==null){
						remainingSpace = maxbooks - booknumber;
						break;
					}
				if(remainingSpace==0){
					System.out.println("Cannot add new book due to lack of space");
					continue;
				}
				//Checking password


				System.out.println("Enter your password: ");
				String passwordentered = keyboard.next();
				int passwordAttempt1 = 1;//Incorrect password counter set to 1.
				while(!passwordentered.equals(password) && passwordAttempt1<3){
					System.out.println("Incorrect password:( Try again: ");
					passwordentered = keyboard.next();
					passwordAttempt1++;
				}
				//Incorrect password counter for main menu counter
				if(!passwordentered.equals(password)){//if tried more than 3 times
					passwordAttempts++;
					if(passwordAttempts<4){//less than 12 times
						System.out.println("Incorrect password :( Gonna return to the main menu!");
						continue;//go to main menu
					}   
					else{//If tried 12 times
						System.out.println("Program detected suspicous activities and will terminate immediately!");
						System.exit(0);
					}
				}
				System.out.println("Your password is correct!");

				int numberOfNewBooks;//The number of new books that we want to add.
				//Check if there is enough space to add
				do{
					System.out.println("How many books do you want to enter?");
					numberOfNewBooks = keyboard.nextInt();
					if(numberOfNewBooks > remainingSpace)
						System.out.println("You can only add at most " + remainingSpace + " books."); 
				}
				while(numberOfNewBooks > remainingSpace);

				//Creating new books
				//booknumber is the index of the first null space in inventory. Got when we calculating the remaining space
				for(int i=0; i<numberOfNewBooks; booknumber++,i++){
					System.out.print("Input the information of book "+(i+1)+":\nTitle: ");
					keyboard.nextLine();
					String title = keyboard.nextLine();
					System.out.print("author name: ");
					String name = keyboard.nextLine();
					System.out.print("ISBN: ");
					long ISBN = keyboard.nextLong();
					System.out.print("Price: ");
					double price = keyboard.nextDouble();
					inventory[booknumber] = new Book(title, name, ISBN, price);
				}
				System.out.println("The new books have been created.\n");
				continue;
			}
			else if (input == 2){
				//Checking password
				System.out.println("Enter your password: ");
				String passwordentered = keyboard.next();
				int passwordAttempts2 = 1;
				while(!passwordentered.equals(password) && passwordAttempts2<3){
					System.out.println("Incorrect password! Try again: ");
					passwordentered = keyboard.next();
					passwordAttempts2++;
				}
				if(!passwordentered.equals(password)){
					System.out.println("Incorrect password! Return to the main menu.");
					continue;
				}
				System.out.println("Your password is correct :(");

				//Update information of books.
				boolean ReEnter = true;
				while(ReEnter){
					System.out.println("Which book number do you want to update?");
					int booknumber = keyboard.nextInt();
					//Invalid index
					if(inventory[booknumber-1]==null){
						System.out.println("There is no book currently at"+ booknumber +" location");
						System.out.println("What do you want to do?\n" + 
								"   1. Re-enter another book\n" + 
								"   2. Quit and go back to the main menu.\n" +
								"Please enter your input > ");
						int input2 = keyboard.nextInt();
						//Check the input
						while(input2<1 || input2>2){
							System.out.println("Please enter a number between 1 and 2 . Try again: ");
							input2 = keyboard.nextInt();
						}
						//If input2 == 1, repeat.
						if(input2==2)
							ReEnter = false;//Get out of the "ReEnter" while loop. Go to main menu
					}
					else{//Update the book.
						System.out.println("Book #" + booknumber);
						System.out.println(inventory[booknumber-1]);    
						System.out.println();
						int input2 = 0;//The user's input of what information will be changed
						while(input2!=5){//Keep changing until he/she quit
							System.out.println("What information would you like to change?\n" + 
									"   1. author\n" + 
									"   2. title\n" +
									"   3. ISBN\n" + 
									"   4. price\n" + 
									"   5. Quit\n" + 
									"Enter your input > ");
							input2 = keyboard.nextInt();
							//check input
							while(input2<1 || input2>5){
								System.out.println("Please enter a number between 1 and 5. Try again: ");
								input2 = keyboard.nextInt();
							}
							//Change the information as required.
							if(input2==1){
								System.out.print("Enter new name here: ");
								keyboard.nextLine();
								String newname = keyboard.nextLine();
								inventory[booknumber-1].setname(newname);
							}
							else if(input2==2){
								System.out.print("Enter new title here: ");
								keyboard.nextLine();
								String newTitle = keyboard.nextLine();
								inventory[booknumber-1].settitle(newTitle);
							}
							else if(input2==3){
								System.out.print("Enter new ISBN here: ");
								long newISBN = keyboard.nextLong();
								inventory[booknumber-1].setISBN(newISBN);
							}
							else if(input2==4){
								System.out.print("Enter new price here: ");
								double newPrice = keyboard.nextDouble();
								inventory[booknumber-1].setprice(newPrice);
							}
							else if(input2==5){
								ReEnter = false;
								break;
							}
							//Display new information
							System.out.println("Now, book #" + booknumber);
							System.out.println(inventory[booknumber-1]);
						}
					}
				}
			}

			else if (input == 3){
				System.out.print("Enter the author's full name here: ");
				keyboard.nextLine();
				String name = keyboard.nextLine();
				//Input: the inventory array, author's name. 
				//Return: An array containing the index of inventory that has the same author's name
				int[] booknumber =Book.findBooksBy(inventory, name);
				if(booknumber!=null)
					for(int i=0; i<booknumber.length; i++)
						System.out.println(inventory[booknumber[i]]);
			}

			else if (input == 4){
				System.out.print("Enter the price here: ");
				double price = keyboard.nextDouble();
				int[] booknumber = Book.findCheaperThan(inventory, price);
				if(booknumber!=null)
					for(int i=0; i<booknumber.length; i++)
						System.out.println(inventory[booknumber[i]]);
			}

			else if (input == 5)
				goToMainMenu = false;
		}
		System.out.println("\n------------------------------------------------------------");
		System.out.println("    Thank you for using Jia's Program! Have a great day!");
		System.out.println("------------------------------------------------------------");

		keyboard.close();
	}
}




