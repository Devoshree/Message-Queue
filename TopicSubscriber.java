package Model;

import public_interface.ISubscriber;
import java.util.concurrent.atomic.AtomicInteger;

public class TopicSubscriber {
    private ISubscriber subscriber;// We used Atomic integer to prevent unwanted changes in multi threaded environment
    private final AtomicInteger offset;

    public TopicSubscriber(final ISubscriber subscriberId){
        this.offset = new AtomicInteger(0);
        this.subscriber = subscriberId;
    }

    public ISubscriber getSubscriber() {
        return subscriber;
    }

    public AtomicInteger getOffset() {
        return offset;
    }
}
