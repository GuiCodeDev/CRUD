package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ConexaoBanco {
	static String URL = "jdbc:mysql://localhost:3306/bancoteste";
	static String USER = "root";
	static String PASSWORD = "1234567";
	
	
	// Fazendo a conexao com o banco
	public  Connection Conexao(){
		Connection conectar = null;
		try {
			conectar = DriverManager.getConnection(URL, USER, PASSWORD);
		}
		catch (SQLException e){
			System.err.print("Erro ao conectar com o banco de dados");
		}
		
		return conectar;
	}
	
	// Inserir pessoas
	public  void Inserirpessoas() {
		String sql = "INSERT INTO cliente (nome, idade, cpf, numero) VALUES(?, ?, ?, ?)";
		
		try (Connection connection = Conexao();
			PreparedStatement cursor = connection.prepareStatement(sql)){
			Scanner sc = new Scanner(System.in);
			System.out.print("Digite seu nome: ");
			String nome = sc.nextLine();
			System.out.print("Digite sua idade: ");
			String idade = sc.nextLine();
			System.out.print("Digite seu cpf: ");
			String cpf = sc.nextLine();
			System.out.print("Digite seu numero: ");
			String numero = sc.nextLine();

			cursor.setString(1, nome);
			cursor.setString(2, idade);
			cursor.setString(3, cpf);
			cursor.setString(4, numero);
			int linhasAfetadas = cursor.executeUpdate();
			System.out.println("Inserção realizada com sucesso! Linhas afetadas: "+ linhasAfetadas);
		}
		catch (SQLException e) {
			System.err.println("Erro ao inserir dados:" + e.getMessage());
		}
	}
	
	public  void RemoverPessoa() {
		String sql = "DELETE FROM cliente where id = ?";
		
		try (Connection connection = Conexao();
		PreparedStatement cursor = connection.prepareStatement(sql)) {
			Scanner sc = new Scanner(System.in);
			System.out.print("Digite o id da pessoa que deseja remover: ");
			int id = sc.nextInt();
			cursor.setInt(1, id);
			


			int linhasAfetadas = cursor.executeUpdate();
			
			if (linhasAfetadas > 0) {
				System.out.println("Remoção realizada com sucesso! Linhas afetadas: " + linhasAfetadas);
			} else {
				System.out.println("Nenhuma pessoa encontrada com o ID fornecido");
			}
			
		} catch (SQLException e) {
			System.err.println("ERRO AO REMOVER PESSOA: " + e.getMessage());
		}
	}
	
	
	//Ler Pessoas 
	
	public  void VerPessoas () {
		String sql = "SELECT * FROM cliente";
				
		try (Connection connection = Conexao();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql))
				
				{
					while(resultSet.next()) {
						int id = resultSet.getInt("id");
						String nome = resultSet.getString("nome");
						String idade = resultSet.getString("idade");
						String numero = resultSet.getString("numero");
						String cpf = resultSet.getString("cpf");
						System.out.printf("ID %d - Nome %s Idade %s Numero %s Cpf %s", id, nome, idade, numero, cpf);
						
						
					}
				
				}catch(SQLException e) {
					
					System.err.println("Erro ao ler dado: " +e.getMessage());
				}
	}
	
	//Atualizar Pessoas 
	
	public  void AtualizarPessoa () {
		String sql = "UPDATE cliente SET nome = ?, idade = ?, numero = ?, cpf = ? WHERE id = ? ";
		
		try(Connection connection = Conexao();
				PreparedStatement cursor = connection.prepareStatement(sql)){
					Scanner sc = new Scanner(System.in);

					System.out.print("Digite o id do cliente que deseja atualizar: ");
					int id = sc.nextInt();
					System.out.print("Digite o novo nome do cliente: ");
					String nome = sc.nextLine();
					System.out.print("Digite a novo idade do cliente: ");
					String idade = sc.nextLine();
					System.out.print("Digite o novo numero do cliente: ");
					String numero = sc.nextLine();
					System.out.print("Digite o novo cpf do cliente: ");
					String cpf = sc.nextLine();

					cursor.setString(1, nome);
					cursor.setString(2, idade);
					cursor.setString(3, numero);
					cursor.setString(4, cpf);
					cursor.setInt(3, id);
				}catch (SQLException e) {
					System.err.println ("Erro ao atualizar dados: " + e.getMessage());
				}
		
	}
}
