package TP.tpgraphql.controllers;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import TP.tpgraphql.dao.CompteRepository;
import TP.tpgraphql.dao.TransactionRepository;
import TP.tpgraphql.dto.TransactionRequest;
import TP.tpgraphql.entites.Compte;
import TP.tpgraphql.entites.Transaction;
import TP.tpgraphql.entites.TypeTransaction;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "*")
public class TransactionControllerGraphQL {

	private final TransactionRepository transactionRepository;
	private final CompteRepository compteRepository;

	public TransactionControllerGraphQL(TransactionRepository transactionRepository,
			CompteRepository compteRepository) {
		super();
		this.transactionRepository = transactionRepository;
		this.compteRepository = compteRepository;
	}

	@QueryMapping
	public List<Transaction> allTransactions() {
		return transactionRepository.findAll();
	}

	@QueryMapping
	public List<Transaction> transactionsByCompte(@Argument Long id) {
		Compte compte = compteRepository.findById(id).orElseThrow(() -> new RuntimeException("Compte  not found: "));
		return transactionRepository.findByCompte(compte);

	}

	@QueryMapping
	public Map<String, Object> transactionStats() {
		long count = transactionRepository.count(); // Total number of transactions
		double sumDepots = transactionRepository.sumByType(TypeTransaction.DEPOT); // Sum of deposit transactions
		double sumRetraits = transactionRepository.sumByType(TypeTransaction.RETRAIT); // Sum of withdrawal transactions

		return Map.of("count", count, "sumDepots", sumDepots, "sumRetraits", sumRetraits);
	}

	@MutationMapping
	public Transaction addTransaction(@Argument TransactionRequest transactionRequest) {
		// Find the associated account
		Compte compte = compteRepository.findById(transactionRequest.getCompteId())
				.orElseThrow(() -> new RuntimeException("Compte not found"));

		Transaction transaction = new Transaction();
		transaction.setMontant(transactionRequest.getMontant());
		transaction.setType(transactionRequest.getType());
		transaction.setDate(transactionRequest.getDate());
		transaction.setCompte(compte);

		if (transactionRequest.getType() == TypeTransaction.DEPOT) {
			compte.setSolde(compte.getSolde() + transactionRequest.getMontant());
		} else if (transactionRequest.getType() == TypeTransaction.RETRAIT) {
			if (compte.getSolde() < transactionRequest.getMontant()) {
				throw new RuntimeException("Insufficient funds for withdrawal");
			}
			compte.setSolde(compte.getSolde() - transactionRequest.getMontant());
		}

		compteRepository.save(compte);

		return transactionRepository.save(transaction);
	}

}
