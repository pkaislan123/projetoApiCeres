package br.com.projetoCeresGo.Models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "central")
@Entity
public class Central {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_central;

	@Column(columnDefinition = "integer default 0")
	private int tipo;

	private String nome;

	private String descricao;

	
	
	@OneToMany(mappedBy = "central_afiliada")
	private List<Sensor> sensores;
	


}
