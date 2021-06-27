package Handler;

import Model.Message;
import Model.Topic;
import Model.TopicSubscriber;

public class SubscriberWorker implements Runnable{
    private final Topic topic;
    private final TopicSubscriber subscriber;

    public SubscriberWorker(Topic topic, TopicSubscriber topicSubscriber) {
        this.subscriber = topicSubscriber;
        this.topic = topic;
    }
    @Override
    public void run(){
        synchronized (subscriber){
            do{
                int offset = subscriber.getOffset().get();
                while (offset >= topic.getMessages().size()){
                    try {
                        subscriber.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Message msg = topic.getMessages().get(offset);
                try {
                    subscriber.getSubscriber().consume(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                subscriber.getOffset().compareAndSet(offset, offset+1);
            }
            while (true);
        }
    }

    synchronized public void wakeUpIfNeeded() {
        synchronized (subscriber){
            subscriber.notify();
        }
    }
}
