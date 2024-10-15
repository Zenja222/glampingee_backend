package com.mainor.project21.glampingestonia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class GlampingestoniaApplication {

	public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
		SpringApplication.run(GlampingestoniaApplication.class, args);
	}

}
