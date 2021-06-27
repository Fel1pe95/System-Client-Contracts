package model.dao;

import java.util.List;

import model.entities.Client;
import model.entities.Contracts;

public interface ContractsDao {

	void insert(Contracts obj);
	void update(Contracts obj);
	void removeById(Integer Id);
	Client findById(Integer Id);
	List<Client>findAll();
}
