package model.dao.impl;

import java.sql.Connection;
import java.util.List;

import model.dao.ClientDao;
import model.entities.Client;

public class ClientJDBC implements ClientDao{

	private Connection conn;
	
	public ClientJDBC(Connection conn) {
		this.conn=conn;
	}
	
	@Override
	public void insert(Client obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Client obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeByRegistration(Integer registration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Client findByRegistration(Integer registration) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> findByContracts() {
		// TODO Auto-generated method stub
		return null;
	}

}
