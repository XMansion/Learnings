package org.deeaae.learnings.core.message;


import org.junit.Test;
import static org.junit.Assert.*;

public class MessageProviderTest {

    @Test
    public void testMessageFromMessageProvider(){
        final String originalMessage = "Hello World";
        MessageProvider messageProvider = new MessageProvider();
        String message = messageProvider.getMessage();
        assertEquals("Validating Message",originalMessage,message);
    }
}
