package io.github.christopheleappdirect.cruduiwithvaadindemo;

import org.openid4java.consumer.ConsumerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.openid.AxFetchListFactory;
import org.springframework.security.openid.OpenIDAuthenticationToken;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public AxFetchListFactory crudWithVaadinAxFetchListFactory() {
		return new CrudWithVaadinAxFetchListFactory();
	}

	@Bean
	public CrudWithVaadinOpenIDConsumer crudWithVaadinOpenIDConsumer(@Value("${openid.identityUrl}") String identityUrl) throws ConsumerException {
		return new CrudWithVaadinOpenIDConsumer(identityUrl,
		                                        crudWithVaadinAxFetchListFactory());
	}

	@Bean
	public AuthenticationUserDetailsService<OpenIDAuthenticationToken> crudWithVaadinUserDetailsService() {
		return new CrudWithVaadinUserDetailsService();
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
