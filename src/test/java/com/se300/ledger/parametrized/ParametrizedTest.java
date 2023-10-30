package com.se300.ledger.parametrized;

import com.se300.ledger.Account;
import com.se300.ledger.Ledger;
import com.se300.ledger.LedgerException;
import com.se300.ledger.Transaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class ParametrizedTest {

    private static Ledger ledger;

    @BeforeAll
    static void setUpClass() throws LedgerException {
        ledger = Ledger.getInstance("test", "test ledger 2023","chapman");

        Account master = ledger.getUncommittedBlock().getAccount("master");
        Account mary = ledger.createAccount("mary");

        Transaction firstTransaction =
                new Transaction("1",60,10,"simple test", master, mary);
        Transaction secondTransaction =
                new Transaction("2",60,10,"simple test", master, mary);
        Transaction thirdTransaction =
                new Transaction("3",60,10,"simple test", master, mary);
        Transaction forthTransaction =
                new Transaction("4",60,10,"simple test", master, mary);
        Transaction fifthTransaction =
                new Transaction("5",60,10,"simple test", master, mary);
        Transaction sixTransaction =
                new Transaction("6",60,10,"simple test", master, mary);
        Transaction seventhTransaction =
                new Transaction("7",60,10,"simple test", master, mary);
        Transaction eightsTransaction =
                new Transaction("8",60,10,"simple test", master, mary);
        Transaction ninthTransaction =
                new Transaction("9",60,10,"simple test", master, mary);
        Transaction tenthTransaction =
                new Transaction("10",60,10,"simple test", master, mary);

        ledger.processTransaction(firstTransaction);
        ledger.processTransaction(secondTransaction);
        ledger.processTransaction(thirdTransaction);
        ledger.processTransaction(forthTransaction);
        ledger.processTransaction(fifthTransaction);
        ledger.processTransaction(sixTransaction);
        ledger.processTransaction(seventhTransaction);
        ledger.processTransaction(eightsTransaction);
        ledger.processTransaction(ninthTransaction);
        ledger.processTransaction(tenthTransaction);
    }

    @ParameterizedTest
    @ValueSource(strings = {"aaron", "rebekah"})
    void testDuplicateAccountCreation(String name) throws LedgerException {

        ledger.createAccount(name);
        assertThrows(LedgerException.class, () -> ledger.createAccount(name));
    }

    @ParameterizedTest
    @CsvSource({"11,60,10,simple test, master, mary", "12,60,10,simple test, master, mary"})
    void testWordsInSentence(String transactionId, int value, int fee, String description, String payer, String payee) throws LedgerException {
        Account actualPayer = ledger.getUncommittedBlock().getAccount(payer);
        Account actualPayee = ledger.getUncommittedBlock().getAccount(payee);

        Transaction nextTransaction =
                new Transaction(transactionId, value, fee, description, actualPayer, actualPayee );

        ledger.processTransaction(nextTransaction);

        assertNotNull(ledger.getTransaction(transactionId));
    }
}
