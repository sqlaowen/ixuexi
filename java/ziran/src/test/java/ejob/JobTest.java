package ejob;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by wenshiwei on 2016/12/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
public class JobTest {

    @Test
    public void test01() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        while ((line = reader.readLine()) != null) {
            if ("stop".equalsIgnoreCase(line)) {
                return;
            }
        }
    }
}