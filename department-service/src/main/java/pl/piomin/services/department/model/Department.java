package pl.piomin.services.department.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {
	private Long id;
	private String name;
	private Long organizationId;
	private List<Employee> employees = new ArrayList<>();
}
