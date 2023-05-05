package itsum.study;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ItsumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItsumApplication.class, args);
	}
}
