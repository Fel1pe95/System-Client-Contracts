package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DBException;
import gui.util.Alerts;
import javafx.scene.control.Alert.AlertType;
import model.dao.ContractsDao;
import model.entities.Contracts;

public class ContractsJDBC implements ContractsDao {

	private Connection conn;

	public ContractsJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Contracts obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTo contracts  (idContracts, clientId, initialDate, finalDate, totalValue) VALUES (?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, obj.getContractId());
			st.setInt(2, obj.getClientId());
			st.setDate(3, new java.sql.Date(obj.getInitialDate().getTime()));
			st.setDate(4, new java.sql.Date(obj.getFinalDate().getTime()));
			st.setDouble(5, obj.getTotalValue());

			int rowsaffecteds = st.executeUpdate();

			if (rowsaffecteds > 0) {
				Alerts.showAlert("Novo Contrato", null, "Contrato realizado com sucesso!" + obj.toString(), AlertType.INFORMATION);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("unexpected Error! no rows Affected!");
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Contracts obj) {

		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("Update contracts SET finalDate=?, totalValue=? WHERE idContracts = ?");

			st.setDate(1, new java.sql.Date(obj.getFinalDate().getTime()));
			st.setDouble(2, obj.getTotalValue());
			st.setInt(3, obj.getContractId());

			int rows = st.executeUpdate();
			if (rows > 0) {
				System.out.println("rows Affected: " + rows);
			}

		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
	}
	
	@Override
	public void removeById(Integer Id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM contracts WHERE idContracts = ?");
			st.setInt(1, Id);
			int rows = st.executeUpdate();
			if (rows > 0) {
				System.out.println("Rows affected: " + rows);
			} else {
				System.out.println("no lines affected!");
			}
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} finally {
			DB.closeStatement(st);
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
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	@Override
	public List<Contracts> findAllByIdClient(Integer id) {
		
			PreparedStatement st = null;
			ResultSet rs = null;
			try {
				st = conn.prepareStatement("SELECT * FROM contracts WHERE clientId = ?");
				st.setInt(1, id);
				rs = st.executeQuery();
				List<Contracts> list = new ArrayList<>();
				while (rs.next()) {
					Contracts contract = InstatiateContract(rs);
					list.add(contract);
				}
				return list;
			} catch (SQLException e) {
				throw new DBException(e.getMessage());
			} finally {
				DB.closeStatement(st);
				DB.closeResultSet(rs);
			
			}
	}
	
	public Contracts InstatiateContract(ResultSet rs) throws SQLException {
		Contracts obj = new Contracts();
		obj.setContractId(rs.getInt("idContracts"));
		obj.setClientId(rs.getInt("clientId"));
		obj.setInitialDate(new java.util.Date(rs.getTimestamp("initialDate").getTime()));
		obj.setFinalDate(new java.util.Date(rs.getTimestamp("finalDate").getTime()));
		obj.setTotalValue(rs.getDouble("totalValue"));
		return obj;
	}

}
