package TP.tpgraphql.entites;

import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float montant;

    @Enumerated(EnumType.STRING)
    private TypeTransaction type;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "compte_id", nullable = false)
    private Compte compte;
    

	public Transaction(Long id, Float montant, TypeTransaction type, LocalDateTime date, Compte compte) {
		super();
		this.id = id;
		this.montant = montant;
		this.type = type;
		this.date = date;
		this.compte = compte;
	}
	

	public Transaction() {
		super();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getMontant() {
		return montant;
	}

	public void setMontant(Float montant) {
		this.montant = montant;
	}

	public TypeTransaction getType() {
		return type;
	}

	public void setType(TypeTransaction type) {
		this.type = type;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	} 
    
    
}
