package blaise;

import blaise.exception.BParsingException;

import java.util.ArrayList;
import java.util.List;

public class StanzaAffectation implements Stanza {

    private final List<BToken> tokens = new ArrayList<>();

    public StanzaAffectation(Inspector<BToken> inspector) throws BParsingException {

        final InspectorTracer tracer = new InspectorTracer(inspector);

        if (isAffectation(tracer) == false)
            throw new BParsingException();
        final BExpressionStateMachine machine = new BExpressionStateMachine(BTokenType.END_OF_LINE, tracer);
        machine.runMachine();
        this.tokens.addAll(tracer.getTrace());
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

    @Override
    public String toString() {
        return tokens.toString();
    }
}
