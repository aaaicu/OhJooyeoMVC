package kr.co.ohjooyeo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect {
	static final Logger logger = LoggerFactory.getLogger(TestAspect.class);
	
	@Pointcut("execution(* kr.co.ohjooyeo..controller.*.*(..))")
	private void testPointcut() {}
	
	@Around("testPointcut()")
	public Object checkTime(ProceedingJoinPoint pjp) throws Throwable {
		long startTime = System.nanoTime();
		Object retVal = pjp.proceed();
		
		long endTime = System.nanoTime();
		logger.debug("time : {}", endTime - startTime);
		
		return retVal;
	}
}
