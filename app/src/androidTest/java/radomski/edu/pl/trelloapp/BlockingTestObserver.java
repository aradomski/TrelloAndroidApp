package radomski.edu.pl.trelloapp;

import java.util.concurrent.CountDownLatch;

import rx.observers.TestObserver;

public class BlockingTestObserver<T> extends TestObserver<T> {

    private CountDownLatch countDownLatch;

    public BlockingTestObserver(CountDownLatch countDownLatch) {
        super();
        this.countDownLatch = countDownLatch;

    }

    @Override
    public void onCompleted() {
        super.onCompleted();
        countDownLatch.countDown();
    }


    @Override
    public void onError(Throwable e) {
        super.onError(e);
        countDownLatch.countDown();
    }


}