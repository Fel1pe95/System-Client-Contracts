package model.service;

import model.dao.ContractsDao;
import model.dao.DaoFactory;
import model.entities.Contracts;

public class ContractService {

	private ContractsDao dao = DaoFactory.createContractsDao();
	
	public void save(Contracts obj) {
		dao.insert(obj);
	}
	public void update(Contracts obj) {
		dao.update(obj);
	}
	public void remove(Integer id) {
		dao.removeById(id);
	}
	public Contracts findById(Integer id){
		return dao.findById(id);
	}
}
