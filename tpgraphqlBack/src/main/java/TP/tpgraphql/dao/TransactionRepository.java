package TP.tpgraphql.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import TP.tpgraphql.entites.Compte;
import TP.tpgraphql.entites.Transaction;
import TP.tpgraphql.entites.TypeTransaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByCompte(Compte compte);

    List<Transaction> findByType(String type);


    @Query("SELECT SUM(t.montant) FROM Transaction t WHERE t.type = :type")
    double sumByType(TypeTransaction type);



}
