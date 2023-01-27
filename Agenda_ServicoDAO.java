package persistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Dominio.Agenda_Servico;
import Dominio.Agendamento;
import Dominio.Serviço;
import persistencia.Conexao;

public class Agenda_ServicoDAO {
	private	Conexao c;
	private final String INSERIR = "insert into Agenda_Servico  (fk_agendamento ,fk_servico) values (?,?)";
	private final String relatorioSERVICOS = "select fk_servico from Agenda_Servico  where fk_agendamento = (?)";
	
	
	public  Agenda_ServicoDAO() {
		c = new Conexao("jdbc:postgresql://localhost:5432/postgres","postgres","davi");
	}
	
	public void inserirAgendaServico( Agenda_Servico servico ) {

        try {
            c.Conectar();
            PreparedStatement instrucao = c.getConexao().prepareStatement(INSERIR);

            instrucao.setLong(1,servico.getFk_agendamento().getCodigo());
            instrucao.setInt(2,servico.getFk_servico().getId());
            instrucao.execute();
            c.Desconectar();
        }catch(Exception e) {
            System.out.println("erro de conexão"+ e);
        }

    }
	
	public ArrayList<Serviço> ServicosDeUmAgendamento(Long codigo){
		
		ArrayList<Serviço> ServicosDeUmAgendamento = new ArrayList<Serviço>();
		Serviço servicoAux = new Serviço();
		servicoDAO servicoDAO = new servicoDAO();

		try {
			
			c.Conectar();
			PreparedStatement instrucao = c.getConexao().prepareStatement(relatorioSERVICOS);
			instrucao.setLong(1,codigo);
			ResultSet rs = instrucao.executeQuery(); 
			
			while(rs.next()) {
			
				servicoAux  = servicoDAO.buscarServico(rs.getInt("fk_servico")) ;
				
				
				ServicosDeUmAgendamento.add(servicoAux);
			}
			c.Desconectar();
		}catch(Exception e) {System.out.println("ERRO NA BUSCA GERAL!!" + e);}
		
		
		
		return ServicosDeUmAgendamento;
		
	}
}
