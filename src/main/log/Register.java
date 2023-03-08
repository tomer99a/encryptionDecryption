package log;

import java.util.LinkedList;
import java.util.List;

public class Register {
    List<EventAbstract> subscribers = new LinkedList<>();

    protected void logRegister(EventAbstract eventAbstract) {
        subscribers.add(eventAbstract);
    }

    /**
     * @param startStatus say if the event start (true) or end (false)
     * @param single say if the event occur on single thing or all of them
     */
    public void publish(boolean startStatus, boolean single) {
        for (EventAbstract eventAbstract : subscribers) {
            eventAbstract.writeMessage(startStatus, single);
        }
    }
}
