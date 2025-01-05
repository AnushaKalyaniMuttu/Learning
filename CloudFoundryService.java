import io.pivotal.cfenv.core.CfEnv;
import io.pivotal.cfenv.core.Service;
import org.springframework.stereotype.Component;

@Component
public class CloudFoundryService {

    private final CfEnv cfEnv;

    public CloudFoundryService(CfEnv cfEnv) {
        this.cfEnv = cfEnv;
    }

    public void printServiceDetails() {
        // Get a specific service by name
        Service myService = cfEnv.findService("my-database-service");
        System.out.println("Service URI: " + myService.getUri());
        System.out.println("Service Credentials: " + myService.getCredentials());
    }
}


/*The java-cfenv-boot dependency is part of the Cloud Foundry ecosystem, specifically designed to integrate with Spring Boot applications running on Cloud Foundry (CF),
a platform-as-a-service (PaaS). This dependency allows Spring Boot applications to easily access and use environment variables and service bindings provided by Cloud Foundry.

Overview of java-cfenv-boot
Library Name: java-cfenv-boot
Group ID: io.pivotal.cfenv
Purpose: This dependency enables Spring Boot applications to retrieve Cloud Foundry environment variables, service bindings, and other Cloud-specific configurations. */

