package project;
import java.util.*;
import javax.swing.JOptionPane;
public class User extends Person  implements User_Procedures{
//attributes for the User class//
private int libraryCardNum;
static LinkedList <User>User=new LinkedList<>();//this is the linkedlist to hold all of the users.

//The two constructors for the User class. The second one is a copy constructor.//

public User(String Name,int Age, String gender,int libraryCardNum) {
	super(Name,Age,gender);
	this.libraryCardNum=libraryCardNum;
}
public User(User t) {
	super(t.getName(),t.getAge(),t.getGender());
	this.libraryCardNum=t.libraryCardNum;
}

//Getter and Setter for the libraryCardNum instance variable and for the LinkedList of Users. 

public int getLibraryCardNum() {
	return libraryCardNum;
}
public void setLibraryCardNum(int libraryCardNum) {
	this.libraryCardNum = libraryCardNum;
}
public static LinkedList<User> getUser() {
	return User;
}
public static void setUser(LinkedList<User> user) {
	User = user;
}

//Overriding the methods of the User_Procedures.
//Searching a book by its Title.
@Override
public void SearchBookByTitle(String Title) {
	//StringBuilder to store the search results//
	StringBuilder result=new StringBuilder();
	//boolean variable to track if any books were found
	boolean i=false;
	//loop through each book in the book LinkedList
	for(Book j:Book.getBook()) {
		//Check if the title of the current book matches the search title
		if(j.getTitle().equals(Title)) {
			//if a matching book is found,set i to true
			i=true;
			//append the details of the matchinig book to the result StringBuilder
			result.append(j.toString()).append("\n");
			//break out of the loop since we found a matching book
			break;
		}
	}
	//checking if any books were found
	if (i) {
		//if books were found,display the search results in a dialog box
        JOptionPane.showMessageDialog(null, "Search Results for Title '" + Title + "':\n" + result.toString());
    } else {
    	// if no books were found, display a message indicating that
        JOptionPane.showMessageDialog(null, "No books found with the title '" + Title + "'.");
    }
	
}
//Searching a book in the book database by its Author.
@Override
public void SearchBookByAuthor(String Name) {
	//No need to comment this code as it is similar to the method SearchBookByTitle
	StringBuilder result=new StringBuilder();
	Boolean k=false;
	for(Book w: Book.getBook()) {
		if(w.getAuthor().equals(Name)) {
			k=true;
			result.append(w.getTitle()).append("\n");
			
		}
	}
	if (k) {
        JOptionPane.showMessageDialog(null, "Books by author '" + Name + "':\n" + result.toString());
    } else {
        JOptionPane.showMessageDialog(null, "No books found by author '" + Name + "'.");
    }
	
}
//Searching for a book based on its Genre.
@Override
public void SearchBookByGenre(String Genre) {
	StringBuilder result = new StringBuilder();
	boolean b=false;
	for(Book c: Book.getBook()) {
		if(c.getGenre().equals(Genre)) {
			b=true;
			result.append(c.getTitle()).append("\n");
			
		}
	}
	if (b) {
        JOptionPane.showMessageDialog(null, "Books in genre '" + Genre + "':\n" + result.toString());
    } else {
        JOptionPane.showMessageDialog(null, "No books found in genre '" + Genre + "'.");
    }
}

//allowing the user to borrow a book if it is available and not reserved.
//If the user borrows the book, it will become unavailable.
@Override
public void BorrowBook(String Title) {

	for(Book x:Book.getBook()) {
		if(x.getTitle().equals(Title)) {
			if(x.isAvailability()==true) {
				if(x.isReserved()==false) {
					x.setAvailability(false);
					//displaying through a dialog box  that the book has been borrowed
	                JOptionPane.showMessageDialog(null, "The book '" + Title + "' has been borrowed.");

					return;
				}
				else {
					//Indicating in a dialog box that the book cannot be borrowed 
					JOptionPane.showMessageDialog(null, "The book '" + Title + "' cannot be borrowed.");
	                return;
				}
			}
			
		}
	}
	//displaying through a dialog box that the book with the given details does not exist t
    JOptionPane.showMessageDialog(null, "The book '" + Title + "' does not exist.");

}

//Returning the information of a book if it was borrowed.

@Override
public Book ReturnBook(String Title) {
	for(Book h:Book.getBook()) {
		if(h.getTitle().equals(Title)) {
		
			if(h.isAvailability()==false) {
				if(h.isReserved()==false) {
					
					return h;
				}
				else {
					//displaying in a dialog box that the book cannot be returned//
					JOptionPane.showMessageDialog(null, "The book '" + Title + "' cannot be returned.");
	                return null;
				}
			}
			
		}
	}
	//displaying in a dialog box that no such book exists.
	JOptionPane.showMessageDialog(null, "The book '" + Title + "' does not exist.");
    return null;
}

//reserving a book according to its availability.
@Override
public void ReserveBook(String Title) {
	for(Book d:Book.getBook()) {
		if(d.getTitle().equals(Title)) {
			if(d.isAvailability()==true) {
				if(d.isReserved()==false) {
					d.setReserved(true);
					//displaying in a dialog box that the book was reserved
					JOptionPane.showMessageDialog(null, "The book '" + Title + "' has been reserved.");
	                return;
				}
				else {
					//displaying in a dialog box that the book cannot be reserved
					JOptionPane.showMessageDialog(null, "The book '" + Title + "' cannot be reserved.");
	                return;
				}
			}
			
		}
	}
	//displaying in a dialog box that no such book exists.
    JOptionPane.showMessageDialog(null, "The book '" + Title + "' does not exist.");

}

//This method is to provide the necessary information relating to the user.
@Override
public String Check_Information() {
	//this opens a dialog box where the user will see his own iindormation including his name, age ,gender , and library card number
	JOptionPane.showMessageDialog(null, super.toString()+"\nLibrary card number:"+this.libraryCardNum);

	return"";
}
}
