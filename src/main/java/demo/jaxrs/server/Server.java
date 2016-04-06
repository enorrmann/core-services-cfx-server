package demo.jaxrs.server;

import java.util.ArrayList;
import java.util.List;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.test.configuration.CustomJsonProvider;
import org.test.impl.GenericServiceImpl;
import org.test.impl.PersonaServiceImpl;

public class Server {

    protected Server() throws Exception {
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        CustomJsonProvider jsonProvider = new CustomJsonProvider();
        List providers = new ArrayList();

        providers.add(jsonProvider);
        sf.setProviders(providers);
        List rc = new ArrayList();
        rc.add(GenericServiceImpl.class);
        rc.add(PersonaServiceImpl.class);
        sf.setResourceClasses(rc);
        sf.setResourceProvider(GenericServiceImpl.class, new SingletonResourceProvider(new GenericServiceImpl()));
        sf.setResourceProvider(PersonaServiceImpl.class, new SingletonResourceProvider(new PersonaServiceImpl()));
        sf.setAddress("http://localhost:9000/");

        sf.create();
    }

    public static void main(String args[]) throws Exception {
        new Server();
        System.out.println("Server ready...");

        Thread.sleep(5 * 6000 * 1000);
        System.out.println("Server exiting");
        System.exit(0);
    }
}
