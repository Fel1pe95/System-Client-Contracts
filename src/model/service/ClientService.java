package model.service;

import java.util.List;

import model.dao.ClientDao;
import model.dao.DaoFactory;
import model.entities.Client;

public class ClientService {

	private ClientDao dao = DaoFactory.createClientDao();

	public void save(Client obj) {
		dao.insert(obj);
	}
	public void update(Client obj) {
		dao.update(obj);
	}

	public void remove(Client obj) {
		dao.removeByRegistration(obj.getRegistration());
	}

	public List<Client> findAll() {
		return dao.findAll();
	}
	
	public Client findById(Integer id){
		return dao.findByRegistration(id);
	}
}
