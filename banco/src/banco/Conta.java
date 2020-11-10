package banco;

public class Conta {
	private int id;
	private String titular;
	private int conta, agencia;
	private double limite, saldo;
	
	public Conta(String titular, int conta, int agencia, double limite, double saldo) {
		super();
		
		this.titular = titular;
		this.conta = conta;
		this.agencia = agencia;
		this.limite = limite;
		this.saldo = saldo;
	}
	
	

	public Conta() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getId() {
		return id;
	}

	public String getTitular() {
		return titular;
	}

	public int getConta() {
		return conta;
	}

	public int getAgencia() {
		return agencia;
	}

	public double getLimite() {
		return limite;
	}

	public double getSaldo() {
		return saldo;
	}

	
}
