package project;
import java.util.*;

import javax.swing.JOptionPane;
public class Librarian extends Person implements Librarian_Procedures {
    private int employeeID; // identifier for any librarian
    static LinkedList<Librarian> librarian = new LinkedList<>(); // storing librarian objects
    public Librarian(String Name, int Age, String gender, int employeeID) {
        super(Name, Age, gender);
        this.employeeID = employeeID;
        
}
    public Librarian(Librarian other) {  // Copy constructor 
        super(other.getName(), other.getAge(), other.getGender());
        this.employeeID = other.employeeID;
}
    public int getEmployeeID() {
        return employeeID;
}
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
}
    public static LinkedList<Librarian> getLibrarian() {
        return librarian;
}
    public static void setLibrarian(LinkedList<Librarian> librarian) {
        Librarian.librarian = librarian;
}
    public void AddLibrarian() {
        
        Random random = new Random();
         int generatedID = random.nextInt(9000) + 1000;
         int finalGeneratedID = generatedID;
         generatedID = librarian.stream().anyMatch(lib -> lib.getEmployeeID() == finalGeneratedID) ? random.nextInt(9000) + 1000 : generatedID;        
        Librarian newLibrarian = new Librarian(this.getName(), this.getAge(), this.getGender(), generatedID);
        librarian.add(newLibrarian);
        // here there's a rather interesting line of code which I learned while searching for different ways 
        // of conditional checking. first we turn the linkedlist to a stream to make it easier for us to 
        // go through the elements, then we check if any of the already existing IDs match the newly generated one.
        // then we have the "?" aka the ternary operator. if the condition before the "?" is true, then the 
        // value before the : is returned, otherwise the value after it is returned. this helps us check if any of the new
        // generated IDs already exist, and if so, we generate a new one.
        JOptionPane.showMessageDialog(null, "Your Employee ID is: " + generatedID, "Employee ID", JOptionPane.INFORMATION_MESSAGE);
        
        JOptionPane.showMessageDialog(null, "Librarian registered successfully!");
}    @Override
    public String Check_Information() { // self explanatory
	JOptionPane.showMessageDialog(null, super.toString()+"\nEmployee ID:"+this.getEmployeeID());

	return"";
}
    @Override
    public void SearchBookByTitle(String Title) { // self explanatory
    	StringBuilder result=new StringBuilder();
    	boolean i=false;
    	for(Book j:Book.getBook()) {
    		if(j.getTitle().equals(Title)) {
    			i=true;
    			result.append(j.toString()).append("\n");
    			break;
    		}
    	}
    	if (i) {
            JOptionPane.showMessageDialog(null, "Search Results for Title '" + Title + "':\n" + result.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No books found with the title '" + Title + "'.");
        }
}
    @Override
    public void SearchBookByAuthor(String Name) { // self explanatory
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
    @Override
    public void SearchBookByGenre(String Genre) { // self explanatory
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
    @Override
    public User Add_User() {  // self explanatory
        
        Random random = new Random();   // generate a random library card number for the user
        int generatedLibraryCard = random.nextInt(9000) + 1000;
        User newUser = new User(this.getName(), this.getAge(), this.getGender(), generatedLibraryCard);
        User.getUser().add(newUser); // add the new user to the list
        JOptionPane.showMessageDialog(null, "Your library card number is: " + generatedLibraryCard, "Library Card Number", JOptionPane.INFORMATION_MESSAGE);
        
        JOptionPane.showMessageDialog(null, "User registered successfully!");        return newUser;
}
    @Override
    public  Book AddBooks(Book b11) { // self explanatory
        Book newBook = new Book(b11.getISBN(), b11.getTitle(), b11.getAuthor(), b11.getGenre(), b11.isAvailability(), b11.isReserved());
        Book.getBook().add(newBook);
        return newBook;
}
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
}