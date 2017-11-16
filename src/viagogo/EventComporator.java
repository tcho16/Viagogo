package viagogo;

import java.util.Comparator;

public class EventComporator implements Comparator<Event> {

    @Override
    public int compare(Event t, Event t1) {
        if (t.getDistance() < t1.getDistance()) {
            return -1;
        } else if (t.getDistance() == t1.getDistance()) {
            return 0;
        } else {
            return 1;
        }
    }

}
