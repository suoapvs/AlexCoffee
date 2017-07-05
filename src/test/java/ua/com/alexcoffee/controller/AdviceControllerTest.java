package ua.com.alexcoffee.controller;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import ua.com.alexcoffee.controller.advice.AdviceController;
import ua.com.alexcoffee.exception.DuplicateException;
import ua.com.alexcoffee.tools.MockController;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ua.com.alexcoffee.tools.ModelAndViews.checkModelAndViewWithException;

public class AdviceControllerTest {

    private static AdviceController adviceController;

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"SEOController\" - START.\n");
        adviceController = MockController.getAdviceController();
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"SEOController\" - FINISH.");
    }

    @Test
    public void noHandlerFoundExceptionTest() throws Exception {
        System.out.print("-> noHandlerFoundException() - ");

        NoHandlerFoundException ex = (NoHandlerFoundException) exceptionMock(NoHandlerFoundException.class);
        HttpServletRequest request = requestMock();

        ModelAndView modelAndView = adviceController.noHandlerFoundException(ex, request);
        checkModelAndViewWithException(modelAndView);

        System.out.println("OK!");
    }

    @Test
    public void nullPointerException() throws Exception {
        System.out.print("-> nullPointerException() - ");

        NullPointerException ex = new NullPointerException();
        HttpServletRequest request = requestMock();

        ModelAndView modelAndView = adviceController.nullPointerException(ex, request);
        checkModelAndViewWithException(modelAndView);

        System.out.println("OK!");
    }

    @Test
    public void illegalArgumentExceptionTest() throws Exception {
        System.out.print("-> illegalArgumentException() - ");

        IllegalArgumentException ex = new IllegalArgumentException();
        HttpServletRequest request = requestMock();

        ModelAndView modelAndView = adviceController.illegalArgumentException(ex, request);
        checkModelAndViewWithException(modelAndView);

        System.out.println("OK!");
    }

    @Test
    public void illegalMappingExceptionTest() throws Exception {
        System.out.print("-> illegalMappingException() - ");

        IllegalMappingException ex = new IllegalMappingException("");
        HttpServletRequest request = requestMock();

        ModelAndView modelAndView = adviceController.illegalMappingException(ex, request);
        checkModelAndViewWithException(modelAndView);

        System.out.println("OK!");
    }

    @Test
    public void duplicateExceptionTest() throws Exception {
        System.out.print("-> duplicateException() - ");

        DuplicateException ex = (DuplicateException) exceptionMock(DuplicateException.class);
        HttpServletRequest request = requestMock();

        ModelAndView modelAndView = adviceController.duplicateException(ex, request);
        checkModelAndViewWithException(modelAndView);

        System.out.println("OK!");
    }

    @Test
    public void otherExceptionTest() throws Exception {
        System.out.print("-> otherException() - ");

        Exception ex = exceptionMock(Exception.class);
        HttpServletRequest request = requestMock();

        ModelAndView modelAndView = adviceController.otherException(ex, request);
        checkModelAndViewWithException(modelAndView);

        System.out.println("OK!");
    }

    @Ignore
    private static Exception exceptionMock(Class<? extends Exception> exp) {
        Exception ex = mock(exp);
        when(ex.getMessage()).thenReturn(exp.getName() + " MESSAGE");
        return ex;
    }

    @Ignore
    private static HttpServletRequest requestMock() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRemoteAddr()).thenReturn("RemoteAddr");
        when(request.getRequestURL()).thenReturn(new StringBuffer("URL"));
        return request;
    }
}
