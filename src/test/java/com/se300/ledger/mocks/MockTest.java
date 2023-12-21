package com.se300.ledger.mocks;

import com.se300.ledger.Account;
import com.se300.ledger.Ledger;
import com.se300.ledger.LedgerException;
import com.se300.ledger.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class MockTest {

    @Test
    void testPayerBalanceCheck() throws LedgerException {

        Ledger ledger = Ledger.getInstance("test", "test ledger 2023", "chapman");

        Account mary = mock(Account.class);
        Account sergey = mock(Account.class);

        Transaction sampleTransaction = new Transaction("1", 1000, 10, "simple test", mary, sergey);

        assertThrows(LedgerException.class, () -> ledger.processTransaction(sampleTransaction));

        verify(mary, times(1)).getBalance();

    }

    // my tests (at least 2):

    // 1. Test for account balance
    @Test
    void testBalance() throws LedgerException {
        Ledger ledger = Ledger.getInstance("test", "test ledger 2023", "chapman");
        Account mary = mock(Account.class);
        Account sergey = mock(Account.class);

        Transaction transaction1 = new Transaction("1", 1000, 10, "simple test", mary, sergey);
        Transaction transaction2 = new Transaction("2", 1000, 10, "simple test", mary, sergey);
        
        assertThrows(LedgerException.class, () -> ledger.processTransaction(transaction1));
        assertThrows(LedgerException.class, () -> ledger.processTransaction(transaction2));

        verify(mary, times(2)).getBalance();

    }

    // 2. Test for number of transactions
    @Test
    void testNumTransactions() throws LedgerException {
        Ledger ledger = Ledger.getInstance("test", "test ledger 2023", "chapman");
        Account mary = mock(Account.class);
        Account sergey = mock(Account.class);

        Transaction transaction1 = new Transaction("1", 1000, 10, "simple test", mary, sergey);
        Transaction transaction2 = new Transaction("2", 1000, 10, "simple test", mary, sergey);
        
        assertThrows(LedgerException.class, () -> ledger.processTransaction(transaction1));
        assertThrows(LedgerException.class, () -> ledger.processTransaction(transaction2));
        assertThrows(LedgerException.class, () -> ledger.processTransaction(transaction2));

        verify(mary, times(3)).getBalance();

    }
}
