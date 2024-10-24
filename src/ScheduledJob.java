public class ScheduledJob implements Comparable<ScheduledJob> {
    public int arrivalTime;
    public int processingTime;
    public int identifier;

    public ScheduledJob(int id, int processingTime, int arrivalTime) {
        this.arrivalTime = arrivalTime;
        this.processingTime = processingTime;
        this.identifier = id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public int getIdentifier() {
        return identifier;
    }

    @Override
    public int compareTo(ScheduledJob other) {
        return Integer.compare(this.processingTime, other.processingTime);
    }

    @Override
    public String toString() {
        return String.valueOf(this.getIdentifier());
    }
}