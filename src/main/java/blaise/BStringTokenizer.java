package blaise;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BStringTokenizer {

    private String current;

    public BStringTokenizer(String line) {
        this.current = line;
    }

    public BToken getNextToken() {
        if (current.length() == 0)
            return null;

        final char first = current.charAt(0);
        if (first == '=')
            return getByFixedSize(1, BTokenType.AFFECTATION);

        if (first == '!')
            return getByFixedSize(1, BTokenType.EXCLAMATION_MARK);

        if (first == ':')
            return getByFixedSize(1, BTokenType.COLON);

        if (first == '+' || first == '-' || first == '/' || first == '*')
            return getByFixedSize(1, BTokenType.OPERATOR);

        if (first == '.')
            return getByFixedSize(1, BTokenType.DOT);

        if (first == ',')
            return getByFixedSize(1, BTokenType.COMMA);

        if (first == '[')
            return getByFixedSize(1, BTokenType.SQUARE_BRACKET_OPEN);
        if (first == ']')
            return getByFixedSize(1, BTokenType.SQUARE_BRACKET_CLOSE);

        if (first == '{')
            return getByFixedSize(1, BTokenType.CURLY_BRACKET_OPEN);
        if (first == '}')
            return getByFixedSize(1, BTokenType.CURLY_BRACKET_CLOSE);

        if (first == '(')
            return getByFixedSize(1, BTokenType.PARENTHESIS_OPEN);
        if (first == ')')
            return getByFixedSize(1, BTokenType.PARENTHESIS_CLOSE);

        if (current.startsWith("?="))
            return getByFixedSize(2, BTokenType.AFFECTATION);

        if (Character.isDigit(first))
            return getByRegex("([0-9.]+)", BTokenType.INTEGER);
        if (Character.isSpaceChar(first))
            return getByRegex("(\\s+)", BTokenType.SPACES);
        if (first == '\'')
            return getSimpleString();
        if (first == '$')
            if (current.length() > 1 && current.substring(1, 2).matches("[_a-zA-Z]"))
                return getByRegex("(\\$?[_a-zA-Z]\\w*)", BTokenType.IDENTIFIER);
        if (current.substring(0, 1).matches("[_a-zA-Z]"))
            return getByRegex("([_a-zA-Z]\\w*)", BTokenType.IDENTIFIER);

        throw new IllegalStateException(current);
    }

    private BToken getSimpleString() {
        final StringBuilder result = new StringBuilder();
        final char sep = current.charAt(0);
        int pos = 1;
        while (pos < current.length()) {
            if (current.charAt(pos) == sep) {
                current = current.substring(pos + 1);
                return new BToken(BTokenType.STRING, result.toString());
            }
            result.append(current.charAt(pos));
            pos++;
        }
        throw new IllegalStateException();
    }

    private BToken getByFixedSize(int nb, BTokenType type) {
        final String single = current.substring(0, nb);
        current = current.substring(nb);
        return new BToken(type, single);
    }

    private BToken getByRegex(String regex, BTokenType type) {
        final Pattern p = Pattern.compile(regex);
        final Matcher m = p.matcher(current);
        if (m.find() == false)
            throw new IllegalStateException(current);
        final String result = m.group(0);
        current = current.substring(result.length());
        return new BToken(type, result);
    }


}
