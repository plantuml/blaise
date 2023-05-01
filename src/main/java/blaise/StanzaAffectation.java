package blaise;

import blaise.exception.BParsingException;

public class StanzaAffectation implements Stanza {

    public StanzaAffectation(Inspector<BToken> inspector) throws BParsingException {
        if (isAffectation(inspector) == false)
            throw new BParsingException();
        final BExpressionStateMachine machine = new BExpressionStateMachine(BTokenType.END_OF_LINE, inspector);
        machine.runMachine();
    }

    public static boolean isAffectation(Inspector<BToken> inspector) {
        if (StanzaUtils.isOfType(BTokenType.EXCLAMATION_MARK, inspector.peek(0)) == false)
            return false;
        inspector.moveForward();
        if (StanzaUtils.isOfType(BTokenType.IDENTIFIER, inspector.peek(0)) == false)
            return false;
        StanzaUtils.ignoreSpaces(inspector);
        inspector.moveForward();
        StanzaUtils.ignoreSpaces(inspector);
        if (StanzaUtils.isOfType(BTokenType.AFFECTATION, inspector.peek(0)) == false)
            return false;
        inspector.moveForward();
        return true;
    }

    public StanzaType getType() {
        return StanzaType.AFFECTATION;
    }
}
