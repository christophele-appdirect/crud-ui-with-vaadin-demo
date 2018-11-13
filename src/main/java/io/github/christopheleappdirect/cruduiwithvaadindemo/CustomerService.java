package io.github.christopheleappdirect.cruduiwithvaadindemo;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	private static final String HIDDEN_PASSWORD_VALUE = "********";

	@Autowired
	private CustomerRepository repository;

	public List<CustomerBean> findAll() {
		return repository.findAll().stream()
				.map(customer -> poorMansMapper(customer))
				.collect(toList());
	}

	public List<CustomerBean> findByLastNameContainsIgnoreCase(String lastName) {
		return repository.findByLastNameContainsIgnoreCase(lastName).stream()
				.map(customer -> poorMansMapper(customer))
				.collect(toList());
	}

	public CustomerBean save(CustomerBean customerBean) {
		Customer customer = new Customer();
		if (!isNull(customerBean.getId())) {
			customer = repository.findById(customerBean.getId())
					.orElseThrow(() -> new EntityNotFoundException());
		}

		if (customerBean.getPassword().equals(HIDDEN_PASSWORD_VALUE)) {
			customerBean.setPassword(customerBean.getPassword());
		}

		BeanUtils.copyProperties(customerBean, customer);

		return poorMansMapper(repository.save(customer));
	}

	private CustomerBean poorMansMapper(Customer e) {
		return CustomerBean.builder()
				.id(e.getId())
				.firstName(e.getFirstName())
				.lastName(e.getLastName())
				.password(HIDDEN_PASSWORD_VALUE)
				.enabled(e.getEnabled())
				.build();
	}
}
