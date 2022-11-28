package br.com.projetoCeresGo.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rele")
@Entity
public class Rele {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_rele;

	@Column(columnDefinition = "integer default 0")
	private int tipo;

	private String descricao;

	
	private int id_central;
	

	@Column(columnDefinition = "integer default 0")
	private int status; //0 acionado //1 desligado

	
	
	
	
}
