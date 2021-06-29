package model.dao.impl;

import java.sql.Connection;
import java.util.List;

import model.dao.ProductDao;
import model.entities.Product;

public class ProductJDBC implements ProductDao{

	private Connection conn;
	
	public ProductJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Product obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Product obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
