package handler;

import log.EventLoggerAbstract;

import java.util.LinkedList;
import java.util.List;

public class Register {
    List<EventLoggerAbstract> subscribers = new LinkedList<>();

    public void logRegister(EventLoggerAbstract eventLoggerAbstract) {
        subscribers.add(eventLoggerAbstract);
    }

    /**
     * @param startStatus say if the event start (true) or end (false)
     * @param single say if the event occur on single thing or all of them
     */
    public void publish(boolean startStatus, boolean single) {
        for (EventLoggerAbstract eventLoggerAbstract : subscribers) {
            eventLoggerAbstract.writeMessage(startStatus, single);
        }
    }
}
