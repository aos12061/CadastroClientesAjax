package br.com.adn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.adn.dominio.FabricaDeConexoes;
import br.com.adn.models.Cliente;

public class ClienteDAO {

	private final Connection connection;
	private ResultSet rs;

	private static List<Cliente> lstClientes;
	private static Cliente cliente;
	
	public ClienteDAO() throws SQLException {
		
		this.connection = FabricaDeConexoes.getConnection();
		
	}

	public void remove(Integer Id) {
		
		String sql = "DELETE FROM clientes WHERE Id = ?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, Id);
			stmt.execute();
			stmt.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public Cliente alterar(Cliente cliente) {
		
		String sql = "UPDATE clientes SET nome = ?, email = ? WHERE Id = ?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, cliente.getNome());				
			stmt.setString(2, cliente.getEmail());
			stmt.setInt(3, cliente.getId());
			stmt.executeUpdate();
			stmt.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return cliente;
		
	}
	
	public Cliente buscar(Integer Id) {
		
		String sql = "SELECT * FROM clientes WHERE Id = " + Id;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			cliente = new Cliente();
			
			while ( rs.next() ) {
				cliente.setId(rs.getInt("Id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setEmail(rs.getString("email"));
		    }

			stmt.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	
		return cliente;
		
	}

	public List<Cliente> listar() {
		
		String sql = "SELECT * FROM clientes";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			lstClientes = new ArrayList<>();
			
			while ( rs.next() ) {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("Id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setEmail(rs.getString("email"));
				
				lstClientes.add(cliente);
		    }

			stmt.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	
		return lstClientes;
		
	}
	
	public Cliente salvar(Cliente cliente) {
		
		String sql = "INSERT INTO clientes (nome, email) VALUES(?, ?)";
	
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// create the mysql insert preparedstatement
			stmt.setString (1, cliente.getNome());
			stmt.setString (2, cliente.getEmail());
			
			stmt.execute();
			stmt.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return cliente;
		
	}
	
}
