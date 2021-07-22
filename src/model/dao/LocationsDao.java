package model.dao;

import java.util.List;

import model.entities.Contracts;
import model.entities.Locations;

public interface LocationsDao {

	void insert(Locations obj);

	void update(Locations obj);

	void removeById(Integer Id);

	Contracts findById(Integer Id);

	List<Locations> findAllByContractId(Integer id);
}
