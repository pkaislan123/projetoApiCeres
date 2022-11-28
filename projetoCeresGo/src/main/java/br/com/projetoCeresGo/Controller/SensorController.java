package br.com.projetoCeresGo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.projetoCeresGo.Models.Sensor;
import br.com.projetoCeresGo.Repository.SensorRepository;


@CrossOrigin
@RestController
@RequestMapping("v1/")
public class SensorController {

	@Autowired
	SensorRepository sensorRepository;
	
	@CrossOrigin
	@GetMapping("sensores/listarTodos")
	public List<Sensor> listarTodos()
	{
		return sensorRepository.findAll();
	}
	
	
	
}
