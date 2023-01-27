package Dominio;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import persistencia.ClienteDAO;
import persistencia.FuncionarioDAO;

public class Serviço {
	private  int id ;
	private String tipo;
    private Funcionario funcionario;
	

	public Serviço(){
		
	}
	
	public Serviço(int id ,String tipo,Funcionario funcionario) {
		this.id = id;
		this.tipo = tipo;
	}
	
	public Serviço(int id ,String tipo) {
		this.id = id;
		this.tipo = tipo;
		}
	
	

	public void setId(int id) {
		this.id = id;
	}
	
	public  int getId() {
		return id;
	}
	
	

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public Funcionario getFuncionario() {
		return funcionario;
	}

	
public void setFuncionario(Funcionario funcionario) {
		
		FuncionarioDAO funcionarioDAO= new FuncionarioDAO();
		
		this.funcionario = funcionarioDAO.buscarFun(funcionario.getCarteiraTrab());
	}

}