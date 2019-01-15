import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {

    static <T extends Comparable<T>> int nonOverlappingRoom(List<Interval<T>> l) {

        //Using a queue to manage in FIFO order the intervals end: we're creating a queue
        //with a maximum size of size l.
        //Also we're using the variable room to take track of the taken room.
        Deque<T> queue = new ArrayDeque<>(l.size());
        int room = 0;

        //Sorting the list for the beginning interval
        l.sort((i1, i2) -> ((i1.start()).compareTo(i2.start())));

        //Checking if the array is greater than 0:
        //if yes, there is always AT LEAST 1 room to occupy, else return.
        if(l.size() != 0) {
            queue.addFirst(l.get(0).end());
            room++;
        } else {
            return 0;
        }

        //Starting at 1 to N, we need to check at every iteration if the starting time of the
        //interval at i is lesser than the end time of the first position of the queue: if it is,
        //that means that the lecture will be "overlapping" to another lecture and we need to increase
        //the room number.
        //Otherwise, if interval[i].first is greater that means that it starts later than the current lecture's end,
        //and then we don't need to overlap a room. Pop the value (because we taken the room).
        //The second if cycle check if the current end time is lessed than the first in the queue: if yes, that means that
        //the current lectures will end before the queue one: so we need to push it first in the queue, otherwise in
        //it's back.
        for(int i=1; i<l.size(); i++) {

            if((l.get(i).start()).compareTo(queue.peekFirst()) < 0) {
                room++;
            } else {
                queue.removeFirst();
            }

            if((l.get(i).end()).compareTo(queue.peekFirst()) < 0) {
                queue.addFirst(l.get(i).start());
            } else {
                queue.addLast(l.get(i).end());
            }

        }

        return room;

    }


    public static void main(String[] args) {
        List<Interval<Integer>> lecturesList = new ArrayList<>();

        //adding values to the list
        lecturesList.add(new Interval<>(1,30));
        lecturesList.add(new Interval<>(20,35));
        lecturesList.add(new Interval<>(25,65));
        lecturesList.add(new Interval<>(22,32));
        lecturesList.add(new Interval<>(31,34));
        lecturesList.add(new Interval<>(2,10));


        //our interval list: [(30,75), (0,50), (60,150)]

        System.out.println("With classroom lectures intervals:");
        lecturesList.forEach((n) -> System.out.print(" [" + n.start() + "," + n.end() + "] "));
        System.out.println("\nWe need to have a minimum of " + nonOverlappingRoom(lecturesList) + " room to offer.");
    }
}
