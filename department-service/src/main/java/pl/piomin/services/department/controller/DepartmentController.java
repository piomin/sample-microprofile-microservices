package pl.piomin.services.department.controller;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.faulttolerance.exceptions.TimeoutException;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.opentracing.Traced;
import pl.piomin.services.department.client.EmployeeClient;
import pl.piomin.services.department.model.Department;
import pl.piomin.services.department.repository.DepartmentRepository;

@Path("/departments")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Timed
public class DepartmentController {

	@Inject
	DepartmentRepository repository;
	@Inject
	EmployeeClient employeeClient;

	@POST
	public Department add(Department department) {
		return repository.add(department);
	}

	@GET
	@Path("/{id}")
	public Department findById(@PathParam("id") Long id) {
		return repository.findById(id);
	}

	@GET
	public List<Department> findAll() {
		return repository.findAll();
	}

	@GET
	@Path("/organization/{organizationId}")
	public List<Department> findByOrganization(@PathParam("organizationId") Long organizationId) {
		return repository.findByOrganization(organizationId);
	}

	@GET
	@Path("/organization/{organizationId}/with-employees")
	@Timeout(500)
	@Retry(retryOn = TimeoutException.class)
	@Traced(operationName = "findByOrganizationWithEmployees")
	public List<Department> findByOrganizationWithEmployees(@PathParam("organizationId") Long organizationId) {
		List<Department> departments = repository.findByOrganization(organizationId);
		departments.forEach(d -> d.setEmployees(employeeClient.findByDepartment(d.getId())));
		return departments;
	}

}
