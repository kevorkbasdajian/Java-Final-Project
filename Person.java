package project;

public abstract class Person {
	//attributes for the abstract class Person.//
	private String name;
	private int age;
	private String gender;
	//constructor//
	public Person(String name, int age, String gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	//Getters and Setters/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	//abstract method with no implementation//
	public abstract String Check_Information();
	// the toString to be used both in the user and the librarian//
	public String toString() {
		return String.format("Name : %s, Age : %d, Gender : %s", name, age, gender);
	}
}
