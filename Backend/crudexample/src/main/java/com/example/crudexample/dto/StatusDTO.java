package com.example.crudexample.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatusDTO {
	
	@JsonProperty("TransactionResult")
	private String transactionResult;

	public String getTransactionResult() {
		return transactionResult;
	}

	public void setTransactionResult(String transactionResult) {
		this.transactionResult = transactionResult;
	}

}
