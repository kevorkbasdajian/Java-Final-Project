package project;
import java.util.*;
public class Book {
	private int ISBN;
	private String title;
	private String author;
	private String genre;
	private boolean availability;
	private boolean reserved;
	private static LinkedList<Book> book=new LinkedList<>();
	
	public Book() {
		this.ISBN = 0;
		this.title = "Unknown";
		this.author = "Unknown";
		this.genre = "Unknown";
		this.availability = true;
		this.reserved = false;
	}
	
	public Book(int ISBN, String title, String author, String genre, boolean availability, boolean reserved) {
		this.ISBN = ISBN;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.availability = availability;
		this.reserved = reserved;
	}
	
	public Book(Book b) {
		this.ISBN = b.ISBN;
		this.title = b.title;
		this.author = b.author;
		this.genre = b.genre;
		this.availability = b.availability;
		this.reserved = b.reserved;
	}

	public int getISBN() {
		return ISBN;
	}

	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public static LinkedList<Book> getBook() {
		return book;
	}

	public static void setBook(LinkedList<Book> book) {
		Book.book = book;
	}
	
	public String toString() {
		return String.format("Title : %s, ISBN : %d, Author : %s, Genre : %s", title, ISBN, author, genre);
	}
}
