package pl.piomin.services.employee.filter;

import java.util.Random;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Delay
public class AddDelayInterceptor {

	Random r = new Random();

	@AroundInvoke
	public Object call(InvocationContext invocationContext) throws Exception {
		Thread.sleep(r.nextInt(1000));
		System.out.println("Intercept");
		return invocationContext.proceed();
	}

}
