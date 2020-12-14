package pl.piomin.services.department.client;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import pl.piomin.services.department.model.Employee;

@Path("/employees")
@RegisterRestClient(baseUri = "http://employee-service:8080")
@Traced
public interface EmployeeClient {

	@GET
	@Path("/department/{departmentId}")
	@Produces(MediaType.APPLICATION_JSON)
	List<Employee> findByDepartment(@PathParam("departmentId") Long departmentId);
}
