package TP.tpgraphql.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import TP.tpgraphql.dao.CompteRepository;
import TP.tpgraphql.entites.Compte;
import TP.tpgraphql.entites.TypeCompte;

import java.text.ParseException;

@Controller
public class CompteControllerGraphQL {

	private CompteRepository compteRepository;

	public CompteControllerGraphQL(CompteRepository compteRepository) {
		super();
		this.compteRepository = compteRepository;
	}
	
	@QueryMapping
	public List<Compte> allComptes() {
	    List<Compte> comptes = compteRepository.findAll();
	    if (comptes.isEmpty()) {
	        System.out.println("No comptes found in the database.");
	    }
	    return comptes;
	}
	@QueryMapping
	public Compte compteById(@Argument Long id) {
		Compte compte = compteRepository.findById(id).orElse(null);
		if (compte == null)
			throw new RuntimeException(String.format("Compte %s not found", id));
		else
			return compte;
	}

	@MutationMapping
	public Compte saveCompte(@Argument("compte") TP.tpgraphql.dto.CompteRequest compteRequest) {
		if (compteRequest == null) {
			throw new RuntimeException("CompteRequest cannot be null");
		}

		Compte compte = new Compte();
		compte.setSolde(compteRequest.getSolde());

		if (compteRequest.getDateCreation() != null) {
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date date = formatter.parse(compteRequest.getDateCreation());
				compte.setDateCreation(date);
			} catch (ParseException e) {
				throw new RuntimeException("Invalid date format. Expected yyyy-MM-dd.");
			}
		} else {
			compte.setDateCreation(new Date());
		}

		compte.setType(compteRequest.getType());
		return compteRepository.save(compte);
	}

	@MutationMapping
	public String deleteCompte(@Argument Long id) {
		Compte compte = compteRepository.findById(id).orElse(null);
		if (compte == null)
			throw new RuntimeException(String.format("Compte %s not found", id));
		else {
			compteRepository.delete(compteById(compte.getId()));
			return "Compte with ID " + compte.getId() + " has been successfully deleted.";
		}
	}

	@QueryMapping
	public Map<String, Object> totalSolde() {
		long count = compteRepository.count(); // Nombre total de comptes
		double sum = compteRepository.sumSoldes(); // Somme totale des soldes
		double average = count > 0 ? sum / count : 0; // Moyenne des soldes

		return Map.of("count", count, "sum", sum, "average", average);
	}

	@QueryMapping
	public List<Compte> findByType(@Argument TypeCompte type) {
		return compteRepository.findByType(type);
	}
}
