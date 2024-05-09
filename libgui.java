package project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Stack;
import java.awt.event.ActionEvent;


public class libgui extends JFrame implements ActionListener/*for handling button click events*/ {
    JButton librarianButton;
    JButton userButton;
    JLabel background;
    JLabel welcomeLabel;
    //Declaration of instance variables for buttons and a label.//

    public libgui()/*Constructor for the 'LibraryGUI' class*/ {
       /*Sets the title, size, close operation, and layout of the JFrame.*/
    	
    	setTitle("Welcome");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        welcomeLabel = new JLabel("<html><div style='text-align: center; width: 600px;'>Welcome to the library system created by Sergei, Kevork, and Christian. Feel free to browse through our system and use its multiple functionalities for your benefit. Enjoy! &#128522;<br><br></div></html>");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        welcomeLabel.setForeground(Color.BLACK);
        welcomeLabel.setBounds(10, 10, 780, 150); // Adjusted width for better visibility
        welcomeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        
        
        JLabel librarianInstruction = new JLabel("<html>If you are a librarian, click the Librarian button. &#8595;</html>");
        librarianInstruction.setBounds(100, 250 - 40, 200, 40); // Adjusted position and width
        JLabel userInstruction = new JLabel("<html>If you are a user, click the User button. &#8595;</html>");
        userInstruction.setBounds(500, 250 - 40, 200, 40); // Adjusted position and width


        
        /*Creates a background image label and sets its bounds.*/
        ImageIcon backgroundImage = new ImageIcon("background.jpg");
        background = new JLabel(backgroundImage);
        background.setBounds(0, 0, 800, 600);
        /*Creates a librarian button and sets its properties.*/
        librarianButton = new JButton("Librarian");
        librarianButton.setBounds(100, 250, 200, 80);
        librarianButton.setFont(new Font("Arial", Font.BOLD, 24));
        librarianButton.setForeground(Color.WHITE);
        librarianButton.setBackground(new Color(66, 134, 244));
        librarianButton.addActionListener(this);
        /*Creates a user button and sets its properties.*/
        userButton = new JButton("User");
        userButton.setBounds(500, 250, 200, 80);
        userButton.setFont(new Font("Arial", Font.BOLD, 24));
        userButton.setForeground(Color.WHITE);
        userButton.setBackground(new Color(66, 134, 244));
        userButton.addActionListener(this);
        /*Adds components to the JFrame.*/
        add(welcomeLabel);
        add(librarianButton);
        add(userButton);
        add(background);
        add(librarianInstruction);
        add(userInstruction);            
        setLocationRelativeTo(null); // Center the window on the screen

        /*Sets the JFrame to be visible*/
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Save user information to file
                FileIO.writeUsersToFile(User.getUser(), "user.txt");
                FileIO.writeLibrariansToFile(Librarian.getLibrarian(), "librarian.txt");
                FileIO.writeBooksToFile(Book.getBook(),"book.txt");
            }
        });
    }
    
    
    	/*Implements the actionPerformed method required by the ActionListener interface.*/
    public void actionPerformed(ActionEvent e) {
    	/*If the librarian button is clicked*/
        if (e.getSource() == librarianButton) {
            dispose(); // Close the welcome page
            new LoginUI("Librarian"); // Open login UI for librarian
        }
        /*If the user button is clicked.*/
        else if (e.getSource() == userButton) {
            dispose(); // Close the welcome page
            new LoginUI("User"); // Open login UI for user
        }
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FileIO.readUsersFromFile(User.getUser(), "user.txt");
            FileIO.readLibrariansFromFile(Librarian.getLibrarian(), "librarian.txt");
            FileIO.readBooksFromFile(Book.getBook(), "book2.txt");
            libgui libGui = new libgui(); // Create a single instance of Libgui
        });
    }
}

class LoginUI extends JFrame implements ActionListener {
	JTextField idField;
	JTextField libraryCardField;
    JButton loginButton;
    JButton registerButton;
    String userType;
    /*Constructor for the LoginUI class. It takes a userType parameter, representing whether the user is a librarian or a regular user.*/
    public LoginUI(String userType) {
        this.userType = userType;
        setTitle("Login (" + userType + ")");/*Sets the title of the window to "Login (userType)", where userType is the parameter passed to the constructor.*/
        setSize(800, 600);/*Sets the size of the window to 300x300 pixels.*/
        setDefaultCloseOperation(EXIT_ON_CLOSE);/*Sets the default close operation to exit the application when the window is closed.*/
        setLayout(null);
        JLabel libraryCardLabel = null;
        JLabel idLabel=null;
        if(userType.equals("User")) {
        libraryCardLabel = new JLabel("Library Card Number:");
        libraryCardLabel.setFont(new Font("Arial", Font.PLAIN, 16)); 
        libraryCardLabel.setBounds(300 - 150, 150, 200, 30); 
        libraryCardField = new JTextField();/*Creates text field for entering the Library Card Number , and sets its bounds.*/
        libraryCardField.setBounds(300 + 5, 150, 250, 30);
        }
       
        else if(userType.equals("Librarian")) {
        	idLabel = new JLabel("Employee ID:");
            idLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            idLabel.setBounds(300 - 150, 200, 200, 30);
            idField = new JTextField();
            idField.setBounds(300 + 5, 200, 250, 30);
        }
        

        /*Creates login and register buttons, sets their text, bounds, background color, foreground color, and adds an action listener (this refers to the LoginUI instance itself).*/
        loginButton = new JButton("Login");
        loginButton.setBounds(100, 250, 200, 80);
        loginButton.setFont(new Font("Arial", Font.BOLD, 24));
        loginButton.setBackground(new Color(66, 134, 244));
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(this);

        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.BOLD, 24));
        registerButton.setBounds(500 , 250, 200, 80);
        registerButton.setBackground(new Color(66, 134, 244));
        registerButton.setForeground(Color.WHITE);
        registerButton.addActionListener(this);
        /* o  Adds the labels, text fields, and buttons to the frame.
         * o  Sets the frame to be visible.*/
        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 10, 80, 30);
        backButton.addActionListener(e -> {
            dispose();
            new libgui();
        });
        if(userType.equals("User")) {
        	add(libraryCardLabel);
        	add(libraryCardField);
        }
        else if(userType.equals("Librarian")) {
        	add(idLabel);
        	add(idField);
        }


        add(backButton);
        add(loginButton);
        add(registerButton);
        
        
        
        setLocationRelativeTo(null);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
               
                FileIO.writeUsersToFile(User.getUser(), "user.txt");
                 FileIO.writeLibrariansToFile(Librarian.getLibrarian(), "librarian.txt");
                 FileIO.writeBooksToFile(Book.getBook(), "book.txt");
            }
        });
    }
    /*This method is called when an action event occurs, such as clicking a button.*/
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
        	if(userType.equals("Librarian")) {
        		String empId = idField.getText();
        		try {
        			int employeeid=Integer.parseInt(empId);
        			boolean librarianfound=false;
        			for(Librarian librarian:Librarian.getLibrarian()) {
        				if(librarian.getEmployeeID()==employeeid) {
        					librarianfound=true;
                            JOptionPane.showMessageDialog(this, "Welcome Librarian!");
                            displayLibrarianOptions(librarian);
                            break;
        				}
        			}
        			if(!librarianfound) {
        				Object[] options = {"Retry", "Go to Menu", "Exit"};
                        int option = JOptionPane.showOptionDialog(this, "Invalid Employee ID. What do you want to do?", "Invalid Library Card Number", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
                        if (option == JOptionPane.YES_OPTION) {
                            idField.setText(""); // Clear the text field
                        } else if (option == JOptionPane.NO_OPTION) {
                            dispose();
                            new libgui();
                        } else {
                            System.exit(0); // Exit the application
                        }
        			}
        		}
        		catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Employee ID must be an integer");
                } 
        	}
        	else {
        		String libraryCardNumber = libraryCardField.getText();
            try {
                int cardNumber = Integer.parseInt(libraryCardNumber);
                boolean userfound=false;
                for(User user : User.getUser()) {
                	if(user.getLibraryCardNum()==cardNumber) {
                		userfound=true;
                        JOptionPane.showMessageDialog(this, "Welcome User!");
                        displayUserOptions(user);
                		break;
                	}
                }
                
                if(!userfound ) {
                	 Object[] options = {"Retry", "Go to Menu", "Exit"};
                     int option = JOptionPane.showOptionDialog(this, "Invalid library card number. What do you want to do?", "Invalid Library Card Number", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
                     if (option == JOptionPane.YES_OPTION) {
                         libraryCardField.setText(""); // Clear the text field
                     } else if (option == JOptionPane.NO_OPTION) {
                         dispose();
                         new libgui();
                     } else {
                         System.exit(0); // Exit the application
                     }
                 }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Library card number must be an integer");
            }
        	}
             
            
        } else if (e.getSource() == registerButton) {
            // Open registration form
            dispose(); // Close the login window
            new RegistrationForm(userType);
        }
    }
    public void displayLibrarianOptions(Librarian librarian) {
    	 setTitle("Librarian Options");
    	 getContentPane().removeAll(); // Remove all existing components
    	 JButton addLibrarianButton = new JButton("Add Librarian");
    	 JButton addUserButton = new JButton("Add User");
    	 JButton checkInfoButton = new JButton("Check Information");
    	 JButton searchByTitleButton = new JButton("Search Book By Title");
    	 JButton searchByAuthorButton = new JButton("Search Book By Author");
    	 JButton searchByGenreButton = new JButton("Search Book By Genre");
    	 JButton borrowBookButton = new JButton("Borrow Book");
    	 JButton returnBookButton = new JButton("Return Book");
    	 JButton reserveBookButton = new JButton("Reserve Book");
    	 JButton addBookButton = new JButton("Add Book");
    	 JButton backButton = new JButton("Back");
         backButton.setBounds(10, 10, 80, 30);

    	 // Set bounds for each button
    	 int buttonWidth = 200;
         int buttonHeight = 30;
         int startX = (getWidth() - buttonWidth) / 2; // Center horizontally
         int startY = (getHeight() - (10 * buttonHeight + 9 * 10)) / 2; // Center vertically

         addLibrarianButton.setBounds(startX, startY, buttonWidth, buttonHeight);
         addUserButton.setBounds(startX, startY + (buttonHeight + 10), buttonWidth, buttonHeight);
         addBookButton.setBounds(startX, startY + 2 * (buttonHeight + 10), buttonWidth, buttonHeight); // Adjusted position
         checkInfoButton.setBounds(startX, startY + 3 * (buttonHeight + 10), buttonWidth, buttonHeight);
         searchByTitleButton.setBounds(startX, startY + 4 * (buttonHeight + 10), buttonWidth, buttonHeight);
         searchByAuthorButton.setBounds(startX, startY + 5 * (buttonHeight + 10), buttonWidth, buttonHeight);
         searchByGenreButton.setBounds(startX, startY + 6 * (buttonHeight + 10), buttonWidth, buttonHeight);
         borrowBookButton.setBounds(startX, startY + 7 * (buttonHeight + 10), buttonWidth, buttonHeight);
         returnBookButton.setBounds(startX, startY + 8 * (buttonHeight + 10), buttonWidth, buttonHeight);
         reserveBookButton.setBounds(startX, startY + 9 * (buttonHeight + 10), buttonWidth, buttonHeight);
    	// Add action listeners to each button
         	backButton.addActionListener(e -> {
         		
         		dispose();
         		new LoginUI("Librarian");
         		});
         
         
    	    addLibrarianButton.addActionListener(e -> {
    	    	// Close the current window
    	        dispose();

    	        // Open the registration form for adding a librarian
    	        RegistrationForm registrationForm = new RegistrationForm("Librarian",this);
    	        registrationForm.setVisible(true);
    	        });

    	    addUserButton.addActionListener(e -> {
    	        dispose();
    	        RegistrationForm registrationForm=new RegistrationForm("User",this);
    	        registrationForm.setVisible(true);
    	    });

    	    checkInfoButton.addActionListener(e -> {
    	    	System.out.println("Check Information button clicked"); // Debugging output
                librarian.Check_Information();
    	    });

    	    searchByTitleButton.addActionListener(e -> {
    	    	String title = JOptionPane.showInputDialog(null, "Enter book title:");
                if (title != null && !title.isEmpty()) {
                    librarian.SearchBookByTitle(title);
                } else if(title==null && title.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please enter a valid book title.");
                }
    	    });
    	    
    	    addBookButton.addActionListener(e -> {
    	        new AddBookWindow();

                });
    	    
    	    searchByAuthorButton.addActionListener(e -> {
    	    	String author = JOptionPane.showInputDialog(null, "Enter book author:");
                if (author != null && !author.isEmpty()) {
                    librarian.SearchBookByAuthor(author);
                } else if(author==null && author.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please enter a valid book title.");
                }  	    });

    	    searchByGenreButton.addActionListener(e -> {
    	    	String genre = JOptionPane.showInputDialog(null, "Enter book genre:");
                if (genre != null && !genre.isEmpty()) {
                    librarian.SearchBookByGenre(genre);
                } else if(genre==null && genre.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please enter a valid book title.");
                }   	    });

    	    borrowBookButton.addActionListener(e -> {
    	    	String title = JOptionPane.showInputDialog(null, "Enter book title to borrow:");
                if (title != null && !title.isEmpty()) {
                    librarian.BorrowBook(title);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid book title to borrow.");
                }
            });

    	    returnBookButton.addActionListener(e -> {
    	    	String title = JOptionPane.showInputDialog(null, "Enter book title to return:");
                if (title != null && !title.isEmpty()) {
                    librarian.ReturnBook(title);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid book title to return.");
                }
            });

    	    reserveBookButton.addActionListener(e -> {
    	    	String title = JOptionPane.showInputDialog(null, "Enter book title to reserve:");
                if (title != null && !title.isEmpty()) {
                    librarian.ReserveBook(title);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid book title to reserve.");
                }
            });

    	    // Add buttons to the frame
    	    add(addLibrarianButton);
    	    add(addUserButton);
    	    add(checkInfoButton);
    	    add(searchByTitleButton);
    	    add(searchByAuthorButton);
    	    add(searchByGenreButton);
    	    add(borrowBookButton);
    	    add(returnBookButton);
    	    add(reserveBookButton);
            add(addBookButton);
            add(backButton);
    	    // Repaint the frame to reflect changes
    	    revalidate();
    	    repaint();
    	    addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                   
                    FileIO.writeBooksToFile(Book.getBook(), "book2.txt");
                }
            });
    }
    public void displayUserOptions(User user) {
        setTitle("User Options");
        getContentPane().removeAll(); // Remove all existing components

        // Create buttons for each option
        JButton searchByTitleButton = new JButton("Search Book By Title");
        JButton searchByAuthorButton = new JButton("Search Book By Author");
        JButton searchByGenreButton = new JButton("Search Book By Genre");
        JButton borrowBookButton = new JButton("Borrow Book");
        JButton returnBookButton = new JButton("Return Book");
        JButton reserveBookButton = new JButton("Reserve Book");
        JButton checkInfoButton = new JButton("Check Information");
        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 10, 80, 30);

        // Set bounds for each button
        int buttonWidth = 200;
        int buttonHeight = 30;
        int startX = (getWidth() - buttonWidth) / 2; // Center horizontally
        int startY = (getHeight() - (7 * buttonHeight + 6 * 10)) / 2; // Center vertically

        searchByTitleButton.setBounds(startX, startY, buttonWidth, buttonHeight);
        searchByAuthorButton.setBounds(startX, startY + (buttonHeight + 10), buttonWidth, buttonHeight);
        searchByGenreButton.setBounds(startX, startY + 2 * (buttonHeight + 10), buttonWidth, buttonHeight);
        borrowBookButton.setBounds(startX, startY + 3 * (buttonHeight + 10), buttonWidth, buttonHeight);
        returnBookButton.setBounds(startX, startY + 4 * (buttonHeight + 10), buttonWidth, buttonHeight);
        reserveBookButton.setBounds(startX, startY + 5 * (buttonHeight + 10), buttonWidth, buttonHeight);
        checkInfoButton.setBounds(startX, startY + 6 * (buttonHeight + 10), buttonWidth, buttonHeight);

     // Add action listeners to each button
        
        backButton.addActionListener(e -> {
            dispose();
            new LoginUI("User");
        });
        
        
        checkInfoButton.addActionListener(e -> {
            System.out.println("Check Information button clicked"); // Debugging output
            user.Check_Information();
        });

        searchByTitleButton.addActionListener(e -> {
            String title = JOptionPane.showInputDialog(null, "Enter book title:");
            if (title != null && !title.isEmpty()) {
                user.SearchBookByTitle(title);
            } else if(title==null && title.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter a valid book title.");
            }
        });

        searchByAuthorButton.addActionListener(e -> {
            String author = JOptionPane.showInputDialog(null, "Enter book author:");
            if (author != null && !author.isEmpty()) {
                user.SearchBookByAuthor(author);
            } else if(author==null && author.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter a valid book title.");
            }
        });

        searchByGenreButton.addActionListener(e -> {
            String genre = JOptionPane.showInputDialog(null, "Enter book genre:");
            if (genre != null && !genre.isEmpty()) {
                user.SearchBookByGenre(genre);
            } else if(genre==null && genre.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter a valid book title.");
            }
        });

        borrowBookButton.addActionListener(e -> {
            String title = JOptionPane.showInputDialog(null, "Enter book title to borrow:");
            if (title != null && !title.isEmpty()) {
                user.BorrowBook(title);
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a valid book title to borrow.");
            }
        });

        returnBookButton.addActionListener(e -> {
            String title = JOptionPane.showInputDialog(null, "Enter book title to return:");
            if (title != null && !title.isEmpty()) {
                user.ReturnBook(title);
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a valid book title to return.");
            }
        });

        reserveBookButton.addActionListener(e -> {
            String title = JOptionPane.showInputDialog(null, "Enter book title to reserve:");
            if (title != null && !title.isEmpty()) {
                user.ReserveBook(title);
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a valid book title to reserve.");
            }
        });

        // Add buttons to the frame
        add(checkInfoButton);
        add(searchByTitleButton);
        add(searchByAuthorButton);
        add(searchByGenreButton);
        add(borrowBookButton);
        add(returnBookButton);
        add(reserveBookButton);
        add(backButton);
        // Repaint the frame to reflect changes
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
               
                FileIO.writeBooksToFile(Book.getBook(), "book2.txt");
            }
        });
        revalidate();
        repaint();
    }

}
class AddBookWindow extends JFrame implements ActionListener {
    JTextField titleField, authorField, genreField, isbnField;
    

    public AddBookWindow() {
        setTitle("Add Book");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10)); // GridLayout with 5 rows, 2 columns, and gaps

        JLabel titleLabel = new JLabel("Title:");
        titleField = new JTextField();
        JLabel authorLabel = new JLabel("Author:");
        authorField = new JTextField();
        JLabel genreLabel = new JLabel("Genre:");
        genreField = new JTextField();
        JLabel isbnLabel = new JLabel("ISBN:");
        isbnField = new JTextField();

        add(titleLabel);
        add(titleField);
        add(authorLabel);
        add(authorField);
        add(genreLabel);
        add(genreField);
        add(isbnLabel);
        add(isbnField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        add(submitButton);

        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton sourceButton = (JButton) e.getSource();
            if (sourceButton.getText().equals("Submit")) {
                // Get the book details from text fields
                String title = titleField.getText();
                String author = authorField.getText();
                String genre = genreField.getText();
                int isbn = Integer.parseInt(isbnField.getText());
                Book b11=new Book(isbn,title,author,genre,true,false);
                
                Librarian L=new Librarian("",0,"",0);
                L.AddBooks(b11);

                // Close the add book window
                dispose();
            }
        }
    }
}
class RegistrationForm extends JFrame implements ActionListener {
    /* instance variables for the name field, age field, gender combo box, register button, and a string to store the user type.*/
	 JTextField nameField;
     JTextField ageField;
     JComboBox<String> genderComboBox;
    JButton submitButton;
    String userType;
    JLabel errorMessage;
    JFrame previousWindow;
    boolean v;
    public RegistrationForm(String userType) {
    	v=true;
        this.userType = userType;
        setTitle("Registration (" + userType + ")");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(200, 150, 80, 20);
        nameField = new JTextField();
        nameField.setBounds(290, 150, 250, 30);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(200, 200, 80, 20);
        ageField = new JTextField();
        ageField.setBounds(290, 200, 250, 30);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(200, 250, 80, 20);
        String[] genders = {"Male", "Female", "Batata"};
        genderComboBox = new JComboBox<>(genders);
        genderComboBox.setBounds(290, 250, 250, 30);
        
        submitButton = new JButton("Submit");
        submitButton.setBounds(300, 320, 150, 40);
        submitButton.setBackground(new Color(66, 134, 244));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.addActionListener(this);

        add(nameLabel);
        add(nameField);
        add(ageLabel);
        add(ageField);
        add(genderLabel);
        add(genderComboBox);
        add(submitButton);

        setLocationRelativeTo(null);
        
        setVisible(true);
    }
    public RegistrationForm(String userType, JFrame previousWindow) {
    	v=false;
    	this.userType = userType;
        this.previousWindow = previousWindow; // Set reference to the previous window

        setTitle("Registration (" + userType + ")");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(200, 150, 80, 20);
        nameField = new JTextField();
        nameField.setBounds(290, 150, 250, 30);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(200, 200, 80, 20);
        ageField = new JTextField();
        ageField.setBounds(290, 200, 250, 30);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(200, 250, 80, 20);
        String[] genders = {"Male", "Female", "Batata"};
        genderComboBox = new JComboBox<>(genders);
        genderComboBox.setBounds(290, 250, 250, 30);
        
        submitButton = new JButton("Submit");
        submitButton.setBounds(300, 320, 150, 40);
        submitButton.setBackground(new Color(66, 134, 244));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.addActionListener(this);

        add(nameLabel);
        add(nameField);
        add(ageLabel);
        add(ageField);
        add(genderLabel);
        add(genderComboBox);
        add(submitButton);

        setLocationRelativeTo(null);
        
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
        	if(userType.equals("User")) {
        		if(v) {
        		String name = nameField.getText();
        		String ageText = ageField.getText();
            if (name.isEmpty() || ageText.isEmpty()) {
            	JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean userExists = checkIfUserExists(name);
            if (userExists) {
                JOptionPane.showMessageDialog(this, "User with the same name already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                nameField.setText("");
                return; // Do not proceed with registration
            }
            // Proceed with registration
            int age = Integer.parseInt(ageField.getText());
            try {
                age = Integer.parseInt(ageText);
                if (age < 18) {
                    throw new IllegalArgumentException("Age must be 18 or above.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid age!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "The age must be 18 or above!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String gender = (String) genderComboBox.getSelectedItem();
            int libraryCardNumber=generateNumber();
            User newUser=new User(name,age,gender,libraryCardNumber);
            User.getUser().add(newUser);
         // Show library card number to user
            JOptionPane.showMessageDialog(this, "Your library card number is: " + libraryCardNumber, "Library Card Number", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(this, "User registered successfully!");
            dispose(); // Close the registration form
        		}
        		else {
        			String name = nameField.getText();
                    String ageText = ageField.getText();
                    
                    if (name.isEmpty() || ageText.isEmpty()) {
                    	JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    boolean userExists = checkIfUserExists(name);
                    if (userExists) {
                        JOptionPane.showMessageDialog(this, "User with the same name already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                        nameField.setText("");
                        return; // Do not proceed with registration
                    }
                    // Proceed with registration
                    
                    
                    int age = Integer.parseInt(ageField.getText());
                    try {
                        age = Integer.parseInt(ageText);
                        if (age < 18) {
                            throw new IllegalArgumentException("Age must be 18 or above.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Please enter a valid age!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(this, "The age must be 18 or above!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String gender = (String) genderComboBox.getSelectedItem();
                    Librarian l1=new Librarian(name,age,gender,0);
                    l1.Add_User() ;              
                 
                    dispose(); // Close the registration form
        		}
        	
            if(v) {
            	new libgui();
            }
            else {
                previousWindow.setVisible(true);

            }
            
        	}
        	else {
        		if(!v) {
        			String name = nameField.getText();
                    String ageText = ageField.getText();
                    if (name.isEmpty() || ageText.isEmpty()) {
                    	JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    boolean librarianExists = checkIfLibrarianExists(name);
                    if (librarianExists) {
                        JOptionPane.showMessageDialog(this, "Librarian with the same name already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                        nameField.setText("");
                        return; // Do not proceed with registration
                    }
                    // Proceed with registration
                    int age = Integer.parseInt(ageField.getText());
                    try {
                        age = Integer.parseInt(ageText);
                        if (age < 18) {
                            throw new IllegalArgumentException("Age must be 18 or above.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Please enter a valid age!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(this, "The age must be 18 or above!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String gender = (String) genderComboBox.getSelectedItem();
                    
                    
                    
                   
                    Librarian newLibrarian=new Librarian(name,age,gender,0);
                    newLibrarian.AddLibrarian();
                    
                 
                    dispose(); // Close the registration form
        		}
        		else {
        			String name = nameField.getText();
                    String ageText = ageField.getText();
                    if (name.isEmpty() || ageText.isEmpty()) {
                    	JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    boolean librarianExists = checkIfLibrarianExists(name);
                    if (librarianExists) {
                        JOptionPane.showMessageDialog(this, "Librarian with the same name already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                        nameField.setText("");
                        return; // Do not proceed with registration
                    }
                    // Proceed with registration
                    int age = Integer.parseInt(ageField.getText());
                    try {
                        age = Integer.parseInt(ageText);
                        if (age < 18) {
                            throw new IllegalArgumentException("Age must be 18 or above.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Please enter a valid age!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(this, "The age must be 18 or above!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String gender = (String) genderComboBox.getSelectedItem();
                    int employeeID=generateNumber();
                    
                    
                   
                    Librarian newLibrarian=new Librarian(name,age,gender,employeeID);
                    Librarian.getLibrarian().add(newLibrarian);
                    JOptionPane.showMessageDialog(this, "Your Employee ID is: " + employeeID, "Employee ID", JOptionPane.INFORMATION_MESSAGE);
                    
                    JOptionPane.showMessageDialog(this, "Librarian registered successfully!");
            
                 
                    dispose(); // Close the registration form
        		}
                if(v) {
                	new libgui();
                }
                else {
                    previousWindow.setVisible(true);

                }
        	}
            
        }
    }
    private int generateNumber() {
    	Random random=new Random();
    	return random.nextInt(9000)+1000;
    }
    private boolean checkIfUserExists(String name) {
        for (User user : User.getUser()) {
            if (user.getName().equalsIgnoreCase(name)) {
                return true; // User with the same name found
            }
        }
        return false; // User with the same name not found
    }
    private boolean checkIfLibrarianExists(String name) {
    	for (Librarian librarian : Librarian.getLibrarian()) {
            if (librarian.getName().equalsIgnoreCase(name)) {
                return true; // Librarian with the same name found
            }
        }
        return false; // Librarian with the same name not found
    }
}
