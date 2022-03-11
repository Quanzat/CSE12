/**
 * Name: Quan Tran 
 * ID: A16191839               
 * Email: qutran@ucsd.edu              
 * Sources used: Lecture      
 * 
 * TODO:
 */

/**
 * TODO:
 */
public class MyCalendar {
    MyTreeMap<Integer, Integer> calendar;

    public MyCalendar() {
        calendar = new MyTreeMap<>();
    }

    public boolean book(int start, int end) {
        if (start < 0 || start >= end)
            throw new IllegalArgumentException();

        Integer startBook = calendar.floorKey(start);
        Integer lastBook = calendar.get(startBook);
        Integer nextBook = calendar.ceilingKey(start);
        Integer endBook = calendar.get(nextBook);

        if (startBook == null && nextBook == null) {
            calendar.put(start, end);
            return true;
        }

        else if (startBook == null) {
            if (end > nextBook) {
                return false;
            }
            calendar.put(start, end);
            return true;
        }

        else if (nextBook == null) {
            if (start < lastBook) {
                return false;
            }
            calendar.put(start, end);
            return true;
        }

        else {
            if (start < lastBook || end < lastBook || end > nextBook
                    || end > endBook) {
                return false;
            }

            else {
                calendar.put(start, end);
                return true;
            }
        }
    }

    public MyTreeMap getCalendar() {
        return this.calendar;
    }
}