package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DBException;
import model.dao.ClientDao;
import model.entities.Client;

public class ClientJDBC implements ClientDao{

	private Connection conn;
	
	public ClientJDBC(Connection conn) {
		this.conn=conn;
	}
	
	@Override
	public void insert(Client obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO client (name, email, cpf, registration) VALUES (?, ?, ?, ?)");
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setLong(3, obj.getCpf());
			st.setInt(4, obj.getRegistration());
			int rows = st.executeUpdate();
			if(rows>0) {System.out.println("customer registered sucefully!");}
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}		
	}

	@Override
	public void update(Client obj) {

		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE client SET name=?, SET email=?, SET cpf=? WHERE registration = ?");
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setInt(3, obj.getCpf());
			st.setInt(4, obj.getRegistration());
			
			int rows = st.executeUpdate();
			if(rows > 0 ) {
				System.out.println("rows affecteds: " + rows);
			}
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}		
	}

	@Override
	public void removeByRegistration(Integer registration) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM client WHERE registration = ?");
			st.setInt(1, registration);
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
	public Client findByRegistration(Integer registration) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM client WHERE registration = ?");
			st.setInt(1, registration);
			rs = st.executeQuery();
			if(rs.next()) {
				Client client = new Client();
				client.setName(rs.getString("name"));
				client.setEmail(rs.getString("email"));
				client.setCpf(rs.getInt("cpf"));
				client.setRegistration(rs.getInt("registration"));
				return client;
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
	public List<Client> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM client");
			rs = st.executeQuery();
			List<Client>list = new ArrayList<>();
			while(rs.next()) {
				Client client = InstatiateClient(rs);
				list.add(client);
			}
			return list;
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	
	public Client InstatiateClient(ResultSet rs) throws SQLException {
		Client obj = new Client();
		obj.setName(rs.getString("name"));
		obj.setEmail(rs.getString("email"));
		obj.setCpf(rs.getInt("cpf"));
		obj.setRegistration(rs.getInt("registration"));
		return obj;
	}

}
