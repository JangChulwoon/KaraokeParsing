package org.karaoke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.karaoke")
public class KaraokeApplication {
	public static void main(String[] args) {
		SpringApplication.run(KaraokeApplication.class, args);
	}
}
