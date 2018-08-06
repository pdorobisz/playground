package playground.drools;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

public class StatelessSessionExample {

    public static void main(String[] args) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        StatelessKieSession kSession = kContainer.newStatelessKieSession("my-stateless-ksession");

        Message message = new Message();
//        message.setMessage("Hello Good World");
//        message.setStatus(1);
        message.setStatus(540);

        kSession.execute(message);
        System.out.println("status after: " + message.getStatus());
    }
}
