package TP.tpgraphql.dto;

import TP.tpgraphql.entites.TypeCompte;

public class CompteRequest {
    private Double solde;
    private String dateCreation; 
    
    public CompteRequest() {
		super();
	}
	public CompteRequest(Double solde, String dateCreation, TypeCompte type) {
		super();
		this.solde = solde;
		this.dateCreation = dateCreation;
		this.type = type;
	}
	public Double getSolde() {
		return solde;
	}
	public void setSolde(Double solde) {
		this.solde = solde;
	}
	public String getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	public TypeCompte getType() {
		return type;
	}
	public void setType(TypeCompte type) {
		this.type = type;
	}
	private TypeCompte type;
}
