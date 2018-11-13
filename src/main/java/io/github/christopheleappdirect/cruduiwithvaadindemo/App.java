package io.github.christopheleappdirect.cruduiwithvaadindemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner loadData(CustomerRepository repository) {
		return (args) -> {
			repository.save(aCustomer("Buggs", "Bunny", "buggsbunny"));
			repository.save(aCustomer("Daffy", "Duck", "daffudyck"));
			repository.save(aCustomer("Elmer", "Fudd", "elmerfudd"));
			repository.save(aCustomer("Henery", "Hawk", "heneryhawk"));
			repository.save(aCustomer("Lola", "Bunny", "lolabunny"));
			repository.save(aCustomer("Penelope", "Pussycat", "penelopepussycat"));
			repository.save(aCustomer("Tasmanian", "Devil", "tasmaniandevil"));
			repository.save(aCustomer("Wile E.", "Coyote", "wilecoyote"));
			repository.save(aCustomer("Road", "Runner", "roadrunner"));
		};
	}

	private Customer aCustomer(String firstName, String lastName, String password) {
		return Customer.builder()
				.firstName(firstName)
				.lastName(lastName)
				.password(password).build();
	}
}
