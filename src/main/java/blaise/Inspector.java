package blaise;

public interface Inspector<O> {
    O peek(int ahead);

    void moveForward();

    void moveBackward();

    Inspector<O> cloneMe();
}
