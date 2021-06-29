package test;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entities.Product;

public class Teste {

	public static void main(String[] args) {
		
		
		ProductDao product = DaoFactory.createProductDao();
		// product.insert(new Product("fanta", 560, 6.50));
		
		List<Product> list = product.findAll();
		for(Product p : list) {
			System.out.println(p);
		}
	}

}
