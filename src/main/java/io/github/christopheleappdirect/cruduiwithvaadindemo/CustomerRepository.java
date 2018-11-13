package io.github.christopheleappdirect.cruduiwithvaadindemo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findByLastNameContainsIgnoreCase(String lastName);
}
