package br.com.projetoCeresGo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.projetoCeresGo.Models.Rele;

@Repository
public interface ReleRepository extends JpaRepository<Rele, Integer>{

	@Query(value = "select * from rele where id_central = :id", nativeQuery = true)
	List<Rele> findByCentral(int id);
	
	
}
