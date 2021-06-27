package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBException;
import model.dao.ContractsDao;
import model.entities.Contracts;

public class ContractsJDBC implements ContractsDao {

	private Connection conn;

	public ContractsJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Contracts obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Contracts obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeById(Integer Id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM contracts WHERE idContracts = ?");
			st.setInt(1, Id);
			int rows = st.executeUpdate();
			if(rows > 0) {
				System.out.println("Rows affected: " + rows);
			}else {
				System.out.println("no lines affected!");
			}
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
	}

	@Override
	public Contracts findById(Integer Id) {

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM contracts WHERE idContracts = ?");
			st.setInt(1, Id);
			rs = st.executeQuery();
			if (rs.next()) {
				Contracts obj = new Contracts();
				obj.setContractId(rs.getInt("idContracts"));
				obj.setInitialDate(rs.getDate("initialDate"));
				obj.setFinalDate(rs.getDate("finalDate"));
				obj.setTotalValue(rs.getDouble("totalValue"));
				return obj;
			}
			return null;

		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}

	}

}
