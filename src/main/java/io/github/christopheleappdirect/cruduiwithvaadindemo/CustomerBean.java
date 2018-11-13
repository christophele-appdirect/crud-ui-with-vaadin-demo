package io.github.christopheleappdirect.cruduiwithvaadindemo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerBean {

	private Long id;
	@NotNull
	@Size(min = 2, max = 128)
	private String firstName;
	@NotNull
	@Size(min = 2, max = 128)
	private String lastName;
	@NotNull
	@Size(min = 8, max = 128)
	private String password;
	@NotNull
	private Boolean enabled;
}
