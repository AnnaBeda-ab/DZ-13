package person;

public class Woman extends Person {
    private String surnameFirst; //дівоче прізвище

    public Woman(String firstName, String lastName, int age, boolean partner) {
        super(firstName, lastName, age, partner);
    }

    public String getSurnameFirst() {
        return surnameFirst;
    }

    public void setSurnameFirst(String surnameFirst) {
        this.surnameFirst = surnameFirst;
    }

    @Override
    public boolean isRetired() {
        return getAge() > 60;
    }


    public void registerPartner(String lastNameOfHusband) {
        setSurnameFirst(getLastName());
        setLastName(lastNameOfHusband);
    }

    public void deregisterPartnership(boolean ifChange) {
        if (ifChange) {
            setLastName(getSurnameFirst());
        }
    }
}


