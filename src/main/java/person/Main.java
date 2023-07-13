package person;

public class Main {
    public static void main(String[] args) {
        Woman woman = new Woman("Inna","Rud",25, true );
        Person man = new Man("Alex","Brown", 30,true);
        System.out.println(woman.isRetired());
        System.out.println(man.isRetired());

        woman.registerPartner("Brown");
        System.out.println(woman.toString());
        woman.deregisterPartnership(true);
        System.out.println(woman.toString());

    }
}