package br.com.projetoCeresGo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoCeresGo.Models.Central;
import br.com.projetoCeresGo.Models.Sensor;
import br.com.projetoCeresGo.Repository.CentralRepository;
import br.com.projetoCeresGo.Repository.SensorRepository;

@CrossOrigin
@RestController
@RequestMapping("v1/")
public class CentralController {

	
	@Autowired
	CentralRepository centralRepository;
	
	@CrossOrigin
	@GetMapping("centrais/listarTodos")
	public List<Central> listarTodos()
	{
		return centralRepository.findAll();
	}
	
}
