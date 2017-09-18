package fr.acceis.jpa.test.jdbc;

public class Main {
	
	public static void main(String[] args) {
		for (int i = 58; i < 10000000; i++) {
			System.out.println("\"INSERT INTO PROFESSEUR VALUES(" +i +",nomProf "+i+",prenomProf " + i +")\",");
		}
	}

}
