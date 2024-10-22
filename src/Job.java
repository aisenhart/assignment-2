public class Job implements Comparable<Job> {
    private int identifier;
    private int processingTime;

    public Job(int id, int priority) {
        this.identifier = id;
        this.processingTime = priority;
    }

    public int getIdentifier() {
        return identifier;
    }
    public int getProcessingTime(){
        return processingTime;
    }

    @Override
    public int compareTo(Job other) {
        return Integer.compare(this.processingTime, other.processingTime);
    }

    @Override
    public String toString(){
        return "Job ID: " + this.getIdentifier() + ", Processing Time: " + this.getProcessingTime();
    }
}



