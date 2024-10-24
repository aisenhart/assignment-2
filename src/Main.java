import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Would you like to test or run a task?");
        System.out.println("1. Test");
        System.out.println("2. Run");
        int choice = sc.nextInt();
        if (choice == 1) {
            while (true) {
                System.out.println("Which test would you like to run?");
                System.out.println("1. Task1");
                System.out.println("2. Task2");
                System.out.println("3. Task3");
                System.out.println("4. Exit");
                int testChoice = sc.nextInt();
                if (testChoice == 1) {
                    testJobs();
                } else if (testChoice == 2) {
                    testPriorityJobs();
                } else if (testChoice == 3) {
                    testScheduledJobs();
                } else if (testChoice == 4) {
                    break;
                } else {
                    System.out.println("Invalid test choice");
                }
            }
        } else if (choice == 2) {
            while (true) {
                System.out.println("What task would you like to run?");
                System.out.println("1. Task 1");
                System.out.println("2. Task 2");
                System.out.println("3. Task 3");
                System.out.println("4. Exit");
                int task = sc.nextInt();
                if (task == 1) {
                    task1();
                } else if (task == 2) {
                    task2();
                } else if (task == 3) {
                    task3();
                } else if (task == 4) {
                    break;
                } else {
                    System.out.println("Invalid task number");
                }
            }
        } else {
            System.out.println("Invalid choice");
        }

    }

    //Task 1 reads from file
    public static void task1() {
        System.out.println("Task 1:");
        List<Job> jobList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/task1-input.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                int id = Integer.parseInt(parts[0]);
                int processingTime = Integer.parseInt(parts[1]);
                jobList.add(new Job(id, processingTime));
            }

            Job[] jobsTask1 = jobList.toArray(new Job[0]);
            scheduleJobs(jobsTask1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Task 2 reads from file
    public static void task2() {
        System.out.println("Task 2:");
        List<Job> jobList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/task2-input.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                int id = Integer.parseInt(parts[0]);
                int priority = Integer.parseInt(parts[1]);
                int processingTime = Integer.parseInt(parts[2]);
                jobList.add(new Job(id, processingTime, priority));
            }

            Job[] jobsTask2 = jobList.toArray(new Job[0]);
            scheduleJobs(jobsTask2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Task 3 reads from file
    public static void task3() {
        System.out.println("Task 3:");
        List<ScheduledJob> jobList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/task3-input.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                int id = Integer.parseInt(parts[0]);
                int processingTime = Integer.parseInt(parts[2]);
                int arrivalTime = Integer.parseInt(parts[1]);
                jobList.add(new ScheduledJob(id, processingTime, arrivalTime));
            }

            ScheduledJob[] jobsTask3 = jobList.toArray(new ScheduledJob[0]);
            scheduleJobs(jobsTask3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Task 1 & 2 job scheduler
    public static void scheduleJobs(Job[] jobs) {
        PriorityQueue<Job> priorityQueue = new PriorityQueue<>(Comparator
                .comparingInt(Job::getPriority)
                .thenComparingInt(Job::getProcessingTime));
        int currentTime = 0;
        int totalCompletionTime = 0;
        List<Integer> executionOrder = new ArrayList<>();

        for (Job job : jobs) {
            priorityQueue.add(job);
        }

        while (!priorityQueue.isEmpty()) {
            Job job = priorityQueue.poll();
            currentTime += job.getProcessingTime();
            totalCompletionTime += currentTime;
            executionOrder.add(job.getIdentifier());
        }

        double averageCompletionTime = (double) totalCompletionTime / jobs.length;
        System.out.println("Execution order: " + executionOrder);
        System.out.println("Average completion time: " + averageCompletionTime);
    }

    // Task 3 job scheduler
    public static void scheduleJobs(ScheduledJob[] jobs) {
        PriorityQueue<ScheduledJob> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(ScheduledJob::getProcessingTime));
        int currentTime = 0;
        int totalCompletionTime = 0;
        int jobIndex = 0;
        List<Integer> executionOrder = new ArrayList<>();

        Arrays.sort(jobs, Comparator.comparingInt(ScheduledJob::getArrivalTime));
        System.out.println("ID Arrival Processing");
        for (ScheduledJob job : jobs) {
            System.out.println(job.getIdentifier() + " " + job.getArrivalTime() + " " + job.getProcessingTime());
        }

        while (jobIndex < jobs.length || !priorityQueue.isEmpty()) {
            while (jobIndex < jobs.length && jobs[jobIndex].getArrivalTime() <= currentTime) {
                priorityQueue.add(jobs[jobIndex]);
                jobIndex++;
            }

            if (!priorityQueue.isEmpty()) {
                ScheduledJob job = priorityQueue.poll();
                currentTime += job.getProcessingTime();
                totalCompletionTime += currentTime;
                executionOrder.add(job.getIdentifier());
            } else {
                if (jobIndex < jobs.length) {
                    currentTime = jobs[jobIndex].getArrivalTime();
                }
            }
        }

        double averageCompletionTime = (double) totalCompletionTime / jobs.length;
        System.out.println("Execution order: " + executionOrder);
        System.out.println("Average completion time: " + averageCompletionTime);
    }

    public static void testScheduledJobs() {
        ScheduledJob job1 = new ScheduledJob(1, 4, 71);
        ScheduledJob job2 = new ScheduledJob(2, 5, 53);
        ScheduledJob job3 = new ScheduledJob(3, 5, 3);
        ScheduledJob job4 = new ScheduledJob(4, 8, 88);
        ScheduledJob job5 = new ScheduledJob(5, 3, 83);
        scheduleJobs(new ScheduledJob[]{job1, job2, job3, job4, job5});

    }

    public static void testPriorityJobs() {
        Job job1 = new Job(1, 12, 3);
        Job job2 = new Job(2, 1, 1);
        Job job3 = new Job(3, 9, 5);
        Job job4 = new Job(4, 6, 3);
        Job job5 = new Job(5, 5, 3);
        scheduleJobs(new Job[]{job1, job2, job3, job4, job5});
    }

    public static void testJobs() {
        Job job1 = new Job(1, 7);
        Job job2 = new Job(2, 13);
        Job job3 = new Job(3, 11);
        Job job4 = new Job(4, 19);
        Job job5 = new Job(5, 16);


        scheduleJobs(new Job[]{job1, job2, job3, job4, job5});
    }
}