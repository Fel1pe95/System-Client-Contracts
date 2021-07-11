package model.service;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entities.Product;

public class ProductService {

	private ProductDao dao = DaoFactory.createProductDao();

	public void save(Product obj) {
		dao.insert(obj);
	}
	public void update(Product obj) {
		dao.update(obj);
	}

	public void remove(Integer id) {
		dao.removeById(id);;
	}
	
	public Product findById(Integer id) {
		return dao.findById(id);
	}
	
	public List<Product> findAll(){
		return dao.findAll();
	}

}
