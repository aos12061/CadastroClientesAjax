package br.com.adn.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.adn.dao.ClienteDAO;
import br.com.adn.models.Cliente;

@RestController
@EnableAutoConfiguration
public class ClienteController {

	@RequestMapping(value="/clientes", method=RequestMethod.POST)
	public Cliente cadastrarCliente(@RequestBody Cliente cliente)  throws SQLException {
		ClienteDAO dao = new ClienteDAO();		
		return dao.salvar(cliente);
	}

	@RequestMapping(value="/clientes", method=RequestMethod.PUT)
	public Cliente alterarCliente(@RequestBody Cliente cliente)  throws SQLException {
		ClienteDAO dao = new ClienteDAO();		
		return dao.alterar(cliente);
	}
	
	@RequestMapping(value="/clientes", method=RequestMethod.GET)
	public List<Cliente> listarClientes() throws SQLException {
		ClienteDAO dao = new ClienteDAO();
		return dao.listar();
	}
	
	@RequestMapping(value="/clientes/{Id}", method=RequestMethod.GET)
	public Cliente buscarClientePorId(@PathVariable("Id") Integer Id) throws SQLException {
		ClienteDAO dao = new ClienteDAO();
		return dao.buscar(Id);		
	}
	
	@RequestMapping(value="/clientes/{Id}", method=RequestMethod.DELETE)
	public void excluirCliente(@PathVariable("Id") Integer Id) throws SQLException {
		ClienteDAO dao = new ClienteDAO();
		dao.remove(Id);
	}
}
