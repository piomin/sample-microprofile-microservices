package pl.piomin.services.department.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import pl.piomin.services.department.model.Department;

@Singleton
public class DepartmentRepository {

	private List<Department> departments = new ArrayList<>();

	public DepartmentRepository() {
		departments.add(new Department(1L, "Test1", 1L, new ArrayList<>()));
		departments.add(new Department(2L, "Test2", 2L, new ArrayList<>()));
		departments.add(new Department(3L, "Test3", 2L, new ArrayList<>()));
	}

	public Department add(Department department) {
		department.setId((long) (departments.size()+1));
		departments.add(department);
		return department;
	}

	public Department findById(Long id) {
		return departments.stream()
				.filter(a -> a.getId().equals(id))
				.findFirst().orElse(null);
	}

	public List<Department> findAll() {
		return departments;
	}

	public List<Department> findByOrganization(Long organizationId) {
		return departments.stream()
				.filter(a -> a.getOrganizationId().equals(organizationId))
				.collect(Collectors.toList());
	}
}
