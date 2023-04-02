package itsum.study;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class ItsumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItsumApplication.class, args);
	}
}
