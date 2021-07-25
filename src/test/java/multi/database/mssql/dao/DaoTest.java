package multi.database.mssql.dao;

import lombok.extern.slf4j.Slf4j;
import multi.database.config.MssqlConfig;
import multi.database.config.SecondConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class DaoTest {
    @Autowired
    MssqlRestDao mssqlRestDao;

    @Autowired
    SecondRestDao secondRestDao;

    @Test
    public void selectMssqltList() {
        log.debug("{}", mssqlRestDao.selectList("1"));
    }

    @Test
    public void selectSecondList() {
        log.debug("{}", mssqlRestDao.selectList("1"));
    }

}
