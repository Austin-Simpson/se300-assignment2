package com.se300.ledger.assumptions;

import com.se300.ledger.Account;
import com.se300.ledger.Ledger;
import com.se300.ledger.LedgerException;
import com.se300.ledger.Transaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class AssumptionsTest {

    private static Ledger ledger;
    private static Account mary;
    private static Account sergey;

    @BeforeAll
    static void setUpClass() throws LedgerException {
        ledger = Ledger.getInstance("test", "test ledger 2023","chapman");

        Account master = ledger.getUncommittedBlock().getAccount("master");
        mary = ledger.createAccount("mary");
        sergey = ledger.createAccount("sergey");

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

    @BeforeEach
    void setUp() throws LedgerException {
        assumeTrue(ledger.getAccountBalance("master") > 0);
    }

    @Test
    void testTransaction(){

        assumingThat(ledger.getTransaction("11") == null,
                () -> {
                    Transaction firstTransaction =
                            new Transaction("11",60,10,"simple test", mary, sergey);
                    ledger.processTransaction(firstTransaction);
                });

    }

    // my tests (at least 3):

    // 1. Test for account creation
    @Test
    void testAccountCreation(){
        assumingThat(mary != null && sergey != null,
                () -> {
                    Account mary = ledger.getUncommittedBlock().getAccount("mary");
                    Account sergey = ledger.getUncommittedBlock().getAccount("sergey");
                    assumeTrue(mary != null);
                    assumeTrue(sergey != null);
                });
    }
    

    // 2. Test for invalid account balance
    @Test
    void testInvalidAccountBalance(){
        assumingThat(mary.getBalance() == sergey.getBalance(),
                () -> {
                    Account mary = ledger.getUncommittedBlock().getAccount("mary");
                    Account sergey = ledger.getUncommittedBlock().getAccount("sergey");
                    assumeTrue(mary != sergey);
                });
    }

    // 3. Test transactions processed correctly
    @Test
    void testTransactionProcessed(){
        assumingThat(ledger.getTransaction("11") != null,
                () -> {
                    Account master = ledger.getUncommittedBlock().getAccount("master");
                    Account mary = ledger.getUncommittedBlock().getAccount("mary");
                    Transaction nextTransaction = new Transaction("11", 10, 10, "simple test", master, mary);
                    ledger.processTransaction(nextTransaction);
                });
    }
    
}
