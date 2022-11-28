package br.com.projetoCeresGo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetoCeresGo.Models.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer>{

}
