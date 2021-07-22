package model.service;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.LocationsDao;
import model.entities.Locations;

public class LocationsService {

	private LocationsDao dao = DaoFactory.createLocationsDao();
	
	public void saveLocations(List<Locations> list) {
		for(Locations loc : list) {
			dao.insert(loc);
		}
	}
	
	public List<Locations> findAll(Integer id){
		return dao.findAllByContractId(id);
	}
	
}
