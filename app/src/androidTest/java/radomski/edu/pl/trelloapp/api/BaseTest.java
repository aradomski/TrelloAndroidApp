package radomski.edu.pl.trelloapp.api;

import android.support.test.InstrumentationRegistry;

import java.util.concurrent.CountDownLatch;

import radomski.edu.pl.trelloapp.test.DaggerTestComponent;
import radomski.edu.pl.trelloapp.test.TestComponent;
import radomski.edu.pl.trelloapp.test.TestModule;

public class BaseTest {

    private CountDownLatch countDownLatch;

    protected TestComponent testComponent;

    public BaseTest() {
        testComponent = DaggerTestComponent.builder().testModule(new TestModule(InstrumentationRegistry.getContext())).build();
    }

    protected CountDownLatch getCountDownLatch() {
        countDownLatch = new CountDownLatch(1);
        return countDownLatch;
    }

    protected void waitUntilCompleted() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}