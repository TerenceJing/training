package com.terence.itech.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author terence
 * @desc
 * @date 2019/2/19 18:13
 */
@SpringBootApplication(scanBasePackages = {"com.terence.itech"})
public class TrainingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingApplication.class, args);
	}

}

/**
 * @SpringBootApplication 注解是一个复合注解，包含了@ComponentScan扫描注解
 * 
 */
