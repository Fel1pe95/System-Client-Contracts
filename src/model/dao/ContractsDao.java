package model.dao;

import java.util.List;

import model.entities.Contracts;

public interface ContractsDao {

	void insert(Contracts obj);
	void update(Contracts obj);
	void removeById(Integer Id);
	Contracts findById(Integer Id);
	List<Contracts> findAllByIdClient(Integer id);
	
}
