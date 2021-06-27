package main;

import java.text.ParseException;

import model.dao.ContractsDao;
import model.dao.DaoFactory;

public class Test {

	public static void main(String[] args) throws ParseException {

 
   /*encontra um contrato pelo seu endereço ID*/
   ContractsDao contracts = DaoFactory.createContractsJDBC();
   System.out.println(contracts.findById(1234));
	
	}

}
