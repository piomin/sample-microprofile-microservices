package pl.piomin.services.employee.health;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;
import pl.piomin.services.employee.model.Employee;
import pl.piomin.services.employee.repository.EmployeeRepository;

@Readiness
@ApplicationScoped
public class ReadinessEndpoint implements HealthCheck {
	@Inject
	EmployeeRepository repository;

	@Override
	public HealthCheckResponse call() {
		HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("Repository up");
		List<Employee> employees = repository.findAll();
		if (repository != null && employees.size() > 0)
			responseBuilder.up();
		else
			responseBuilder.down();
		return responseBuilder.build();
	}
}
