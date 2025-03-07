package main;

import java.util.Scanner;

public class Executavel {

	public static void main(String[] args) {
		ConexaoBanco conexao = new ConexaoBanco();
		
		if(conexao.Conexao()!= null) {
			
		while(true) {
			
			// MENU
			System.out.println("---------- MENU ----------");
			System.out.println("Escolha uma opção");
			System.out.println("1 - Inserir cliente");
			System.out.println("2 - Remover cliente");
			System.out.println("3 - Ver clientes");
			System.out.println("4 - Atualizar clientes");
			System.out.println("5 - Sair");
			System.out.print("Opcão: ");
			Scanner sc = new Scanner(System.in);
			String opcao = sc.next();

			System.out.println(" ");
			switch (opcao) {
			case "1":					
				conexao.Inserirpessoas();
				break;

			case "2":
				conexao.RemoverPessoa();
				break;
			case "3":
				conexao.VerPessoas();
				break;
			case "4":					
				conexao.AtualizarPessoa();
				break;
			case "5":
				System.out.println("Fechando o menu...");
				return;
			default:
				System.out.println("Opção invalida");
				break;
			}		
			System.out.println("aperte B");
			sc.next();
		}
			
		}
	}
	
}



