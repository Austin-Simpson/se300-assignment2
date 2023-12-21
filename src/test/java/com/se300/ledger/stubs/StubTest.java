package com.se300.ledger.stubs;

import com.se300.ledger.Ledger;
import com.se300.ledger.LedgerException;
import com.se300.ledger.Transaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StubTest {

    @Test
    void testNegativeAmountValue() throws LedgerException {

        Ledger ledger = Ledger.getInstance("test", "test ledger 2023","chapman");

        Transaction sampleTransaction = mock(Transaction.class);
        when(sampleTransaction.getAmount()).thenReturn(-1);

        assertThrows(LedgerException.class, () -> ledger.processTransaction(sampleTransaction));
    }

    @Test
    void testNegativeTransactionFee() throws LedgerException {
        Ledger ledger = Ledger.getInstance("test", "test ledger 2023","chapman");
        Transaction sampleTransaction = mock(Transaction.class);
        when(sampleTransaction.getAmount()).thenReturn(100); // Assume a valid amount
        when(sampleTransaction.getFee()).thenReturn(-1); // Negative fee

        // Expect LedgerException due to negative fee
        assertThrows(LedgerException.class, () -> ledger.processTransaction(sampleTransaction),
                "A transaction with a negative fee should throw LedgerException");
    }

    @Test
    void testTransactionWithNullAccount() throws LedgerException {
        Ledger ledger = Ledger.getInstance("test", "test ledger 2023","chapman");
        Transaction sampleTransaction = mock(Transaction.class);
        when(sampleTransaction.getAmount()).thenReturn(100); // Assume a valid amount
        when(sampleTransaction.getFee()).thenReturn(10); // Assume a valid fee
        when(sampleTransaction.getPayer()).thenReturn(null); // Payer account is null

        // Expect LedgerException due to null payer account
        assertThrows(java.lang.NullPointerException.class, () -> ledger.processTransaction(sampleTransaction),
                "A transaction with a null payer account should throw LedgerException");
    }

}
