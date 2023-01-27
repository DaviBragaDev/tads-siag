package Dominio;

public class Agenda_Servico {
	private int id;
	private Agendamento fk_agendamento;
	private Serviço fk_servico;
	
	
	public Agenda_Servico() {
		
	}
	
	public Agenda_Servico(int id, Agendamento fk_agendamento, Serviço fk_servico) {
		this.id = id;
		this.fk_agendamento = fk_agendamento;
		this.fk_servico = fk_servico;

	}


	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Agendamento getFk_agendamento() {
		return fk_agendamento;
	}
	public void setFk_agendamento(Agendamento fk_agendamento) {
		this.fk_agendamento = fk_agendamento;
	}
	public Serviço getFk_servico() {
		return fk_servico;
	}
	public void setFk_servico(Serviço fk_servico) {
		this.fk_servico = fk_servico;
	}

}
