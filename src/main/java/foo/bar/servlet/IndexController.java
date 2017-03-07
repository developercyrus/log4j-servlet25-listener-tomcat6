package foo.bar.servlet;

import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;


public class IndexController extends HttpServlet {	
	private static final long serialVersionUID = 1624341795050409539L;
	private static Logger logger = Logger.getLogger(IndexController.class);

	@Override
    public void init() {
    	logger.debug("hello world2");
    }
}