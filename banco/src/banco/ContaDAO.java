package banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Scanner;

import java.sql.Connection;


public class ContaDAO {
	
	public static void main(String[] args) throws SQLException
	{
		Scanner nro = new Scanner(System.in);
        int num = 0;
        while(num<=0||num>=6)
        {
        	System.out.println("##### - SISTEMA BANCÁRIO - #####");
			System.out.println("Escolha uma das opções abaixo");
			System.out.println("1 - Cadastrar conta");
			System.out.println("2 - Atualizar conta");
			System.out.println("3 - Excluir conta");
			System.out.println("4 - Listar contas");
			System.out.println("5 - Sair");
			System.out.print("DIGITE A OPÇÃO: ");
            num = nro.nextInt();
        
        if(num==1)
        	recebeConta();     		
        else if(num==2)
            recebeAtualizacaoConta();
        else if(num==3)
            recebeExcluir();
        else if(num==4)
			listagem();
        else if(num==5)
            System.exit(0);
        else
        	System.out.println("Você digitou uma opção inválida");
        }
		
	}

	public void inserir(Conta c) throws SQLException {
		  Connection conexao = FabricaDeConexao.getConnection();
		  String sql = "insert into conta" +
				"(titular,numero,agencia,limite,saldo)" +
				" values (?,?,?,?,?)";
		  PreparedStatement stmt = conexao.prepareStatement(sql);
		  stmt.setString(1,c.getTitular());
		  stmt.setInt(2, c.getConta());
		  stmt.setInt(3, c.getAgencia());
		  stmt.setDouble(4, c.getLimite());
		  stmt.setDouble(5, c.getSaldo());
		  stmt.execute();
		  stmt.close();
		  conexao.close();
		}
	
	public void atualizar(Conta c, int id) throws SQLException {
		  Connection conexao = FabricaDeConexao.getConnection();
		  String sql = "UPDATE conta SET titular='"+c.getTitular()+"', numero="+c.getConta()+", agencia="+c.getAgencia()+", limite="+c.getLimite()+", saldo="+c.getSaldo()+" WHERE id="+id+";";
		  System.out.println(sql);
		  
		  PreparedStatement stmt = conexao.prepareStatement(sql);
		  stmt.execute();
		  stmt.close();
		  conexao.close();
		}
	
	public void excluir(int id) throws SQLException {
		  Connection conexao = FabricaDeConexao.getConnection();
		  String sql = "DELETE from conta WHERE id="+id+";";
		  
		  PreparedStatement stmt = conexao.prepareStatement(sql);
		  stmt.execute();
		  stmt.close();
		  conexao.close();
		}
	
	public static void listagem() throws SQLException {
		  Connection conexao = FabricaDeConexao.getConnection();
		  String sql = "select * from conta";
		  PreparedStatement stmt = conexao.prepareStatement(sql);
		  ResultSet resultado = stmt.executeQuery();
		  while (resultado.next()) {
			  System.out.println("Id: "+resultado.getString("id"));
			  System.out.println("Títular: "+resultado.getString("titular"));
			  System.out.println("Número: "+resultado.getString("numero"));
			  System.out.println("Agência: "+resultado.getString("agencia"));
			  System.out.println("Limite: "+resultado.getString("limite"));
			  System.out.println("Saldo: "+resultado.getString("saldo"));
		  }
		  resultado.close();
		  stmt.close();
		  conexao.close();
		}


	
	
	public static void recebeConta() {
		
		Scanner titular = new Scanner(System.in);
		Scanner numero = new Scanner(System.in);
		Scanner agencia = new Scanner(System.in);
		Scanner limite = new Scanner(System.in);
		Scanner saldo = new Scanner(System.in);
		
		System.out.print("Títular: ");
		String tit = titular.nextLine();
		
		System.out.print("Número: ");
		int nro = numero.nextInt();
		
		System.out.print("Agência: ");
		int ag = agencia.nextInt();
		
		System.out.print("Limite: ");
		double lim = titular.nextDouble();
		
		System.out.print("Saldo: ");
		double sald = titular.nextDouble();
		
		  
		Conta c = new Conta(tit, nro, ag, lim, sald);
		ContaDAO dao = new ContaDAO();
		
		try {
			dao.inserir(c);
			System.out.println("Conta cadastrada com sucesso");
		}catch(SQLException e) {
			System.out.println("Erro ao cadastrar a conta");
			e.printStackTrace();
		}
	}
	
	public static void recebeAtualizacaoConta() {
		
		Scanner id = new Scanner(System.in);
		Scanner titular = new Scanner(System.in);
		Scanner numero = new Scanner(System.in);
		Scanner agencia = new Scanner(System.in);
		Scanner limite = new Scanner(System.in);
		Scanner saldo = new Scanner(System.in);
		
		System.out.print("Digite o ID para atualizar: ");
		int ident = id.nextInt();
		
		System.out.print("Títular: ");
		String tit = titular.nextLine();
		
		System.out.print("Número: ");
		int nro = numero.nextInt();
		
		System.out.print("Agência: ");
		int ag = agencia.nextInt();
		
		System.out.print("Limite: ");
		double lim = titular.nextDouble();
		
		System.out.print("Saldo: ");
		double sald = titular.nextDouble();
		
		  
		Conta c = new Conta(tit, nro, ag, lim, sald);
		ContaDAO dao = new ContaDAO();
		
		try {
			dao.atualizar(c, ident);
			System.out.println("Conta atualizada com sucesso");
		}catch(SQLException e) {
			System.out.println("Erro ao atualizar a conta");
			e.printStackTrace();
		}
	}
	
public static void recebeExcluir() {
		
		Scanner id = new Scanner(System.in);
		
		System.out.print("Digite o ID para excluir: ");
		int ident = id.nextInt();
		ContaDAO dao = new ContaDAO();
		
		try {
			dao.excluir(ident);
			System.out.println("Conta atualizada com sucesso");
		}catch(SQLException e) {
			System.out.println("Erro ao atualizar a conta");
			e.printStackTrace();
		}
	}
}
