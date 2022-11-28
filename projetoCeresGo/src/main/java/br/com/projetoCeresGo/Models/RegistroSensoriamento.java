package br.com.projetoCeresGo.Models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "registro_sensoriamento")
@Entity
public class RegistroSensoriamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_registro;
	
	
	@ManyToOne
	@JoinColumn(name = "sensor_id", referencedColumnName = "id_sensor" , nullable = false)
	private Sensor sensor_afiliado;
	
	double valor;
	
	private LocalDateTime data_hora;
	
	@JsonInclude()
	@Transient
	private double media;
	
	
	
}
