package TP.tpgraphql;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import TP.tpgraphql.dao.CompteRepository;
import TP.tpgraphql.entites.Compte;
import TP.tpgraphql.entites.TypeCompte;

@SpringBootApplication
public class TpgraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpgraphqlApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(CompteRepository compteRepository){
		return args -> {
			compteRepository.save(new Compte(null, Math.random()*9000, new Date(), TypeCompte.EPARGNE));
			compteRepository.save(new Compte(null, Math.random()*9000, new Date(), TypeCompte.COURANT));
			compteRepository.save(new Compte(null, Math.random()*9000, new Date(), TypeCompte.EPARGNE));
		};
	}

}
