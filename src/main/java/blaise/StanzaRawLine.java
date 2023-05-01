package blaise;

public class StanzaRawLine implements Stanza {

    public StanzaRawLine(Inspector<BToken> inspector) {
        while (inspector.peek(0).getType() != BTokenType.END_OF_LINE)
            inspector.moveForward();
        inspector.moveForward();
    }

    public StanzaType getType() {
        return StanzaType.RAW;
    }
}
