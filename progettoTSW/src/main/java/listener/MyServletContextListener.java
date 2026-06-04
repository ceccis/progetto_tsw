package listener;

import connessioneDB.ConnectionPool;
import javax.servlet.*;
import javax.servlet.annotation.WebListener;

//Listener per controllare se la connection pool viene inizializata correttamente

@WebListener
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sevent) {
        try {
            ConnectionPool.init();
            System.out.println("Connection pool inizializzata");
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        
    }
}
