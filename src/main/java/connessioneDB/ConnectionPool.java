package connessioneDB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public class ConnectionPool {

	//é la "variabile" che conterrá un oggetto DataSource, cioé la connection pool che verrá creata dal Container
    private static DataSource dataSource;

    public static void init() throws Exception {
    	//ottengo il contesto iniziale JNDI 
        Context initCtx = new InitialContext();
        
        //faccio lookup per andare a prendere nel contesto, tramite il nome, la risorsa chiamata java:comp/env/jdbc/ProgettoTS (la connection pool)
        // e la metto nella variabile dataSource. Faccio un cast a DataSource perché lookup restituisce un oggetto di tipo
        //Object generico
        dataSource = (DataSource) initCtx.lookup("java:comp/env/jdbc/ProgettoTSW");
    }

    
    //metodo per prendere una connessione dalla pool
    public static Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }
}
