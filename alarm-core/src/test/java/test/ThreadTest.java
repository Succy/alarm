package test;

/**
 * @author Succy
 * @date 2017-10-15 13:04
 **/

public class ThreadTest {

    public static void main(String[] args) {
        new Thread(new Task()).start();
    }
}
