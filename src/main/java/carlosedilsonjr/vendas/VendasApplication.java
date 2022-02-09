package carlosedilsonjr.vendas;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VendasApplication {

	
	private String appName = "Testando";

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

	@GetMapping("/hello")
	public String helloWorld(){
		return appName;
	}

	
}
