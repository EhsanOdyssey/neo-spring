package neo.ehsanodyssey.java.spring.aspect;

public class AspectOrientedProgramming {
    // Aspects are reusable blocks of code that are injected into your application at runtime. These are very powerful
    // tools for adding behavior to an application. Most of the time, we are writing a simple behavior that applies to
    // many different points of our code base, so-called [cross-cutting] concerns and in using Aspects, we not only
    // reduce the need to replicate that code many times, but we even reduce the need to call into that code.
    // Aspects by their nature are injected into our code at specific points of operation. Some of the most common used
    // cases that I've come into are things like:
    //      Logging
    //      Transaction Management
    //      Caching
    //      Security
    // When thinking about the term cross-cutting concern we need to look at what it really means. The first place
    // that we want to look at is a set of business requirements that have words like every or always. These tend to
    // indicate that we have a simple set of requirements that applies to many different use cases. Another place,
    // and one that I find more often than not can be solved with Aspecting, is system level requirements.
    // If we have a system level requirement that says any time a user logs in, log all of the actions that they
    // perform, or things like every time we execute a database method, we want specific logging written out to our
    // system logs. These are cross-cutting concerns that apply to system requirements and these are great places to
    // solve that concern with an Aspect.
    // Now, I just talked a little bit about that logging routine, but let's talk a little bit more about why that
    // logging routine becomes so important. If we were to write a logging routine that executes every time data is
    // accessed from the database, we would have to copy and paste that essential block of code every single time we
    // need it. That violates the principle of don't repeat yourself. So, using Aspects removes that code duplication.
    // Another problem with using this sort of duplicated code (DRY - Don't Repeat Yourself) is that now we're mixing
    // concerns of our application method. If we have a method whose sole purpose is to go to the database and load
    // the customer from the database, and then we add into it the security concerns and the logging concerns, we're
    // now mixing into our database access method other concerns that don't apply to database access itself.
    // Ultimately replicating our code and mixing these concerns makes our application logic, that business logic that
    // we're trying to solve very complex. By leveraging Aspects, we can maintain our application logic in very concise
    // format, which improves readability as well as maintainability of our code.
    // Now Spring Aspecting leverages [AspectJ] underneath for all of its aspecting needs. This is done through
    // byte code modification, so called run time interweaving our Aspects at run time. Spring Aspects by their nature
    // are dynamic proxy based Aspects, which is why every being that gets injected into an application through
    // the IoC container has a proxy around it.
    // Parts of a Spring Aspect:
    //      Join Point:
    //          A join point is a point in code in the program where execution of an Aspect is targeted towards.
    //          So this is your method, or your line of code or your annotation that the Aspect is going to target.
    //      Pointcut:
    //          The pointcut is the expression that identifies that join point through some sort of regular expression
    //          matching. The pointcut is the expression that is used to identify the selection criteria of
    //          the join point.
    //      Advice:
    //          The advice is the code that you actually execute at a join point that was selected by a pointcut.
    //          So the advice is your cross-cutting concern routine that we are applying to a join point in our
    //          application.
    //      Aspect:
    //          An Aspect is a module that contains all of your pointcuts, as well as all of your advice that is then
    //          injected at the run time of your application.
}
