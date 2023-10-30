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
}
