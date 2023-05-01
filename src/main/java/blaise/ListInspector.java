package blaise;

import java.util.List;

public class ListInspector<O> implements Inspector<O> {

    private final List<O> list;
    private int pos = 0;

    public ListInspector(List<O> list) {
        this.list = list;
    }

    @Override
    public O peek(int ahead) {
        final int tmp = pos + ahead;
        if (tmp < list.size())
            return list.get(tmp);
        return null;
    }


    @Override
    public void moveForward() {
        pos++;
    }

    @Override
    public void moveBackward() {
        pos--;
        if (pos<0)
            throw new IllegalStateException();
    }

    @Override
    public Inspector<O> cloneMe() {
        final ListInspector<O> result = new ListInspector<>(list);
        result.pos = this.pos;
        return result;
    }
}
