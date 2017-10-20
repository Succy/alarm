package cn.succy.alarm.test;

import org.junit.Test;

/**
 * @author Succy
 * @date 2017-10-15 13:04
 **/

public class ThreadTest {
    //@Test
    //public void test() {
    //    new Thread(new Task()).start();
    //}

    public static void main(String[] args) {
        new Thread(new Task()).start();
    }
}
