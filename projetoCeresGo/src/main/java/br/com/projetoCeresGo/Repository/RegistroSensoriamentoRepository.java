package br.com.projetoCeresGo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projetoCeresGo.Models.RegistroSensoriamento;;

@Repository
public interface RegistroSensoriamentoRepository extends JpaRepository<RegistroSensoriamento, Integer>{

	@Query(value = "select *,sum(reg.valor)/count(reg.id_registro) as media from registro_sensoriamento reg where reg.sensor_id = :id_sensor "
			+ " GROUP BY date(reg.data_hora), hour(reg.data_hora)  order by reg.data_hora ", nativeQuery = true)
	List<RegistroSensoriamento> findBySensor_Id(int id_sensor);
	
	

	@Query(value = "select *,sum(reg.valor)/count(reg.id_registro) as media "
			+ " from registro_sensoriamento reg where reg.sensor_id = :id_sensor "
			+ " and ( date(reg.data_hora) BETWEEN  str_to_date(:dataInicio , '%Y-%m-%d')  and str_to_date(:dataFim, '%Y-%m-%d') ) "
			+ " GROUP BY date(reg.data_hora) "
			+ " order by reg.data_hora", nativeQuery = true)
	List<RegistroSensoriamento> findBySensorFiltradoPorDia(int id_sensor, String dataInicio, String dataFim);
	

	@Query(value = "select *,sum(reg.valor)/count(reg.id_registro) as media "
			+ " from registro_sensoriamento reg where reg.sensor_id = :id_sensor "
			+ " and ( date(reg.data_hora) BETWEEN  str_to_date(:dataInicio , '%Y-%m-%d')  and str_to_date(:dataFim, '%Y-%m-%d') ) "
			+ " GROUP BY date(reg.data_hora), hour(reg.data_hora)  "
			+ " order by reg.data_hora", nativeQuery = true)
	List<RegistroSensoriamento> findBySensorFiltradoPorHora(int id_sensor, String dataInicio, String dataFim);
	
	
	
	
	
}
