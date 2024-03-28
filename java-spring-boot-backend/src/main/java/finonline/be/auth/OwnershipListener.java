package finonline.be.auth;
import org.springframework.beans.factory.aspectj.ConfigurableObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import jakarta.persistence.PostLoad;

@Configuration
public class OwnershipListener implements ConfigurableObject {

    @PostLoad
    public void afterLoad(Object target) throws Exception {
    	if (target == null) {
    		System.out.println("rofl");
    		throw new Exception("Object is null.");
    	}

        if (target instanceof IOwner) {
            String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
           
            if (!((IOwner) target).getOwner().getName().equals(userName)) {
            	System.out.println("lol");
            	throw new Exception("Object does not belong to user making request.");
            }
        }
    }
}