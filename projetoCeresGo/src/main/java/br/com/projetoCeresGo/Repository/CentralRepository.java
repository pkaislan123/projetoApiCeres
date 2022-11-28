package br.com.projetoCeresGo.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetoCeresGo.Models.Central;


@Repository
public interface CentralRepository extends JpaRepository<Central, Integer>{

}
