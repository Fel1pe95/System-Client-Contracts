package main;

import java.text.ParseException;

import model.dao.ContractsDao;
import model.dao.DaoFactory;
import model.entities.Contracts;

public class Test {

	public static void main(String[] args) throws ParseException {

		/* encontra um contrato pelo seu endereço ID */
		ContractsDao contracts = DaoFactory.createContractsJDBC();
		Contracts contract = contracts.findById(1234);
		System.out.println(contract);

		/*Deleta um contrato pelo seu endereço ID*/
		contracts.removeById(12345);
	}

}
