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
        Assertions.assertEquals("[IDENTIFIER a, END_OF_LINE ]", stanzas.get(0).toString());
        Assertions.assertEquals(StanzaType.RAW, stanzas.get(1).getType());
        Assertions.assertEquals("[IDENTIFIER b, END_OF_LINE ]", stanzas.get(1).toString());
        Assertions.assertEquals(StanzaType.RAW, stanzas.get(2).getType());
        Assertions.assertEquals("[IDENTIFIER c, END_OF_LINE ]", stanzas.get(2).toString());

    }
    @Test
    void compile02() throws BParsingException {
        final BSource source = new BSource();
        source.addLine("a");
        source.addLine("!$foo1 = 42");
        source.addLine("c");

        final List<Stanza> stanzas = source.compileMe();
        Assertions.assertEquals(3, stanzas.size());
        Assertions.assertEquals(StanzaType.RAW, stanzas.get(0).getType());
        Assertions.assertEquals("[IDENTIFIER a, END_OF_LINE ]", stanzas.get(0).toString());
        Assertions.assertEquals(StanzaType.AFFECTATION, stanzas.get(1).getType());
        Assertions.assertEquals("[EXCLAMATION_MARK !, IDENTIFIER $foo1, SPACES  , AFFECTATION =, SPACES  , INTEGER 42, END_OF_LINE ]", stanzas.get(1).toString());
        Assertions.assertEquals(StanzaType.RAW, stanzas.get(2).getType());
        Assertions.assertEquals("[IDENTIFIER c, END_OF_LINE ]", stanzas.get(2).toString());

    }


}