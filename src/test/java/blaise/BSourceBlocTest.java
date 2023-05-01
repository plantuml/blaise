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

    }


}