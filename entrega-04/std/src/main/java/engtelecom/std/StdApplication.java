package engtelecom.std;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import engtelecom.std.config.Consumidor;

@SpringBootApplication
@ComponentScan(basePackages = "engtelecom.std")
public class StdApplication {

	public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {
		// Inicia o consumidor em uma thread separada
		new Thread(() -> {
			try {
				Consumidor rabbitConsumidor = new Consumidor();
				rabbitConsumidor.startConsumidor();
			} catch (IOException | InterruptedException | TimeoutException e) {
				e.printStackTrace();
			}
		}).start();
		
		// Inicia o Spring Boot
		SpringApplication.run(StdApplication.class, args);
	}

}
