package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DBException;
import model.dao.ProductDao;
import model.entities.Product;

public class ProductJDBC implements ProductDao {

	private Connection conn;

	public ProductJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Product obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO product (productId, productName, productValue) VALUES (?, ?, ?)");
			st.setInt(1, obj.getId());
			st.setString(2, obj.getName());
			st.setDouble(3, obj.getValue());

			int rows = st.executeUpdate();
			if (rows > 0) {
				System.out.println("Product registered sucefully!");
			}
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Product obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE product SET productValue=? WHERE productId = ?");

			st.setInt(1, obj.getId());

			int rows = st.executeUpdate();
			if (rows > 0) {
				System.out.println("rows affecteds: " + rows);
			}
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void removeById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM product WHERE productId = ?");
			st.setInt(1, id);
			int rows = st.executeUpdate();
			if(rows > 0) {
				System.out.println("rows affected: " + rows);
			}
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public Product findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM product WHERE productId = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Product product = new Product();
				product.setName(rs.getString("productName"));
				product.setId(rs.getInt("productId"));
				product.setValue(rs.getDouble("productValue"));
				
				return product;
			}
			return null;
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Product> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM product");
			rs = st.executeQuery();
			List<Product>list = new ArrayList<>();
			while(rs.next()) {
				Product product = InstatiateProduct(rs);
				list.add(product);
			}
			return list;
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	public Product InstatiateProduct(ResultSet rs) throws SQLException {
		Product obj = new Product();
		obj.setName(rs.getString("productName"));
		obj.setId(rs.getInt("productId"));
		obj.setValue(rs.getDouble("productValue"));
		return obj;
	}

}
