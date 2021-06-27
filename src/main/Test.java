package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.entities.Client;
import model.entities.Contracts;

public class Test {

	public static void main(String[] args) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Client c = new Client("luis", "@gmail.com", 135742, 5995, new Contracts(123,sdf.parse("28/02/2021"), sdf.parse("29/02/2021"), 250.00));
System.out.println(c);
	}

}
