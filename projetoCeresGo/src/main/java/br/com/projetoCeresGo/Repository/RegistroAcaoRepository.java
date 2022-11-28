package br.com.projetoCeresGo.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projetoCeresGo.Models.RegistroAcao;
import br.com.projetoCeresGo.Models.RegistroSensoriamento;
import br.com.projetoCeresGo.Models.Rele;

@Repository
public interface RegistroAcaoRepository extends JpaRepository<RegistroAcao, Integer>{

	
	
	@Query(value = "select rc.* from registro_acao rc "
			+ " left join rele r on r.id_rele = rc.rele_id "
			+ " where respondido = 0 and r.id_central = :id_central and "
			+ " TIME_TO_SEC( timediff(now() ,data_hora_requisicao)) < 3", nativeQuery = true)
	List<RegistroAcao> listarAcoesNaoRespondidasPorCentral(int id_central);
	
	
}

