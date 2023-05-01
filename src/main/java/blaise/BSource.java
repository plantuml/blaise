package blaise;

import blaise.exception.BParsingException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BSource {

    private final List<String> lines = new ArrayList<>();
    private final List<BToken> tokens = new ArrayList<>();

    public static BSource build(String line) {
        final BSource result = new BSource();
        result.addLine(line);
        return result;
    }


    public void addLine(String line) {
        lines.add(line);

        final BStringTokenizer tokenizer = new BStringTokenizer(line);
        while (true) {
            final BToken token = tokenizer.getNextToken();
            if (token == null) {
                tokens.add(new BToken(BTokenType.END_OF_LINE, ""));
                return;
            }

            if (token.getType() != BTokenType.COMMENT)
                tokens.add(token);
        }

    }

    public Inspector<BToken> noComments() {
        return new ListInspector<>(tokens);
    }

    public List<Stanza> compileMe() throws BParsingException {
        final List<Stanza> stanzas = new ArrayList<>();
        final Inspector<BToken> inspector = noComments();
        while (inspector.peek(0) != null) {
            stanzas.add(StanzaUtils.build(inspector));
        }
        return Collections.unmodifiableList(stanzas);
    }


    public int getTotalNumberOfTokens() {
        return tokens.size();
    }

    public BToken getToken(int index) {
        return tokens.get(index);
    }
}
