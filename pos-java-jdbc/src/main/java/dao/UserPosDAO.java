package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.BeanUserFone;
import model.Telefone;
import model.UserPosJava;

public class UserPosDAO {

	private Connection connection;
	
	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(UserPosJava userposjava) {
		try{
			String sql = "insert into userposjava (nome, email) values (?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, userposjava.getNome());
			insert.setString(2, userposjava.getEmail());
			insert.execute();
			connection.commit(); //salva no banco
		} catch(Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}		
	}
	
	public void salvarTelefone(Telefone telefone, long userId) {
		try{
			String sql = "insert into telefoneuser (numero, tipo, usuariopessoa) values (?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, telefone.getNumero());
			insert.setString(2, telefone.getTipo());
			insert.setLong(3, userId);
			insert.execute();
			connection.commit(); //salva no banco
		} catch(Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}		
	}
	
	public List<UserPosJava> listar(){
		List<UserPosJava> users = new ArrayList<UserPosJava>();
		try {
			String sql = "select * from userposjava";
			PreparedStatement query = connection.prepareStatement(sql);
			ResultSet resultSet = query.executeQuery();
			
			while(resultSet.next()) {
				UserPosJava userPosJava = new UserPosJava();
				userPosJava.setId(resultSet.getLong("id"));
				userPosJava.setNome(resultSet.getString("nome"));
				userPosJava.setEmail(resultSet.getString("email"));
				users.add(userPosJava);				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public List<BeanUserFone> listarUserFone(){
		List<BeanUserFone> users = new ArrayList<BeanUserFone>();
		try {
			String sql = "select nome, numero, email  from telefoneuser as fone "
					+ "inner join userposjava as userpos "
					+ "on fone.usuariopessoa = userpos.id";
			PreparedStatement query = connection.prepareStatement(sql);
			ResultSet resultSet = query.executeQuery();
			
			while(resultSet.next()) {
				BeanUserFone beanUserFone = new BeanUserFone();
				beanUserFone.setTelefone(resultSet.getString("numero"));
				beanUserFone.setNome(resultSet.getString("nome"));
				beanUserFone.setEmail(resultSet.getString("email"));
				users.add(beanUserFone);				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public UserPosJava buscarPorId(long id){
		UserPosJava userPosJava = new UserPosJava();
		
		try {
			String sqlSelect = "select * from userposjava where id = " + id;
			PreparedStatement query = connection.prepareStatement(sqlSelect);
			ResultSet resultSet = query.executeQuery();
			
			if(resultSet.next()) {		
				userPosJava.setId(resultSet.getLong("id"));
				userPosJava.setNome(resultSet.getString("nome"));
				userPosJava.setEmail(resultSet.getString("email"));				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userPosJava;
	}
	
	public void atualizar(UserPosJava userPosJava) {
	
		try {
			String sqlSelect = "select * from userposjava where id = " + userPosJava.getId();
			PreparedStatement query = connection.prepareStatement(sqlSelect);
			ResultSet resultSet = query.executeQuery();
				
			if(resultSet.next()) {
				String sqlUpdate = "update userposjava set nome = ?, email = ? where id = " + userPosJava.getId();
				PreparedStatement queryUpdate = connection.prepareStatement(sqlUpdate);
				queryUpdate.setString(1, userPosJava.getNome());
				queryUpdate.setString(2, userPosJava.getEmail());
				queryUpdate.execute();
				connection.commit(); //salva no banco
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}			
	}
	
	/*public void deletarPorUserIdUser(long id) {
		try {
			String sqlSelect = "select * from userposjava where id = " + id;
			PreparedStatement query = connection.prepareStatement(sqlSelect);
			ResultSet resultSet = query.executeQuery();
				
			if(resultSet.next()) {
				String sqlDelete = "delete from userposjava where id = " + id;
				PreparedStatement queryDelete = connection.prepareStatement(sqlDelete);
				queryDelete.executeUpdate();
				connection.commit(); //salva no banco
			}
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}	
	}*/
	
	public void deletarPorUserIdFone(long id) {
		try {
			String sql = "select nome, numero, email  from telefoneuser as fone "
					+ "inner join userposjava as userpos "
					+ "on fone.usuariopessoa = userpos.id";
			PreparedStatement query = connection.prepareStatement(sql);
			ResultSet resultSet = query.executeQuery();
				
			if(resultSet.next()) {
				String sqlDeleteFone = "delete from telefoneuser where usuariopessoa = " + id;
				String sqlDeleteUser = "delete from userposjava where id = " + id;
				
				PreparedStatement queryDeleteFone = connection.prepareStatement(sqlDeleteFone);
				queryDeleteFone.executeUpdate();
				connection.commit(); //salva no banco
				
				PreparedStatement queryDeleteUser = connection.prepareStatement(sqlDeleteUser);
				queryDeleteUser.executeUpdate();
				connection.commit(); //salva no banco
			}
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}	
	}
}
