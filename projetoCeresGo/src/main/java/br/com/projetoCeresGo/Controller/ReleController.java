package br.com.projetoCeresGo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoCeresGo.Models.Rele;
import br.com.projetoCeresGo.Models.Sensor;
import br.com.projetoCeresGo.Repository.ReleRepository;
import br.com.projetoCeresGo.Repository.SensorRepository;

@CrossOrigin
@RestController
@RequestMapping("v1/")
public class ReleController {

	@Autowired
	ReleRepository releRepository;
	
	@CrossOrigin
	@GetMapping("reles/listarTodos")
	public List<Rele> listarTodos()
	{
		return releRepository.findAll();
	}
	

	@CrossOrigin
	@GetMapping("reles/listarRelesPorCentral/{id_central}")
	public List<Rele> listarTodos(@PathVariable int id_central)
	{
		return releRepository.findByCentral(id_central);
	}
	


	
	
}
