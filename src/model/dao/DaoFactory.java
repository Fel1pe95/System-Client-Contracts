package model.dao;

import db.DB;
import model.dao.impl.ClientJDBC;
import model.dao.impl.ContractsJDBC;
import model.dao.impl.ProductJDBC;

public class DaoFactory {

	public static ContractsDao createContractsDao() {
		return new ContractsJDBC(DB.getConnection());
	}
	
	public static ClientDao createClientDao() {
		return new ClientJDBC(DB.getConnection());
	}
	
	public static ProductDao createProductDao() {
		return new ProductJDBC(DB.getConnection());
	}
}
