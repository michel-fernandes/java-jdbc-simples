package pos_java_jdbc;

import java.util.List;

import org.junit.Test;

import conexaojdbc.SingleConnection;
import dao.UserPosDAO;
import junit.framework.TestCase;
import model.BeanUserFone;
import model.Telefone;
import model.UserPosJava;

public class TesteBancoJdbc extends TestCase{

	@Test
	public void testConnection() {
		assertNotNull(SingleConnection.getConnection());
	}
	
	@Test
	public void testDAOInsert() {
		UserPosJava userPosJava = new UserPosJava("Teste", "teste@gmail.com");	
		UserPosDAO userPosDAO = new UserPosDAO();
		userPosDAO.salvar(userPosJava);
	}
	
	@Test
	public void testDAOListar() {
		UserPosDAO userPosDAO = new UserPosDAO();
		try{
			List<UserPosJava> users = userPosDAO.listar();
			for (UserPosJava userPosJava : users) {
				System.out.println(userPosJava.toString());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testDAOListarUserFone() {
		UserPosDAO userPosDAO = new UserPosDAO();
		try{
			List<BeanUserFone> users = userPosDAO.listarUserFone();
			for (BeanUserFone beanUserFone : users) {
				System.out.println(beanUserFone.toString());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void testDAOBuscarPorId() {
		UserPosDAO userPosDAO = new UserPosDAO();
		UserPosJava userPosJava = new UserPosJava();
		try{
			userPosJava = userPosDAO.buscarPorId(12);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		assertEquals("Pollyana", userPosJava.getNome());
	}
	
	@Test
	public void testDAOTualizar() {
		UserPosDAO userPosDAO = new UserPosDAO();
		UserPosJava userPosJava = new UserPosJava();
		try{
			userPosJava = userPosDAO.buscarPorId(11);
			userPosJava.setNome("Michel fernandes");
			userPosDAO.atualizar(userPosJava);			
		}catch(Exception e) {
			e.printStackTrace();
		}
		assertEquals("Michel fernandes", userPosJava.getNome());
	}
	
	@Test
	public void testDAODeletarFoneEUser() {
		long idUser = 15;
		try{
			UserPosDAO userPosDAO = new UserPosDAO();
			userPosDAO.deletarPorUserIdFone(idUser);		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testDAOInsertTelefone() {
		long idUser = 15;
		UserPosJava userPosJava = new UserPosJava();
		UserPosDAO userPosDAO = new UserPosDAO();
		Telefone telefone = new Telefone("(99)99999-99999", "Teste", idUser);
		try{
			userPosJava = userPosDAO.buscarPorId(idUser);
			userPosDAO.salvarTelefone(telefone, userPosJava.getId());		
		}catch(Exception e) {
			e.printStackTrace();
		}
		assertEquals(idUser, telefone.getUserId());
	}
}
