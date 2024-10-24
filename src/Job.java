public class Job implements Comparable<Job> {
    public int identifier;
    public int processingTime;
    public int priority;

    public Job(int id, int processingTime, int priority) {
        this.identifier = id;
        this.processingTime = processingTime;
        this.priority = priority;
    }
    public Job(int id, int processingTime) {
        this.identifier = id;
        this.processingTime = processingTime;
        this.priority = 0;
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



