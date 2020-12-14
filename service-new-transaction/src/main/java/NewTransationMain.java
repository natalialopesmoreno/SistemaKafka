package br.com.Teste;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewTransationMain {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		// Esta seria uma classe Producer que está enviando as mensagens
		try (var transactionDispatcher = new KafkaDispatcher<Transaction>()) {
			try (var emailDispatcher = new KafkaDispatcher<Email>()) {
				for (var i = 0; i < 10; i++) {
					// com a chave randomizada será possível dividir entre as partições para que
					// ocorra o paralelismo
					var userId = UUID.randomUUID().toString();
					var transactionId = UUID.randomUUID().toString();
					var value = new BigDecimal(Math.random() * 5000 + 1);
					var transaction = new Transaction(userId, transactionId, value);
					transactionDispatcher.send("BANK_TRANSACTION", userId, transaction);

					// Aqui está produzindo um novo tópico para o envio de email
					var email = "Thank you! We are processing your transaction!";
					emailDispatcher.send("BANK_SEND_EMAIL", userId, email);
				}
			}
		}
	}

}
