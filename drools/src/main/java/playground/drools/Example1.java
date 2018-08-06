package playground.drools;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

public class Example1 {
    public static void main(String[] args) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        StatelessKieSession kSession = kContainer.newStatelessKieSession("example1-session");

        Person person = new Person();
        person.setAge(17);
        person.setSalary(100);
        person.setName("Janek");

        kSession.execute(person);
        System.out.println("--------------");
        System.out.printf("%s: %d, %d%n", person.getName(), person.getAge(), person.getSalary());
    }

    public static class Person {
        private int age;
        private String name;
        private int salary;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }
    }
}
