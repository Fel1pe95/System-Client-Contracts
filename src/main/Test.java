package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.dao.ContractsDao;
import model.dao.DaoFactory;
import model.entities.Contracts;

public class Test {

	public static void main(String[] args) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		/* encontra um contrato pelo seu endereço ID */
		ContractsDao contracts = DaoFactory.createContractsJDBC();
		Contracts contract = contracts.findById(1234);
		System.out.println(contract);

		/*Deleta um contrato pelo seu endereço ID*/
		contracts.removeById(1357425);
		
		/*Inere um contrato no banco de dados*/
		//contracts.insert(new Contracts(1357425,sdf.parse("28/02/2021") , sdf.parse("03/03/2021"), 350.50));
		
	}

}
