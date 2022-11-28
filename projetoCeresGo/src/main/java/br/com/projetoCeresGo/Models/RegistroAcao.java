package br.com.projetoCeresGo.Models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "registro_acao")
@Entity
public class RegistroAcao {

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_registro;
	
	@ManyToOne
	@JoinColumn(name = "rele_id", referencedColumnName = "id_rele" , nullable = false)
	private Rele rele_afiliado;
	
	
	private int id_central;
	
	private LocalDateTime data_hora_requisicao;
	
	
	@Column(columnDefinition = "integer default -1")
	private int requisicao;//-1 sem requisicao //0 desligar //1 ligar //2 obter status reles

	
	@Column(nullable = true)
	private LocalDateTime data_hora_resposta;
	
	@Column(columnDefinition = "integer default 0")
	private int respondido; //0 nao respondida // 1 respondida
	
	@Column(nullable = true)
	private String resposta;
	
}
