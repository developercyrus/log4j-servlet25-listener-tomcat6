package foo.bar.listener;

import java.io.File;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;

/*
 * reference: 
 * http://www.javatastic.com/2007/08/22/initialize-log4j-in-a-web-application
 */
public class Log4InitializerListener implements ServletContextListener {
	private static long DEFAULT_INTERVAL = 300000;

	public void contextInitialized(ServletContextEvent event) {
		String file = event.getServletContext().getInitParameter("log4j-file");
		String interval = event.getServletContext().getInitParameter("lo4j-refresh-interval");
		long delay = DEFAULT_INTERVAL; 

		if (interval != null && !"".equals(interval)) {
			try {
				delay = Long.parseLong(interval);
			} catch (NumberFormatException e) {
				delay = DEFAULT_INTERVAL;
			}
		}

		if (file != null && !"".equals(file)) {
			if (!(new File(file).exists())) {
				throw new IllegalArgumentException("Invalid 'log4j-file' parameter value '" + file + "'.");
			}
			if (file.toLowerCase().endsWith(".xml")) {
				DOMConfigurator.configureAndWatch(file, delay);
			} else {
				PropertyConfigurator.configureAndWatch(file, delay);
			}
		}
	}
	
	public void contextDestroyed(ServletContextEvent event) {		
		LogManager.shutdown();
	}

}