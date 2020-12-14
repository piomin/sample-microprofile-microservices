package pl.piomin.services.employee.health;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
@ApplicationScoped
public class LivenessEndpoint implements HealthCheck {
	@Override
	public HealthCheckResponse call() {
		return HealthCheckResponse.up("Server up");
	}
}
