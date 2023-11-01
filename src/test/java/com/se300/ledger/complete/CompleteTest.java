package com.se300.ledger.complete;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

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
        assertEquals("Account does not exist", updatedReason, "Expected reason to be updated to 'Account does not exist'");
    }

    // Tests for Ledger.java:
    @Test
    public void testGetName() {
        // Arrange
        Ledger ledger = new Ledger("TestLedger");

        // Act
        String name = ledger.getName();

        // Assert
        assertEquals("TestLedger", name, "Expected name to be 'TestLedger'");
    }

}
