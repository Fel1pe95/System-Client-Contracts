package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import db.DBException;
import model.dao.LocationsDao;
import model.entities.Contracts;
import model.entities.Locations;

public class LocationsJDBC implements LocationsDao {
	private Connection conn;

	public LocationsJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Locations obj) {
		PreparedStatement st = null;
	
		try {
			st = conn.prepareStatement(
					"INSERT into locations (contractId, productId, productName, productQuantity, totalValue) VALUES (?, ?, ?, ?, ?) ");
        st.setInt(1, obj.getId());
        st.setInt(2, obj.getCod());
        st.setString(3, obj.getProductName());
        st.setInt(4, obj.getQuantity());
        st.setDouble(5, obj.getTotalValue());
        
        int rows = st.executeUpdate();
        if(rows > 0 ) {System.out.println("sucess!");}
        
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}

	}

	@Override
	public void update(Locations obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeById(Integer Id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Contracts findById(Integer Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Locations> findAllByIdContract(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}
