package log;

import java.util.LinkedList;
import java.util.List;

public class Register {
    List<EventAbstract> subscribers = new LinkedList<>();

    protected void logRegister(EventAbstract eventAbstract) {
        subscribers.add(eventAbstract);
    }

    public void publish(boolean startStatus) {
        for (EventAbstract eventAbstract : subscribers) {
            eventAbstract.writeMessage(startStatus);
        }
    }
}
