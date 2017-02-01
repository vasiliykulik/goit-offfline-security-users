package ua.goit.offline.test_security;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by andreymi on 1/31/2017.
 */
public class TApplication implements WebApplicationInitializer {

    private static class InitListener implements ServletContextListener {
        public void contextInitialized(ServletContextEvent sce) {
            System.out.println("We are up!");
        }

        public void contextDestroyed(ServletContextEvent sce) {
            System.out.println("We are down!");
        }
    }

    private static class LogFilter implements Filter {
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            System.out.println("Hello I am filtered@");
            chain.doFilter(request, response);
        }

        public void destroy() {

        }
    }

    public void onStartup(ServletContext servletContext) throws ServletException {
        XmlWebApplicationContext context = new XmlWebApplicationContext();
        context.setConfigLocation("/WEB-INF/application-context.xml");

        ServletRegistration.Dynamic reg = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        reg.setLoadOnStartup(1);
        reg.addMapping("/");

        DelegatingFilterProxy filter = new DelegatingFilterProxy("springSecurityFilterChain");
        filter.setContextAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.dispatcher");
        servletContext.addFilter("springSecurityFilterChain", filter).addMappingForUrlPatterns(null, false, "/*");

        servletContext.addListener(new InitListener());
        servletContext.addFilter("logFilter", new LogFilter()).addMappingForUrlPatterns(null, false, "/*");;
    }
}
