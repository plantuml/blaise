package blaise;

import blaise.exception.BParsingEndOfDataException;
import blaise.exception.BParsingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BExpressionStateMachineTest {

    @Test
    void test1() {
        final BSource line = BSource.build("42 + 43");
        runMachineOk(line);
    }

    private static void runMachineOk(BSource line) {
        BExpressionStateMachine machine = new BExpressionStateMachine(BTokenType.END_OF_LINE, line.noComments());
        try {
            machine.runMachine();
        } catch (BParsingException e) {
            assertFalse(true);
        }
        assertTrue(machine.isFinished());
    }

    private static BParsingException runMachineException(BSource line) {
        BExpressionStateMachine machine = new BExpressionStateMachine(BTokenType.END_OF_LINE, line.noComments());
        try {
            machine.runMachine();
        } catch (BParsingException e) {
            return e;
        }
        assertFalse(true);
        return null;
    }

    @Test
    void test2() {
        final BSource line = BSource.build("toto.titi.tutu + 42");
        runMachineOk(line);
    }


    @Test
    void test3() {
        final BSource line = BSource.build("42 + ");
        line.addLine("43");
        runMachineOk(line);
    }

    @Test
    void test4() {
        final BSource line = BSource.build("44");
        runMachineOk(line);
    }


    @Test
    void test5() {
        final BSource line = BSource.build("(44)");
        runMachineOk(line);
    }


    @Test
    void test6() {
        final BSource line = BSource.build("toto + (44 + 3) + toto * (14 + (3 * 14) )");
        runMachineOk(line);
    }


    @Test
    void test7() {
        final BSource line = BSource.build("toto + (44 + 3) + toto * (14 + (3 * 14)");
        BParsingException e = runMachineException(line);
        System.err.println("e=" + e);

        assertTrue(e instanceof BParsingEndOfDataException);
    }


}