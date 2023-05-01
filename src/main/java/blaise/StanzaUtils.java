package blaise;

import blaise.exception.BParsingException;

public abstract class StanzaUtils {

    private StanzaUtils() {
    }

    public static Stanza build(Inspector<BToken> inspector) throws BParsingException {
        if (StanzaAffectation.isAffectation(inspector.cloneMe()))
            return new StanzaAffectation(inspector);

        return new StanzaRawLine(inspector);

    }



    public static void ignoreSpaces(Inspector<BToken> inspector) {
        while (isOfType(BTokenType.SPACES, inspector.peek(0)))
            inspector.moveForward();
    }

    public static boolean isOfType(BTokenType type, BToken token) {
        if (token == null)
            return false;
        return token.getType() == type;
    }

}
