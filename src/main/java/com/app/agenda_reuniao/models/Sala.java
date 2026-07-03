package com.app.agenda_reuniao.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_salas")
public class Sala {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Integer capacidade;

    private String localizacao;

    private Boolean ativa;

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Integer getCapacidade() {
		return capacidade;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public Boolean getAtiva() {
		return ativa;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public void setAtiva(Boolean ativa) {
		this.ativa = ativa;
	}
    
    

}
