package br.com.adn.dominio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaDeConexoes {
	
	private static final String BANCO_DE_DADOS = "eventosapp";
	private static final String URL = "jdbc:mysql://localhost:3306/eventosapp?useTimezone=true&serverTimezone=UTC";
	private static final String USUARIO = "root";
	private static final String SENHA = "12345";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	
	public static Connection getConnection() throws SQLException{
		
		try {
			Class.forName(DRIVER);// BD_URL = "jdbc:mysql://localhost:port/bd_name?useTimezone=true&serverTimezone=UTC";
			System.out.println(" --- CONEXÃO CRIADA COM O BANCO --- ");
			
			return DriverManager.getConnection(URL, USUARIO, SENHA);
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new SQLException(e.getMessage()); 
		}
		
	}
	
}
