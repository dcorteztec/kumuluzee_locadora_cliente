package br.com.marteleto.locadora.vo;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.marteleto.framework.persistence.vo.abstracts.APersistenceEntity;

@Entity
@Table(
		name="TB_CLIENTE"
		,uniqueConstraints={
				@UniqueConstraint(name="UN_CLIE_CHAVE_CANDIDATA",columnNames={"NM_CLIE_EMAIL"})
		}
		,indexes={
				@Index(name="PK_CLIE",columnList="SQ_CLIE_CLIENTE"),
				@Index(name="IX_CLIE_CHAVE_CANDIDATA",columnList="NM_CLIE_EMAIL")
		}
)
@SequenceGenerator(name="SEQUENCE", sequenceName="SQ_CLIE_CLIENTE", allocationSize = 1)
@AttributeOverride(name="id", column=@Column(name="SQ_CLIE_CLIENTE",nullable=false,length=5))
public class ClienteVO extends APersistenceEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name="NM_CLIE_NOME",nullable=false,length=100)
	private String nome;
	
	@Column(name="NM_CLIE_EMAIL",nullable=false,length=50)
	private String email;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
