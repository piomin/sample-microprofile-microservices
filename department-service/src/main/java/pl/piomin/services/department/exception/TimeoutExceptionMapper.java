package pl.piomin.services.department.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.eclipse.microprofile.faulttolerance.exceptions.TimeoutException;

@Provider
public class TimeoutExceptionMapper implements ExceptionMapper<TimeoutException> {

	public Response toResponse(TimeoutException e) {
		return Response.status(Response.Status.GATEWAY_TIMEOUT).build();
	}

}
