package io.github.christopheleappdirect.cruduiwithvaadindemo;

import static com.vaadin.flow.data.value.ValueChangeMode.EAGER;
import static org.springframework.util.StringUtils.isEmpty;
import static org.vaadin.crudui.crud.CrudOperation.UPDATE;

import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {

	private final CustomerService service;
	private final GridCrud<CustomerBean> crud;

	public MainView(CustomerService service) {
		this.service = service;

		// Search bar
		TextField filter = new TextField();
		filter.setPlaceholder("Filter by last name");
		filter.setValueChangeMode(EAGER);
		filter.addValueChangeListener(e -> listCustomers(e.getValue()));

		// Main table
		crud = new GridCrud<>(CustomerBean.class);
		crud.getGrid().setPageSize(2);
		// Displayed CustomerBean fields in the main table
		crud.getGrid().setColumns("id", "firstName", "lastName", "password", "enabled");
		// Displayed in a pop-up (any operation)
		crud.getCrudFormFactory().setVisibleProperties("firstName", "lastName", "password", "enabled");
		// Among displayed, disabled in an UPDATE pop-up
		crud.getCrudFormFactory().setDisabledProperties(UPDATE, "firstName", "lastName");
		// Before action, perform JPA annotations
		crud.getCrudFormFactory().setUseBeanValidation(true);
		// Click on a row to show the pop-up
		crud.setClickRowToUpdate(true);
		// Code to run for each operation
		crud.setFindAllOperation(() -> service.findAll());
		crud.setAddOperation(customerBean -> service.save(customerBean));
		crud.setUpdateOperation(customerBean -> service.save(customerBean));
		// Do not display the UPDATE button (we have clickRowOnUpdate instead)
		crud.setUpdateOperationVisible(false);
		// Do not display the DELETE button
		crud.setDeleteOperationVisible(false);

		// Wrap everything up
		VerticalLayout container = new VerticalLayout();
		container.add(filter, crud);
		container.setSizeFull();

		setSizeFull();
		add(container);
	}

	private void listCustomers(String filterText) {
		if (isEmpty(filterText)) {
			crud.getGrid().setItems(service.findAll());
		} else {
			crud.getGrid().setItems(service.findByLastNameContainsIgnoreCase(filterText));
		}
	}
}
