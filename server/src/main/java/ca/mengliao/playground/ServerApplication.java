package ca.mengliao.playground;

import java.net.URISyntaxException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) throws URISyntaxException {
		SpringApplication.run(ServerApplication.class, args);
	}

}
