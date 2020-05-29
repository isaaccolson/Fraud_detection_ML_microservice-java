package com.siovermedal.batchprocessing.config;

import com.siovermedal.batchprocessing.models.TransactionRecord;
import com.siovermedal.batchprocessing.processing.TransactionRecordProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<TransactionRecord> reader() {
        return new FlatFileItemReaderBuilder<TransactionRecord>()
                .name("transactionRecordReader")
                .resource(new ClassPathResource("sample-data.csv"))
                .delimited()
                .names(new String[]{"step", "type", "amount","nameOrig","oldbalanceOrg","newbalanceOrig","nameDest","oldbalanceDest","newbalanceDest","isFraud","isFlaggedFraud"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<TransactionRecord>() {{
                    setTargetType(TransactionRecord.class);
                }})
                .build();
    }

    @Bean
    public TransactionRecordProcessor processor() {
        return new TransactionRecordProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<TransactionRecord> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<TransactionRecord>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO transactions (step, type, amount, nameOrig, oldbalanceOrg, newbalanceOrig, nameDest, oldbalanceDest, newbalanceDest, isFraud, isFlaggedFraud) VALUES (:step, :type, :amount, :nameOrig, :oldbalanceOrg, :newbalanceOrig, :nameDest, :oldbalanceDest, :newbalanceDest, :isFraud, :isFlaggedFraud)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job importTransactionJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importTransactionJob")
                .incrementer(new RunIdIncrementer())
                //.preventRestart() //added to prevent restart with remote call
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<TransactionRecord> writer) {

        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.afterPropertiesSet();//all for multithreading

        return stepBuilderFactory.get("step1")
                .<TransactionRecord, TransactionRecord> chunk(500)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .taskExecutor(taskExecutor) //use for multithreading
                .build();
    }

}