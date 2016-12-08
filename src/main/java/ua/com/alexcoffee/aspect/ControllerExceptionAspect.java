package ua.com.alexcoffee.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Класс реализует сквозную функциональность, а именно перехват исключений.
 * Помечен аннотациями @Aspect - аспект изменяет поведение остального кода, применяя совет в точках соединения.
 * Помечен аннотациями @Component указывает, что клас является компонентом фреймворка Spring.
 *
 * @author Yurii Salimov
 */
@Component
@Aspect
public class ControllerExceptionAspect {
    /**
     * Объект для логирования информации.
     */
    private final static Logger LOGGER = Logger.getLogger(ControllerExceptionAspect.class);

    /**
     * Перехватывает исключения. Метод будет вызываться в случае появления исключительных
     * ситуаций, логирует информацию об исключении.
     *
     * @param exception Объект-исключение наследник класса Exception.
     */
    @AfterThrowing(pointcut = "execution(* ua.com.alexcoffee..controller..*(..))", throwing = "exception")
    public void afterThrowingAdvice(Exception exception) {
        LOGGER.error("EXCEPTION IN METHOD -> " + exception.getClass());
    }
}
