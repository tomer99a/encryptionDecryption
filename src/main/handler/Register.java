package handler;

import log.SubscriberLog4jLogger;

import java.util.LinkedList;
import java.util.List;

public class Register {
    List<SubscriberLog4jLogger> subscribers = new LinkedList<>();

    public void eventLogRegister(SubscriberLog4jLogger subscriber) {
        subscribers.add(subscriber);
    }

    public void publish() {
        for (SubscriberLog4jLogger subscriber : subscribers) {
            subscriber.eventAction();
        }
    }
}
