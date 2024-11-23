package TP.tpgraphql.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import TP.tpgraphql.entites.Compte;
import TP.tpgraphql.entites.TypeCompte;


@Repository
public interface CompteRepository extends JpaRepository<Compte,Long> {
	 	
		List<Compte> findByType(TypeCompte type);

	    @Query("SELECT SUM(c.solde) FROM Compte c")
	    double sumSoldes();
}
