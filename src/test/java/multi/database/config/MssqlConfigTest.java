package multi.database.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MssqlConfigTest {

    @Autowired
    MssqlConfig mssqlConfig;

    @Autowired
    SecondConfig secondConfig;

    @Test
    public void getMssqlDataSource() {
        mssqlConfig.mssqlDataSource();
    }

    @Test
    public void getSecondDataSource() {
        secondConfig.secondDataSource();
    }
}
