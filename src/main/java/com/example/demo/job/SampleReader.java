package com.example.demo.job;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import com.example.demo.model.Student;

@Component
public class SampleReader implements ItemReader<Student> {

	AtomicInteger ai = new AtomicInteger();

	@Override
	public Student read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		ai.getAndIncrement();
		if (ai.intValue() == 10) {
			throw new Exception();
		}
		Thread.sleep(1000);
		return new Student("サンプル太郎");
	}
}
