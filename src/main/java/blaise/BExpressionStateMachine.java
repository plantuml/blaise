package blaise;

import blaise.exception.BParsingEndOfDataException;
import blaise.exception.BParsingException;
import blaise.exception.BParsingIllegalStateException;

public class BExpressionStateMachine {

    private final Inspector<BToken> inspector;
    private final BTokenType endingMarker;

    public BExpressionStateMachine(BTokenType endingMarker, Inspector<BToken> inspector) {
        this.inspector = inspector;
        this.endingMarker = endingMarker;
    }

    enum State {
        START, AFTER_IDENT, AFTER_IDENT_AND_DOT, AFTER_NUMBER, FINISHED
    }

    private State currentState = State.START;

    public boolean isFinished() {
        return currentState == State.FINISHED;
    }

    public void runMachine() throws BParsingException {
        while (true) {
            final BToken peek = inspector.peek(0);
            if (peek == null)
                throw new BParsingEndOfDataException();
            executeTransition(peek.getType());
            inspector.moveForward();
            if (isFinished())
                return;
        }
    }


    private void executeTransition(BTokenType type) throws BParsingException {
        if (isFinished())
            throw new BParsingIllegalStateException();

        switch (currentState) {
            case START:
                manageStart(type);
                return;
            case AFTER_NUMBER:
                manageAfterNumber(type);
                return;
            case AFTER_IDENT:
                manageAfterIdent(type);
                return;
            case AFTER_IDENT_AND_DOT:
                manageAfterIdentAndDot(type);
                return;
        }
        throw new BParsingException();
    }

    private void manageStart(BTokenType type) throws BParsingException {
        switch (type) {
            case SPACES:
            case END_OF_LINE:
                return;
            case PARENTHESIS_OPEN:
                inspector.moveForward();
                final BExpressionStateMachine submachine = new BExpressionStateMachine(BTokenType.PARENTHESIS_CLOSE, inspector);
                submachine.runMachine();
                inspector.moveBackward();
                currentState = State.AFTER_NUMBER;
                return;
            case IDENTIFIER:
                currentState = State.AFTER_IDENT;
                return;
            case INTEGER:
                currentState = State.AFTER_NUMBER;
                return;
        }
        throw new BParsingException();
    }


    private void manageAfterNumber(BTokenType type) throws BParsingException {
        if (type == endingMarker) {
            currentState = State.FINISHED;
            return;
        }
        switch (type) {
            case SPACES:
            case END_OF_LINE:
                return;
            case OPERATOR:
                currentState = State.START;
                return;
        }
        throw new BParsingException();
    }

    private void manageAfterIdent(BTokenType type) throws BParsingException {
        if (type == endingMarker) {
            currentState = State.FINISHED;
            return;
        }
        switch (type) {
            case SPACES:
            case END_OF_LINE:
                return;
            case OPERATOR:
                currentState = State.START;
                return;
            case DOT:
                currentState = State.AFTER_IDENT_AND_DOT;
                return;
        }
        throw new BParsingException();
    }

    private void manageAfterIdentAndDot(BTokenType type) throws BParsingException {
        switch (type) {
            case SPACES:
            case END_OF_LINE:
                return;
            case IDENTIFIER:
                currentState = State.AFTER_IDENT;
                return;
        }
        throw new BParsingException();
    }


}
