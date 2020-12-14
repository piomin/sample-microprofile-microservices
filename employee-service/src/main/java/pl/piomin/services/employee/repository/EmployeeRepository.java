package pl.piomin.services.employee.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import pl.piomin.services.employee.model.Employee;

@Singleton
public class EmployeeRepository {

	private List<Employee> employees = new ArrayList<>();

	public EmployeeRepository() {
		employees.add(new Employee(1L, 1L, 1L, "Test1", 30, "developer"));
		employees.add(new Employee(2L, 1L, 2L, "Test2", 20, "developer"));
		employees.add(new Employee(3L, 2L, 3L, "Test3", 40, "developer"));
		employees.add(new Employee(4L, 2L, 3L, "Test4", 55, "developer"));
	}

	public Employee add(Employee employee) {
		employee.setId((long) (employees.size()+1));
		employees.add(employee);
		return employee;
	}

	public Employee findById(Long id) {
		Optional<Employee> employee = employees.stream()
				.filter(a -> a.getId().equals(id))
				.findFirst();
		return employee.orElse(null);
	}

	public List<Employee> findAll() {
		return employees;
	}

	public List<Employee> findByDepartment(Long departmentId) {
		return employees.stream()
				.filter(a -> a.getDepartmentId().equals(departmentId))
				.collect(Collectors.toList());
	}

	public List<Employee> findByOrganization(Long organizationId) {
		return employees.stream()
				.filter(a -> a.getOrganizationId().equals(organizationId))
				.collect(Collectors.toList());
	}

}
