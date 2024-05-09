package project;



public interface Librarian_Procedures {
    public void SearchBookByTitle(String Title);   // search for a book by title
    public void SearchBookByAuthor(String Name);  // search for a book by author's name
    public void SearchBookByGenre(String Genre);  // search for a book by genre
    public Book ReturnBook(String Title);
	public void ReserveBook(String Title);
	public User Add_User(); // add a new user to the system
    public Book AddBooks(Book b); // add a new book to the library
	
}