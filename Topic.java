package Model;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private final String topicId;
    private final String topicName;
    private final List<Message> messageList;
    private final List<TopicSubscriber> subscriberList;

    public Topic(String topicId, String topicName){
        this.topicId = topicId;
        this.topicName = topicName;
        this.subscriberList = new ArrayList<>();
        this.messageList = new ArrayList<>();
    }

    public synchronized void addMessage(@NotNull final Message msg){
        messageList.add(msg);
    }

    public void addSubscriber(@NotNull final TopicSubscriber subscriber){
        subscriberList.add(subscriber);
    }

    public String getTopicId(){
        return topicId;
    }

    public List<TopicSubscriber> getSubscriberList(){
        return subscriberList;
    }

    public String getTopicName(){
        return topicName;
    }

    public List<Message> getMessages() {
        return messageList;
    }
}
