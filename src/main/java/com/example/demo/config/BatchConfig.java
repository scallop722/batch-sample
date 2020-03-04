package com.example.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.job.SampleReader;
import com.example.demo.job.SampleWriter;
import com.example.demo.model.Student;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;


	@Bean
	public Job sampleJob(Step sampleStep) {
		return jobBuilderFactory.get("sampleJob")
				.incrementer(new RunIdIncrementer())
				.start(sampleStep)
				.build();

	}

	@Bean
	public Step sampleStep(SampleReader sampleReader, SampleWriter sampleWriter) {
		return stepBuilderFactory.get("sampleStep")
				.<Student, Student>chunk(1)
				.reader(sampleReader)
				.writer(sampleWriter)
				.build();
	}
}
