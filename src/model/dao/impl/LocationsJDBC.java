package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
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
			if (rows > 0) {
				System.out.println("sucess!");
			}

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
	public List<Locations> findAllByContractId(Integer id) {

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM locations Where contractId = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			List<Locations> list = new ArrayList<>();
			while (rs.next()) {
				Locations loc = InstatiateLocation(rs);
				list.add(loc);
			}
			return list;
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	public Locations InstatiateLocation(ResultSet rs) throws SQLException {
		Locations obj = new Locations();
		obj.setProductName(rs.getString("productName"));
		obj.setCod(rs.getInt("productId"));
		obj.setQuantity(rs.getInt("productQuantity"));
		obj.setTotalValue(rs.getDouble("totalValue"));
		return obj;
	}
}
