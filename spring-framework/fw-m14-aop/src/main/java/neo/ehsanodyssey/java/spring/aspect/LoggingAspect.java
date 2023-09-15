package neo.ehsanodyssey.java.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    // The basic syntax of a pointcut is shown, here: designator("r p.c.m(arg)")
    //      1) r: is return type
    //      2) p: is package
    //      3) c: is class
    //      4) m: is method
    //      5) arg: is args
    // You then need to specify the package, class and method. Each of these values can be replaced with wildcards
    // to select groups of joinpoints. This, however, is based on the selection of the designator. Finally, you can
    // specify zero or more arguments, again, through the use of wildcards.
    // When writing pointcuts in advice, there are several designators that can be leveraged. Each use-case is
    // different and you should look at the Spring documentation or the AspectJ documentation to help you to make
    // your choice. Keep in mind, however, that Spring does not support the full AspectJ offerings, but instead only
    // a subset of them.
    // I am listing the most common ones from my experience.
    // Common Designators:
    //      execution: expression for matching method execution
    //      within: expressions for matching within certain types
    //      target: expressions for matching a specific type
    //      @annotation: expressions for matching a specific annotation
    @Pointcut("@annotation(Loggable)")
    public void executeLogging() {
    }

    // When using Before Advice, your advice is executed prior to the JoinPoint that is selected by the PointCut.
    // This is a great opportunity for call stack metrics, security audits, and other behavior that is not impacted
    // by the outcome of the call, only the fact that a method was actually called. We will start our discussion
    // with the advice first.
    @Before("executeLogging()")
    public void logMethodCallBefore(JoinPoint joinPoint) {
        StringBuilder message = new StringBuilder("Before Method: ");
        message.append(joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        if (null != args && args.length > 0) {
            message.append(" args=[");
            Arrays.asList(args).forEach(arg -> {
                message.append("arg=").append(arg).append("|");
            });
        }
        LOGGER.info(message.toString());
    }

    // When using After Advice, your advice is executed after the join point that is selected by the point cut.
    // As there are two possible outcomes of a method call, you might think that there are two possible ways to use
    // the after, but in reality, there's three. And it'll all make sense here shortly. The first is after returning,
    // which is the call executes successfully and returns normally. The second is after throwing, which indicates
    // that the call executed and threw an exception. The final and the one that I use most is the After Advice, also
    // known as the After Finally, which is executed regardless of the flow of the application. Each of these has
    // their place, but the key here is when you need to do work, after a method is done, but not necessarily before,
    // one of these three after advices is what you would use.
    @AfterReturning(pointcut = "executeLogging()", returning = "returnValue")
    public void logMethodCallAfter(JoinPoint joinPoint, Object returnValue) {
        StringBuilder message = new StringBuilder("After Method: ");
        message.append(joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        if (null != args && args.length > 0) {
            message.append(" args=[");
            Arrays.asList(args).forEach(arg -> {
                message.append("arg=").append(arg).append("|");
            });
        }
        if (returnValue instanceof Collection) {
            message.append(" | returning ").append(((Collection) returnValue).size()).append(" instance(s)");
        } else {
            message.append(" | returning ").append(returnValue.toString());
        }
        LOGGER.info(message.toString());
    }

    // When using Around Advice, you have some of the most powerful types of aspecting you can do in my opinion.
    // The Around Advice allows you to perform part of your advice prior to the execution of the joined point,
    // selected by the point cut, and then return to the advice after the joint point completes and execute the rest
    // of the advice. As always, with great power comes great responsibility. And you have to control all code flows
    // in your advice itself. If you encounter an exception, you have to ensure that your advice operates as expected,
    // and then throws the exception back out or transforms it in some way. In a happy path flow, you have to return
    // the value from the advice that you receive from the join point.
    // This Around Advice gives us a lot of power because now we can do work before and after. This example may seem
    // trivial to some of you, but as you move into Cloud native computing, you can find a lot of power in such a
    // logging aspect.
    // You could create a before advice that creates a tracer and apply that to your controller methods.
    // You can then build an Around Advice and create structured output that a log aggregator or build searchable
    // indexes off of that to include timers.
    // So this trivial example is a real-world workhorse that will help in unifying logging across your enterprise.
    // But this is just the tip of the iceberg. You can use aspecting to log data access by user in systems where
    // this must be audited, like health-care systems. You could log protected APIs, you could modify caches through
    // aspecting, and really you could handle any global, cross-cutting system or business requirement in one place
    // when using aspect oriented programming.
    @Around("executeLogging()")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object returnValue = joinPoint.proceed(); // the join point has an unhandled exception.
        long totalTime = System.currentTimeMillis() - startTime;
        StringBuilder message = new StringBuilder("Around Method: ");
        message.append(joinPoint.getSignature().getName());
        message.append(" totalTime: ").append(totalTime).append("ms ");
        Object[] args = joinPoint.getArgs();
        if (null != args && args.length > 0) {
            message.append(" args=[");
            Arrays.asList(args).forEach(arg -> {
                message.append("arg=").append(arg).append("|");
            });
        }
        if (returnValue instanceof Collection) {
            message.append(" | returning ").append(((Collection) returnValue).size()).append(" instance(s)");
        } else {
            message.append(" | returning ").append(returnValue.toString());
        }
        LOGGER.info(message.toString());
        return returnValue;
    }
}
