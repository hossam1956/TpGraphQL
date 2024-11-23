package TP.tpgraphql.dto;



import java.time.LocalDateTime;

import TP.tpgraphql.entites.TypeTransaction;


public class TransactionRequest {
    private Long compteId;     
    private Float montant;   
    private TypeTransaction type;        
    private LocalDateTime date;
	public TransactionRequest(Long compteId, Float montant, TypeTransaction type, LocalDateTime date) {
		super();
		this.compteId = compteId;
		this.montant = montant;
		this.type = type;
		this.date = date;
	}
	public TransactionRequest() {
		super();
	}
	public Long getCompteId() {
		return compteId;
	}
	public void setCompteId(Long compteId) {
		this.compteId = compteId;
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



}
