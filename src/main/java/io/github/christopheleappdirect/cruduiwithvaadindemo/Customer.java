package io.github.christopheleappdirect.cruduiwithvaadindemo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false, length = 128)
	private String firstName;
	@Column(nullable = false, length = 128)
	private String lastName;
	@Column(nullable = false, length = 128)
	private String password;
	@Builder.Default
	@Column(nullable = false)
	@ColumnDefault("'true'")
	private Boolean enabled = true;
}
