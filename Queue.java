package public_interface;

import Handler.TopicHandler;
import Model.Message;
import Model.Topic;
import Model.TopicSubscriber;
import com.sun.istack.internal.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Queue {
    private final Map<String, TopicHandler> topicHandlers;

    public Queue(){
        this.topicHandlers = new HashMap<>();
    }

    public Topic createTopic(@NotNull final String topicName){
        final Topic topic = new Topic(UUID.randomUUID().toString(), topicName);
        TopicHandler topicHandler = new TopicHandler(topic);
        topicHandlers.put(topic.getTopicId(), topicHandler);
        System.out.println("Created Topic "+topicName);
        return topic;
    }

    public void subscribe(@NotNull final ISubscriber subscriber, @NotNull final Topic topic){
        System.out.println(subscriber.getId()+" is subscribed to topic: "+topic.getTopicName());
        topic.addSubscriber(new TopicSubscriber(subscriber));
    }

    public void publish(@NotNull final Topic topic, @NotNull final Message message){
        topic.addMessage(message);
        System.out.println(message.getMsg()+": Message Added to topic :"+topic.getTopicName());
        // We don't want to block the callers while publish happens also this is not persistent thread it just publishes and vanishes away
        new Thread(() -> topicHandlers.get(topic.getTopicId()).publish()).start();
    }
}
