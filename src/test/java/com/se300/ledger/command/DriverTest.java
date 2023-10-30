package com.se300.ledger.command;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Objects;

/**
 * Test Driver Class for testing Blockchain
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2023-10-11
 */
public class DriverTest {

    @Test
    public void testDriver() throws URISyntaxException {

        Path path = new File(Objects.requireNonNull(getClass().getResource("/ledger.script")).getFile()).toPath();

        CommandProcessor processor = new CommandProcessor();
        processor.processCommandFile (path.toString());
    }
}