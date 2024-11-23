package TP.tpgraphql.entites;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Compte {
	@Id
	@GeneratedValue()
	private Long id;
	private double solde;
	private Date dateCreation;
	@Enumerated(EnumType.STRING)
	private TypeCompte type;
	 @OneToMany(mappedBy = "compte", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Transaction> transactions = new ArrayList<>();
	public Compte(Long id, double solde, Date dateCreation, TypeCompte type) {
		super();
		this.id = id;
		this.solde = solde;
		this.dateCreation = dateCreation;
		this.type = type;
	}
	public Compte() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public TypeCompte getType() {
		return type;
	}
	public void setType(TypeCompte type) {
		this.type = type;
	}
	

}
