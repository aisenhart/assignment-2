public class Main {
    public static void main(String[] args) {
        System.out.println("Task 1:");
        Job[] jobsTask1 = new Job[5];
        jobsTask1[0] = new Job(1,2);
        jobsTask1[1] = new Job(2,3);
        jobsTask1[2] = new Job(3,4);
        jobsTask1[3] = new Job(4,2);
        jobsTask1[4] = new Job(5,1);
        scheduleJobs(jobsTask1);

        System.out.println("Task 2:");
        Job[] jobsTask2 = new Job[5];
        jobsTask2[0] = new Job(1,1, 2);
        jobsTask2[1] = new Job(2,3, 3);
        jobsTask2[2] = new Job(3,4, 4);
        jobsTask2[3] = new Job(4,2, 2);
        jobsTask2[4] = new Job(5,1, 1);
        scheduleJobs(jobsTask2);
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