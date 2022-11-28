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
@Table(name = "sensor")
@Entity
public class Sensor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_sensor;

	@Column(columnDefinition = "integer default 0")
	private int tipo;

	@Column(columnDefinition = "integer default 0")
	private int unidade_medida;

	private String descricao;

	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "central_id", referencedColumnName = "id_central" , nullable = false)
	private Central central_afiliada;
	
	   
}
