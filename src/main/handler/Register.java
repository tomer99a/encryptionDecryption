package handler;

import log.SubscriberLog4jLogger;

import java.util.LinkedList;
import java.util.List;

public class Register {
    List<SubscriberLog4jLogger> subscribers = new LinkedList<>();

    public void eventLogRegister(SubscriberLog4jLogger subscriber) {
        subscribers.add(subscriber);
    }

    /**
     * @param single say if the event occur on single thing or all of them
     */
    public void publish(boolean single) {
        for (SubscriberLog4jLogger subscriber : subscribers) {
            subscriber.eventAction(single);
        }
    }
}
