package playground.drools;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class StatefulSessionExample {

    public static void main(String[] args) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("my-stateful-ksession");

        Message message = new Message();
        message.setStatus(540);
        kSession.insert(message);
        kSession.fireAllRules();

        System.out.println("status after: " + message.getStatus());
    }
}
