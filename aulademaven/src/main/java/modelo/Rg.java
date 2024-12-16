package modelo;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Rg {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int codigo;
	@Temporal(TemporalType.DATE)
	private Date dataExpedicao;
	private String orgaoExpedidor;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Date getDataExpedicao() {
		return dataExpedicao;
	}
	public void setDataExpedicao(Date dataExpedicao) {
		this.dataExpedicao = dataExpedicao;
	}
	
	public void setOrgaoExpedidor(String orgaoExpedidor) {
		this.orgaoExpedidor = orgaoExpedidor;
	};
	
	public String setOrgaoExpedidor() {
		return orgaoExpedidor;
	};
	
	public Rg() {};
	public Rg(int codigo,Date dataExpedicao,String orgaoExpedidor) {
		this.codigo = codigo;
		this.dataExpedicao = dataExpedicao;
		this.orgaoExpedidor = orgaoExpedidor;
	};
	
}
