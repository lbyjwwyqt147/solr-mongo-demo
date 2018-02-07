package com.solr.solrdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class SolrDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolrDemoApplication.class, args);
	}
}
