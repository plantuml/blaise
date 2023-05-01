package blaise;

import java.util.ArrayList;
import java.util.List;

public class StanzaRawLine implements Stanza {

    private final List<BToken> tokens = new ArrayList<>();

    public StanzaRawLine(Inspector<BToken> inspector) {
        while (inspector.peek(0).getType() != BTokenType.END_OF_LINE) {
            tokens.add(inspector.peek(0));
            inspector.moveForward();
        }
        tokens.add(inspector.peek(0));
        inspector.moveForward();
    }

    public StanzaType getType() {
        return StanzaType.RAW;
    }

    @Override
    public String toString() {
        return tokens.toString();
    }
}
