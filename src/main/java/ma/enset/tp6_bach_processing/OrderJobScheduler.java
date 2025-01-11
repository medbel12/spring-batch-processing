package ma.enset.tp6_bach_processing;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderJobScheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job processProductJob;

    @Scheduled(cron = "0 0 20 * * *") // Schedule the job to run every day at 8:00 PM
    public void runJob() {
        try {
            JobParameters params = new JobParametersBuilder()
                    .addLong("startTime", System.currentTimeMillis())
                    .toJobParameters();

            jobLauncher.run(processProductJob, params);

            System.out.println("Job exécuté avec succès.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

