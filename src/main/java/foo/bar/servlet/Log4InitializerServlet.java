package foo.bar.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class Log4InitializerServlet extends HttpServlet {
	private static final long serialVersionUID = -3916626406794615130L;
	private static Logger logger = Logger.getLogger(Log4InitializerServlet.class);

    @Override
    public void init() {
    	String site = this.getInitParameter("site");		
		String[] siteParams = this.getInitParameter(site).split(",");
		String log4j = this.getInitParameter(siteParams[0]);		
		String key = this.getInitParameter(siteParams[1]);
		
    	PropertyConfigurator.configure(log4j);
    	
    	logger.debug("hello world1");
    }


    @Override
    public void destroy() {
    	LogManager.shutdown();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
    }

}
