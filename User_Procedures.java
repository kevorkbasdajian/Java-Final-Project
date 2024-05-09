package project;

//this is the User_Procedures interface. The methods listed below will be implemeneted and used in the User class.

public interface User_Procedures {
	
	//The methods of the User interface
	
	public void SearchBookByTitle(String Title);
	public void SearchBookByAuthor(String Name);
	public void SearchBookByGenre(String Genre);
	public void BorrowBook(String Title);
	public Book ReturnBook(String Title);
	public void ReserveBook(String Title); 
	
}
