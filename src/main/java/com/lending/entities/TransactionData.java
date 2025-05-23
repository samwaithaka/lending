package com.lending.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class TransactionData implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String accountNumber;
	private double alternativechanneltrnscrAmount;
	private int alternativechanneltrnscrNumber;
	private double alternativechanneltrnsdebitAmount;
	private int alternativechanneltrnsdebitNumber;
	private int atmTransactionsNumber;
	private double atmtransactionsAmount;
	private int bouncedChequesDebitNumber;
	private int bouncedchequescreditNumber;
	private double bouncedchequetransactionscrAmount;
	private double bouncedchequetransactionsdrAmount;
	private double chequeDebitTransactionsAmount;
	private int chequeDebitTransactionsNumber;
	private LocalDateTime createdAt;
	private LocalDateTime createdDate;
	private double credittransactionsAmount;
	private double debitcardpostransactionsAmount;
	private int debitcardpostransactionsNumber;
	private double fincominglocaltransactioncrAmount;
	private double incominginternationaltrncrAmount;
	private int incominginternationaltrncrNumber;
	private int incominglocaltransactioncrNumber;
	private int intrestAmount;
	private LocalDateTime lastTransactionDate;
	private String lastTransactionType;
	private int lastTransactionValue;
	private double maxAtmTransactions;
	private double maxMonthlyBebitTransactions;
	private double maxalternativechanneltrnscr;
	private double maxalternativechanneltrnsdebit;
	private double maxbouncedchequetransactionscr;
	private double maxchequedebittransactions;
	private double maxdebitcardpostransactions;
	private double maxincominginternationaltrncr;
	private double maxincominglocaltransactioncr;
	private double maxmobilemoneycredittrn;
	private double maxmobilemoneydebittransaction;
	private double maxmonthlycredittransactions;
	private double maxoutgoinginttrndebit;
	private double maxoutgoinglocaltrndebit;
	private double maxoverthecounterwithdrawals;
	private double minAtmTransactions;
	private double minMonthlyDebitTransactions;
	private double minalternativechanneltrnscr;
	private double minalternativechanneltrnsdebit;
	private double minbouncedchequetransactionscr;
	private double minchequedebittransactions;
	private double mindebitcardpostransactions;
	private double minincominginternationaltrncr;
	private double minincominglocaltransactioncr;
	private double minmobilemoneycredittrn;
	private double minmobilemoneydebittransaction;
	private double minmonthlycredittransactions;
	private double minoutgoinginttrndebit;
	private double minoutgoinglocaltrndebit;
	private double minoverthecounterwithdrawals;
	private double mobilemoneycredittransactionAmount;
	private int mobilemoneycredittransactionNumber;
	private double mobilemoneydebittransactionAmount;
	private int mobilemoneydebittransactionNumber;
	private double monthlyBalance;
	private double monthlydebittransactionsAmount;
	private double outgoinginttransactiondebitAmount;
	private int outgoinginttrndebitNumber;
	private double outgoinglocaltransactiondebitAmount;
	private int outgoinglocaltransactiondebitNumber;
	private double overdraftLimit;
	private double overthecounterwithdrawalsAmount;
	private int overthecounterwithdrawalsNumber;
	private double transactionValue;
	private LocalDateTime updatedAt;
}
