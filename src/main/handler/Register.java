package handler;

import log.Subscriber;

import java.util.LinkedList;
import java.util.List;

public class Register {
    List<Subscriber> subscribers = new LinkedList<>();

    public void eventLogRegister(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    /**
     * @param startStatus say if the event start (true) or end (false)
     * @param single say if the event occur on single thing or all of them
     */
    public void publish(boolean startStatus, boolean single) {
        for (Subscriber subscriber : subscribers) {
            subscriber.eventAction(startStatus, single);
        }
    }
}
