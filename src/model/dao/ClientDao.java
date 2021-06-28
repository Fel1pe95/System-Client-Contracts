package model.dao;

import java.util.List;

import model.entities.Client;

public interface ClientDao {

	void insert(Client obj);
	void update(Client obj);
	void removeByRegistration(Integer registration);
	Client findByRegistration(Integer registration);
	List<Client>findAll();
	
	
}
