package pl.piomin.services.department.health;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;
import pl.piomin.services.department.model.Department;
import pl.piomin.services.department.repository.DepartmentRepository;

@Readiness
@ApplicationScoped
public class ReadinessEndpoint implements HealthCheck {
	@Inject
	DepartmentRepository repository;

	@Override
	public HealthCheckResponse call() {
		HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("Repository up");
		List<Department> departments = repository.findAll();
		if (repository != null && departments.size() > 0)
			responseBuilder.up();
		else
			responseBuilder.down();
		return responseBuilder.build();
	}
}
