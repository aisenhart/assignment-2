public class Main {
    public static void main(String[] args) {
        Job[] jobs = new Job[5];
        jobs[0] = new Job(1, 2);
        jobs[1] = new Job(2, 3);
        jobs[2] = new Job(3, 4);
        jobs[3] = new Job(4, 2);
        jobs[4] = new Job(5, 1);
        scheduleJobs(jobs);

    }
    public static void scheduleJobs(Job[] jobs){
        MinPQ<Job> priorityQueue = new MinPQ<>(jobs.length);
        int currentTime = 0;
        int totalCompletionTime = 0;

        for (Job job : jobs) {
            priorityQueue.insert(job);
        }

        while (!priorityQueue.isEmpty()) {
            Job job = priorityQueue.delMin();
            currentTime += job.getProcessingTime();
            totalCompletionTime += currentTime;
            System.out.println("Completed " + job + " at time " + currentTime);
        }
        double averageCompletionTime = (double) totalCompletionTime / jobs.length;
        System.out.println("Average Completion Time: " + averageCompletionTime);
    }
}