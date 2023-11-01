package com.se300.ledger.complete;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import com.se300.ledger.Account;
import com.se300.ledger.MerkleTrees;
import com.se300.ledger.Transaction;

import static org.mockito.Mockito.*;

import com.se300.ledger.Block;

import com.se300.ledger.LedgerException;

import com.se300.ledger.Ledger;

public class CompleteTest {

    /*
     * Must do the following
     * 1. Achieve 100% Test Coverage
     * 2. Produce/Print Identical Results to Command Line DriverTest
     * 3. Produce Quality Report
     */

    // Tests for Transaction.java:
    @Test
    public void testSetTransactionId() {
        Transaction transaction = new Transaction(null, null, null, null, null, null);
        transaction.setTransactionId("12345");
        assertEquals("12345", transaction.getTransactionId());
    }

    @Test
    public void testSetAmount() {
        Transaction transaction = new Transaction(null, null, null, null, null, null);
        transaction.setAmount(1000);
        assertEquals(1000, transaction.getAmount());
    }

    @Test
    public void testSetFee() {
        Transaction transaction = new Transaction(null, null, null, null, null, null);
        transaction.setFee(50);
        assertEquals(50, transaction.getFee());
    }

    @Test
    public void testSetNote() {
        Transaction transaction = new Transaction(null, null, null, null, null, null);
        transaction.setNote("Test Note");
        assertEquals("Test Note", transaction.getNote());
    }

    @Test
    public void testSetPayer() {
        Transaction transaction = new Transaction(null, null, null, null, null, null);
        Account payer = new Account("PayerAddress123", 1000); // Using the provided Account constructor
        transaction.setPayer(payer);
        assertEquals(payer, transaction.getPayer());
    }

    @Test
    public void testSetReceiver() {
        Transaction transaction = new Transaction(null, null, null, null, null, null);
        Account receiver = new Account("ReceiverAddress456", 1500); // Using the provided Account constructor
        transaction.setReceiver(receiver);
        assertEquals(receiver, transaction.getReceiver());
    }

    // Test for Account.java:
    @Test
    public void testAccountSetAddress() {
        // Create an account with initial address
        Account account = new Account("InitialAddress789", 500);

        // Set a new address for the account
        account.setAddress("NewAddress101");

        // Assert that the address is updated
        assertEquals("NewAddress101", account.getAddress());
    }

    // Test for MerkleTree.java:
    @Test
    public void testGetSHA2HexValueException() throws NoSuchAlgorithmException {
        // Use try-with-resources with mockStatic for MessageDigest
        try (MockedStatic<MessageDigest> mdMock = mockStatic(MessageDigest.class)) {
            // Mock the getInstance method to throw an exception
            mdMock.when(() -> MessageDigest.getInstance("SHA-256")).thenThrow(new NoSuchAlgorithmException());

            MerkleTrees merkleTrees = new MerkleTrees(new ArrayList<>());

            // As the exception is mocked, calling getSHA2HexValue should now enter the
            // catch block
            String result = merkleTrees.getSHA2HexValue("test");

            // Assert the result
            assertEquals("", result); // as the catch block returns an empty string
        }

    }

    // Tests for Block.java:
    @Test
    public void testSetBlockNumber() {
        // Arrange
        Block block = new Block(1, "previousHashSample");
        Integer newBlockNumber = 2;

        // Act
        block.setBlockNumber(newBlockNumber);

        // Assert
        assertEquals(newBlockNumber, block.getBlockNumber());
    }

    @Test
    public void testSetPreviousHash() {
        // Arrange
        Block block = new Block(1, "previousHashSample");
        String newPreviousHash = "newPreviousHashSample";

        // Act
        block.setPreviousHash(newPreviousHash);

        // Assert
        assertEquals(newPreviousHash, block.getPreviousHash());
    }

    // Tests for LedgerException.java:
    @Test
    public void testGetAction() {
        // Arrange
        LedgerException exception = new LedgerException("AddAccount", "Account already exists");

        // Act
        String action = exception.getAction();

        // Assert
        assertEquals("AddAccount", action, "Expected action to be 'AddAccount'");
    }

    @Test
    public void testSetAction() {
        // Arrange
        LedgerException exception = new LedgerException("AddAccount", "Account already exists");

        // Act
        exception.setAction("RemoveAccount");
        String updatedAction = exception.getAction();

        // Assert
        assertEquals("RemoveAccount", updatedAction, "Expected action to be updated to 'RemoveAccount'");
    }

    @Test
    public void testSetReason() {
        // Arrange
        LedgerException exception = new LedgerException("AddAccount", "Account already exists");

        // Act
        exception.setReason("Account does not exist");
        String updatedReason = exception.getReason();

        // Assert
        assertEquals("Account does not exist", updatedReason,
                "Expected reason to be updated to 'Account does not exist'");
    }

    // Tests for Ledger.java:
    private Ledger ledger;

    // Setup method to initialize the Ledger instance before each test
    @BeforeEach
    public void setUp() {
        // Using the getInstance() method to get a Ledger instance
        ledger = Ledger.getInstance("TestLedger", "This is a test ledger.", "TestSeed");
    }

    @Test
    public void testGetName() {
        // Testing the getName() method of Ledger class
        assertEquals("TestLedger", ledger.getName(), "Expected name to be 'TestLedger'");
    }

    @Test
    public void testSetName() {
        // Setting the name to a new value
        ledger.setName("NewLedgerName");

        // Asserting that the name has been updated
        assertEquals("NewLedgerName", ledger.getName(), "Expected name to be 'NewLedgerName'");
    }

    // @Test
    // public void testGetDescription() {
    // ledger = Ledger.getInstance("TestLedger", "This is a test ledger.",
    // "TestSeed");
    // assertEquals("This is a test ledger.", ledger.getDescription(), "Expected
    // description to be 'This is a test ledger.'");
    // }

    @Test
    public void testGetDescription() {
        String description = ledger.getDescription();
        assertEquals("This is a test ledger.", description);
    }

    @Test
    public void testSetDescription() {
        // Setting the description to a new value
        ledger.setDescription("New Description");

        // Asserting that the description has been updated
        assertEquals("New Description", ledger.getDescription(), "Expected description to be 'New Description'");

        ledger.setDescription("This is a test ledger.");

    }

    @Test
    public void testGetSeed() {
        // Testing the getSeed() method of Ledger class
        assertEquals("TestSeed", ledger.getSeed(), "Expected seed to be 'TestSeed'");
    }

    @Test
    public void testSetSeed() {
        // Setting the seed to a new value
        ledger.setSeed("NewSeed");

        // Asserting that the seed has been updated
        assertEquals("NewSeed", ledger.getSeed(), "Expected seed to be 'NewSeed'");
    }

    @Test
    public void testNoteLengthExceedsLimit() {
        Account payer = new Account("payerAddress", 1000);
        Account receiver = new Account("receiverAddress", 1000);

        String longNote = new String(new char[1025]).replace("\0", "A");
        Transaction transaction = new Transaction("txId3", 100, 10, longNote, payer, receiver);
        assertThrows(LedgerException.class, () -> {
            ledger.processTransaction(transaction);
        }, "Note Length Must Be Less Than 1024 Chars");
    }

    // Note: This test assumes a method in your ledger to add a transaction without
    // validation, you might need to adjust it.
    @Test
    public void testDuplicateTransactionId() throws LedgerException {
        Account payer = new Account("payerAddress", 1000);
        Account receiver = new Account("receiverAddress", 1000);

        Transaction transaction1 = new Transaction("txId4", 100, 10, "Note1", payer, receiver);
        ledger.processTransaction(transaction1); // Assumes the ledger adds it without validation.

        Transaction transaction2 = new Transaction("txId4", 200, 15, "Note2", payer, receiver);
        assertThrows(LedgerException.class, () -> {
            ledger.processTransaction(transaction2);
        }, "Transaction Id Must Be Unique");
    }

    @Test
    public void testNegativeTransactionAmount() {
        Account payer = new Account("payerAddress", 1000);
        Account receiver = new Account("receiverAddress", 1000);

        Transaction transaction = new Transaction("txId5", -100, 10, "Note", payer, receiver);
        assertThrows(LedgerException.class, () -> {
            ledger.processTransaction(transaction);
        }, "Transaction Amount Is Out of Range");
    }

    // @Test
    // void testGetAccountBalance_AccountDoesNotExist() {
    // String nonExistentAddress = "someRandomAddress123";

    // LedgerException exception = assertThrows(LedgerException.class, () -> {
    // ledger.getAccountBalance(nonExistentAddress);
    // });

    // String expectedMessage = "Account Does Not Exist";
    // String actualMessage = exception.getMessage(); // Using getMessage()

    // System.out.println("Actual exception message: " + actualMessage); // Print
    // out the actual exception message for debugging

    // assertTrue(actualMessage != null && actualMessage.contains(expectedMessage));
    // }

    // @Test
    // void testGetBlock_BlockDoesNotExist() {
    // Integer nonExistentBlockNumber = 9999; // Assuming this block number doesn't
    // exist in your blockMap

    // // Expect the LedgerException to be thrown when attempting to get a
    // non-existent
    // // block
    // LedgerException exception = assertThrows(LedgerException.class, () -> {
    // ledger.getBlock(nonExistentBlockNumber);
    // });

    // // Check if the thrown exception message matches the expected message
    // String expectedMessage = "Block Does Not Exist";
    // String actualMessage = exception.getMessage(); // Assuming your
    // LedgerException uses getMessage() for this

    // assertTrue(actualMessage != null && actualMessage.contains(expectedMessage));
    // }

    // @Test
    // void testGetNumberOfBlocks() {
    //     // Given
    //     int expectedNumberOfBlocks = 3;  // Example number of blocks to add
    
    //     // Add blocks to the ledger using a possible method
    //     for (int i = 0; i < expectedNumberOfBlocks; i++) {
    //         Block newBlock = new Block(i, "somePreviousHash");
    //         ledger.addBlock(newBlock);  // Assuming there is a method addBlock in the Ledger class
    //     }
    
    //     // When
    //     int actualNumberOfBlocks = ledger.getNumberOfBlocks();
    
    //     // Then
    //     assertEquals(expectedNumberOfBlocks, actualNumberOfBlocks, "Number of blocks returned is not as expected.");
    // }
    

}
