package ru.yandex.practicum.blockerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDiscoveryClient
public class BlockerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlockerServiceApplication.class, args);
	}

}
