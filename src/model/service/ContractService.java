package model.service;

import java.util.List;

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
	
	public List<Contracts> findAll(Integer id) {
		return dao.findAllByIdClient(id);
	}
}
