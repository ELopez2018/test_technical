package com.prueba.config;


import com.prueba.components.UserItemProcessor;
import com.prueba.models.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<User> reader() {
        return new FlatFileItemReaderBuilder<User>()
                .name("userItemReader")
                .resource(new ClassPathResource("users.csv"))
                .delimited()
                .names("id", "name", "email")
                .targetType(User.class)
                .build();
    }
    @Bean
    public Job importUserJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                             FlatFileItemReader<User> reader, UserItemProcessor processor, JdbcBatchItemWriter<User> writer) {
        Step step = stepBuilderFactory.get("step1")
                .<User, User>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();

        return jobBuilderFactory.get("importUserJob")
                .start(step)
                .build();
    }
}