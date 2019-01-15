public class Interval<T extends Comparable<T>> {
    @Attributes
    private T start;
    private T end;

    @Constructors
    public Interval() {}

    public Interval(T start, T end) {
        this.start = start;
        this.end = end;
    }

    @AccessModifiers
    public T start() {
        return this.start;
    }

    public T end() {
        return this.end;
    }

    public void setStart(T start) {
        this.start = start;
    }

    public void setEnd(T end) {
        this.end = end;
    }

}
