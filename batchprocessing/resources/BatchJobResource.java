package com.siovermedal.batchprocessing.resources;

import com.siovermedal.batchprocessing.models.TransactionRecord;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
public class BatchJobResource {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

  //  @RequestMapping("/csv")
  //  public TransactionRecord startBatchJob() {
  //      return new TransactionRecord();
    //}

    @RequestMapping("/launch")
    public void handle() throws Exception{
        jobLauncher.run(job, new JobParameters());
    }
}




