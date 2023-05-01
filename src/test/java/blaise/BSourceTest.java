package blaise;

import blaise.exception.BParsingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class BSourceTest {


    @Test
    void compile01() throws BParsingException {
        final BSource source = new BSource();
        source.addLine("!$foo1 = 42");

        final List<Stanza> stanzas = source.compileMe();
        Assertions.assertEquals(1, stanzas.size());
        Assertions.assertEquals(StanzaType.AFFECTATION, stanzas.get(0).getType());

    }

    private void assertToken(BTokenType type, String value, BToken token) {
        Assertions.assertEquals(type, token.getType());
        Assertions.assertEquals(value, token.getValue());
    }


    @Test
    void simple42() {
        final BSource line = BSource.build("42");
        Assertions.assertEquals(2, line.getTotalNumberOfTokens());
        assertToken(BTokenType.INTEGER, "42", line.getToken(0));
        assertToken(BTokenType.END_OF_LINE, "", line.getToken(1));

    }


    @Test
    void simple42_43() {
        final BSource line = BSource.build("42 43");
        Assertions.assertEquals(4, line.getTotalNumberOfTokens());

        assertToken(BTokenType.INTEGER, "42", line.getToken(0));
        assertToken(BTokenType.SPACES, " ", line.getToken(1));
        assertToken(BTokenType.INTEGER, "43", line.getToken(2));
        assertToken(BTokenType.END_OF_LINE, "", line.getToken(3));

    }

    @Test
    void simpleEquals() {
        final BSource line = BSource.build("=");
        Assertions.assertEquals(2, line.getTotalNumberOfTokens());
        assertToken(BTokenType.AFFECTATION, "=", line.getToken(0));
        assertToken(BTokenType.END_OF_LINE, "", line.getToken(1));
    }

    @Test
    void simpleEqualsConditional() {
        final BSource line = BSource.build("?=");
        Assertions.assertEquals(2, line.getTotalNumberOfTokens());
        assertToken(BTokenType.AFFECTATION, "?=", line.getToken(0));
        assertToken(BTokenType.END_OF_LINE, "", line.getToken(1));
    }


    @Test
    void simpleString1() {
        final BSource line = BSource.build("'foo1'");
        Assertions.assertEquals(2, line.getTotalNumberOfTokens());
        assertToken(BTokenType.STRING, "foo1", line.getToken(0));
        assertToken(BTokenType.END_OF_LINE, "", line.getToken(1));
    }

    @Test
    void simpleString2() {
        final BSource line = BSource.build("'foo1'4'foo3'");
        Assertions.assertEquals(4, line.getTotalNumberOfTokens());
        assertToken(BTokenType.STRING, "foo1", line.getToken(0));
        assertToken(BTokenType.INTEGER, "4", line.getToken(1));
        assertToken(BTokenType.STRING, "foo3", line.getToken(2));
        assertToken(BTokenType.END_OF_LINE, "", line.getToken(3));
    }


    @Test
    void simpleIdentifier() {
        final BSource line = BSource.build("$foo1");
        Assertions.assertEquals(2, line.getTotalNumberOfTokens());
        assertToken(BTokenType.IDENTIFIER, "$foo1", line.getToken(0));
        assertToken(BTokenType.END_OF_LINE, "", line.getToken(1));

        assertToken(BTokenType.IDENTIFIER, "foo1", BSource.build("foo1").getToken(0));

    }


    @Test
    void simple42_add_43() {
        final BSource line = BSource.build("42 + 43");
        Assertions.assertEquals(6, line.getTotalNumberOfTokens());

        assertToken(BTokenType.INTEGER, "42", line.getToken(0));
        assertToken(BTokenType.SPACES, " ", line.getToken(1));
        assertToken(BTokenType.OPERATOR, "+", line.getToken(2));
        assertToken(BTokenType.SPACES, " ", line.getToken(3));
        assertToken(BTokenType.INTEGER, "43", line.getToken(4));
        assertToken(BTokenType.END_OF_LINE, "", line.getToken(5));

    }



}