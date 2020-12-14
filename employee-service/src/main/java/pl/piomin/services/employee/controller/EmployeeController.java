package pl.piomin.services.employee.controller;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lombok.SneakyThrows;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.opentracing.Traced;
import pl.piomin.services.employee.filter.Delay;
import pl.piomin.services.employee.model.Employee;
import pl.piomin.services.employee.repository.EmployeeRepository;

@Path("/employees")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Delay
@Timed
public class EmployeeController {

	@Inject
	EmployeeRepository repository;

	@POST
	public Employee add(Employee employee) {
		return repository.add(employee);
	}

	@GET
	@Path("/{id}")
	public Employee findById(@PathParam("id") Long id) {
		return repository.findById(id);
	}

	@GET
	@Traced(operationName = "findAll")
	public List<Employee> findAll() {
		return repository.findAll();
	}

	@GET
	@Path("/department/{departmentId}")
	@Traced(operationName = "findByDepartment")
	public List<Employee> findByDepartment(@PathParam("departmentId") Long departmentId) {
		return repository.findByDepartment(departmentId);
	}

	@GET
	@Path("/organization/{organizationId}")
	@Traced
	public List<Employee> findByOrganization(@PathParam("organizationId") Long organizationId) {
		return repository.findByOrganization(organizationId);
	}

}
