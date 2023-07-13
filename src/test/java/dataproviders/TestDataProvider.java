package dataproviders;

import org.testng.annotations.DataProvider;

import static utils.DBReader.getManFromDB;

public class TestDataProvider {
    @DataProvider(name="manFromDb")
    public static Object [][] manFromDb(){
        return getManFromDB().stream()
                .map(man->new Object[]{man.getFirstName(),man.getLastName(),man.getAge(),man.getPartnr()})
                .toArray(Object[][]::new);

    }
}
