package ua.com.alexcoffee.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Диспетчер Сервлета, который отвечает за инициализацию Spring MVC и меппинг URL.
 * Класс расширяет класс AbstractAnnotationConfigDispatcherServletInitializer.
 *
 * @author Yurii Salimov
 * @see WebConfig
 * @see RootConfig
 * @see SecurityConfig
 * @see SecurityInitializer
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * Возвращает конфигурацию, в которой инициализируем ViewResolver.
     *
     * @return Массив объектов класса Class - класс с настройками {@link WebConfig}.
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    /**
     * Возвращает конфигурации, которые инициализируют Beans.
     *
     * @return Массив объектов класса Class - класс с настройками {@link RootConfig} и {@link SecurityConfig}.
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class, SecurityConfig.class};
    }

    /**
     * Настроили мэпинг сервлета на "/" и поэтому все запросы будут перехвачены Диспетчером Сервлета Spring.
     *
     * @return Массив типа String.
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * Настройка ссесии.
     *
     * @param servletContext Реализация интерфейса ServletContext.
     * @throws ServletException Исключении выбрасывают методы класса
     *                          AbstractAnnotationConfigDispatcherServletInitializer.
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.addMappingForUrlPatterns(null, true, "/*");
    }

    /**
     * Включение исключений NoHandlerFound.
     *
     * @param context Реализация инерфейса WebApplicationContext.
     * @return Объект класса DispatcherServlet.
     */
    @Override
    protected DispatcherServlet createDispatcherServlet(WebApplicationContext context) {
        final DispatcherServlet dispatcherServlet = (DispatcherServlet) super.createDispatcherServlet(context);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        return dispatcherServlet;
    }
}
