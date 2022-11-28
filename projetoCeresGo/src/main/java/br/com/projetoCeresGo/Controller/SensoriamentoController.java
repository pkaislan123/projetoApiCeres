package br.com.projetoCeresGo.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoCeresGo.Models.RegistroSensoriamento;
import br.com.projetoCeresGo.Models.Sensor;
import br.com.projetoCeresGo.Repository.RegistroSensoriamentoRepository;
import br.com.projetoCeresGo.Repository.SensorRepository;
import br.com.projetoCeresGo.Utils.RegistroRequest;
import br.com.projetoCeresGo.Utils.RegistroResponse;

@CrossOrigin
@RestController
@RequestMapping("v1/")
public class SensoriamentoController {

	@Autowired
	RegistroSensoriamentoRepository registroSensoriamentoRepository;

	@Autowired
	SensorRepository sensorRepository;

	@CrossOrigin
	@PostMapping({ "sensoriamento/inserir_registro" })
	public RegistroResponse inserirRegistroConsumo(@Valid @RequestBody RegistroRequest form) {

		RegistroResponse resposta = new RegistroResponse();
		Sensor sensor_afiliado = sensorRepository.findById(form.getId_sensor()).get();

		if (sensor_afiliado != null) {
			RegistroSensoriamento registro_sensor = new RegistroSensoriamento();
			registro_sensor.setSensor_afiliado(sensor_afiliado);
			registro_sensor.setData_hora(LocalDateTime.now());
			registro_sensor.setValor(form.getValor());

			if (registroSensoriamentoRepository.save(registro_sensor) != null) {
				resposta.setResposta("ok");

			} else {
				resposta.setResposta("erro");
			}
		} else {
			resposta.setResposta("ERRO 1");

		}

		return resposta;

	}

	@CrossOrigin
	@GetMapping("registro/listarTodos")
	public List<RegistroSensoriamento> listarTodos() {
		return registroSensoriamentoRepository.findAll();
	}

	@CrossOrigin
	@GetMapping("registro/listarPorSensorFiltrado/{id_sensores}/{dataInicio}/{dataFim}/{acumulado}")
	public ArrayList<List<RegistroSensoriamento>> listarPorSensor(@PathVariable String id_sensores,
			@PathVariable String dataInicio, @PathVariable String dataFim, @PathVariable int acumulado) {

		
		System.out.println("Data Inicio: " + dataInicio);
		System.out.println("Data Fim: " + dataFim);

		
		String ids[] = id_sensores.split(",");
		ArrayList<List<RegistroSensoriamento>> listaSensoriamento = new ArrayList<>();

		if (ids.length > 0) {

			for (String id : ids) {

				try {
					int idInt = Integer.parseInt(id);
					System.out.println("Id pesquisando: " + idInt);

					if (acumulado == 0) {
						List<RegistroSensoriamento> lista = registroSensoriamentoRepository.findBySensorFiltradoPorDia(idInt, dataInicio,
								dataFim);
						listaSensoriamento.add(lista);
						

					} else {
						List<RegistroSensoriamento> lista = registroSensoriamentoRepository.findBySensorFiltradoPorHora(idInt, dataInicio,
								dataFim);

						listaSensoriamento.add(lista);

					}
				} catch (Exception e) {

				}
			}
		} else {
			return null;
		}

		return listaSensoriamento;

	}
	
	
	@CrossOrigin
	@GetMapping("registro/listarPorSensor/{id_sensor}")
	public List<RegistroSensoriamento> listarPorSensor(@PathVariable int id_sensor) {

		return registroSensoriamentoRepository.findBySensor_Id(id_sensor);

	}

}
