package org.karaoke;

import org.karaoke.config.CategoryConverter;
import org.karaoke.domain.Category;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@SpringBootApplication
@ComponentScan("org.karaoke")
public class KaraokeApplication {
	public static void main(String[] args) {
		SpringApplication.run(KaraokeApplication.class, args);
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(Category.class, new CategoryConverter());
	}
}
