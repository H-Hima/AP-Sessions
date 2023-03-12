package Synchronization;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class MySemaphore {
    Object monitor;
    Integer permits=2;
    Integer bound=2;

    MySemaphore() {
        monitor=this;
    }

    MySemaphore(Object monitor) {
        this.monitor=monitor;
    }

    MySemaphore(Object monitor,int permits) {
        this(monitor,permits,2);
    }

    MySemaphore(Object monitor,int permits,int bound) {
        this.permits=permits;
        this.monitor=monitor;
        this.bound=bound;
    }

    MySemaphore(int permits,int bound) {
        this(new Object(),permits,bound);
    }

    void myWait() {
        synchronized (monitor) {
            while(permits==0) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            permits--;
        }
    }

    void myNotify() {
        synchronized (monitor) {
            if(permits<bound)
                permits++;
            monitor.notifyAll();
        }
    }
}
