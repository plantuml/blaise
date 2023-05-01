package blaise;

import blaise.exception.BParsingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class BSourceBlocTest {


    @Test
    void compile01() throws BParsingException {
        final BSource source = new BSource();
        source.addLine("a");
        source.addLine("b");
        source.addLine("c");

        final List<Stanza> stanzas = source.compileMe();
        Assertions.assertEquals(3, stanzas.size());
        Assertions.assertEquals(StanzaType.RAW, stanzas.get(0).getType());
        Assertions.assertEquals("[IDENTIFIER a]", stanzas.get(0).toString());
        Assertions.assertEquals(StanzaType.RAW, stanzas.get(1).getType());
        Assertions.assertEquals("[IDENTIFIER b]", stanzas.get(1).toString());
        Assertions.assertEquals(StanzaType.RAW, stanzas.get(2).getType());
        Assertions.assertEquals("[IDENTIFIER c]", stanzas.get(2).toString());

    }


}