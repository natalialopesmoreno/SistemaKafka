package br.com.Teste;

import java.math.BigDecimal;

public class Transaction 
{

	private final String userId, transactionId;
	private final BigDecimal value;
	
	
	public Transaction(String userId, String transactionId, BigDecimal value) {
		
		this.userId = userId;
		this.transactionId = transactionId;
		this.value = value;
	}
	
	
}
