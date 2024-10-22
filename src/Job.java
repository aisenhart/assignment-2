public class Job implements Comparable<Job> {
    private final int identifier;
    private final int processingTime;
    private final int priority;
    //private final int arrivalTime;

    public Job(int id, int priority, int processingTime) {
        this.identifier = id;
        this.processingTime = processingTime;
        this.priority = priority;
    }
    public Job(int id, int processingTime) {
        this.identifier = id;
        this.processingTime = processingTime;
        this.priority = 0;
    }
  /*
    I am a bit confused with the Assignment, specifically with task 3
    It wants arrival time in the constructor, but I already have a constructor with 3 int parameters
    I feel as though arrival time should be a double. Which would solve the problem.
*/
    public Job(int id, int processingTime, int arrivalTime){
        this.identifier = id;
        this.processingTime = processingTime;
        this.priority = 0;
        this.arrivalTime = arrivalTime;

    }



    public int getIdentifier() {
        return identifier;
    }
    public int getProcessingTime(){
        return processingTime;
    }
    public int getPriority(){
        return priority;
    }

    @Override
    public int compareTo(Job other) {
       if(this.priority == other.priority){
           return Integer.compare(this.processingTime, other.processingTime);
       }
       else if (this.priority > other.priority){
           return 1;
       }
       else {return -1;}

    }

    @Override
    public String toString(){
        if(this.priority == 0){
            return "Job ID: " + this.getIdentifier() + ", Processing Time: " + this.getProcessingTime();
        }
        return "Job ID: " + this.getIdentifier() + ", Priority: " + this.getPriority()+   ", Processing Time: " + this.getProcessingTime();
    }
}



