package public_interface;

import Model.Message;
// It has public facing APIs the one with clients, whosoever uses our queue, will need to deal with these
public interface ISubscriber {

    public String getId();

    public void consume(Message message) throws InterruptedException;

}
