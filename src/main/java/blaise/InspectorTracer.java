package blaise;

import java.util.ArrayList;
import java.util.List;

public class InspectorTracer<O> implements Inspector<O> {

    private final Inspector<O> source;

    private final List<O> trace = new ArrayList<>();

    public InspectorTracer(Inspector<O> source) {
        this.source = source;
    }

    @Override
    public O peek(int ahead) {
        return source.peek(ahead);
    }

    @Override
    public void moveForward() {
        trace.add(source.peek(0));
        source.moveForward();
    }

    @Override
    public void moveBackward() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Inspector<O> cloneMe() {
        throw new UnsupportedOperationException();
    }

    public List<O> getTrace() {
        return trace;
    }

}
