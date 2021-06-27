package model.dao;

import db.DB;
import model.dao.impl.ClientJDBC;
import model.dao.impl.ContractsJDBC;

public class DaoFactory {

	public static ContractsDao createContractsJDBC() {
		
		return new ContractsJDBC(DB.getConnection());
	}
	
	public static ClientDao createClientJDBC() {
		
		return new ClientJDBC(DB.getConnection());
	}
}
