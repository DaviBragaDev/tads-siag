package persistencia;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Dominio.Cliente;
import Dominio.Funcionario;
import Dominio.Serviço;

public class servicoDAO {
	private Conexao c;
	private final String INSERIR = "insert into \"servico\" "+"(\"id\",\"tipo\",\"fk_funcionario\") values (?,?,?)";
	private final String EXCLUIR = "delete from \"servico\"where \"id\"=?";	
	private final String GERAL = "select * from \"servico\"";
	private final String BUSCAR = "select * from \"servico\" where \"id\"=?";
	private final String ALTERAR = "update \"servico\" set \"id\"=?, \"tipo\"=?, where \"id\"=?";


	public servicoDAO() {
		c = new Conexao("jdbc:postgresql://localhost:5432/postgres","postgres","davi");
	}


	FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	Funcionario funcionarioAux = new Funcionario();
	
	public void inserirServico(Serviço servico,String carteiraTrab) {
		
		try {
			
			c.Conectar();
			
			
			
			
			funcionarioAux = funcionarioDAO.buscarFun(carteiraTrab);
			PreparedStatement instrucao = c.getConexao().prepareStatement(INSERIR);
			instrucao.setInt(1, servico.getId());
			instrucao.setString(2, servico.getTipo());
			instrucao.setString(3,funcionarioAux.getCarteiraTrab());
			instrucao.execute();
			
			c.Desconectar();
		}catch(SQLException e) {
			System.out.println("Erro na inclusao" + e);
		}
	}
	
	public void excluirServico(int id) {
		try {
			c.Conectar();
			PreparedStatement instrucao = c.getConexao().prepareStatement(EXCLUIR);
			instrucao.setInt(1, id);
			instrucao.execute();
			c.Desconectar();
			
		}catch(SQLException e){
			System.out.println("Erro na exclusao do servico"+e);
		}
		
	}
	
	public Serviço  buscarServico(int id) {
        Serviço servico = null;
        try {
            c.Conectar();
            PreparedStatement instrucao = c.getConexao().prepareStatement(BUSCAR);
            instrucao.setInt(1, id);
            ResultSet rs = instrucao.executeQuery();
            
            if(rs.next()) {
                servico = new Serviço (rs.getInt("id"), rs.getString("tipo"));
                funcionarioAux.setCarteiraTrab(rs.getString("fk_funcionario"));
            }
            c.Desconectar();
        }catch(SQLException e) {
            System.out.println("Erro na busca"+e);
        }
        return servico;

    }

	public void alterarCliente(Serviço servico, int idDeBusca) {
			
			try {
				c.Conectar();
				PreparedStatement instrucao = c.getConexao().prepareStatement(ALTERAR);
				instrucao.setInt(1, servico.getId());
				instrucao.setString(2, servico.getTipo());
				instrucao.setInt(2, idDeBusca);
				
				instrucao.execute();
				c.Desconectar();
				
				
			}catch(SQLException e) {
				System.out.println("Erro na alteracao" + e);
			}
			
		}
		
	public ArrayList<Serviço> servicoTotal(){
        ArrayList<Serviço> lista = new ArrayList<>();

        try {
            c.Conectar();
            Statement instrucao = c.getConexao().createStatement();
            ResultSet rs = instrucao.executeQuery(GERAL);
            while(rs.next()) {
                Serviço servico = new Serviço (
                		rs.getInt("id"),
                		rs.getString("tipo"),
                		funcionarioAux = funcionarioDAO.buscarFun(rs.getString("fk_funcionario")));
				
                servico.setFuncionario(funcionarioAux);
                
                lista.add(servico);
            }
            c.Desconectar();
        }catch(SQLException e){
            System.out.println("Erro na lista "+e);
        }
        return lista;
    }
		






}
