package com.example.demo;

import com.example.demo.persistence.model.Book;
import com.example.demo.persistence.repo.BookRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.example.demo.persistence.repo")
@EntityScan("com.example.demo.persistence.model")
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private static Logger log= LoggerFactory.getLogger(DemoApplication.class);
	public static void main(String[] args) {
		log.info("STARTING THE APPLICATION");
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

	}

	@Bean
	public CommandLineRunner loadData(BookRepository bookRepository){
		return (args)->{
			bookRepository.save(new Book("title", "author"));
			bookRepository.save(new Book("title1", "author1"));
			bookRepository.save(new Book("title2", "author2"));
			// fetch all books
			log.info("books found with findAll():");
			log.info("-------------------------------");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
			log.info("");
		};
	}
}
