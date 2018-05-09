package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void contextLoads() {
        long count = mongoTemplate.count(new Query() {{
            addCriteria(Criteria.where("id").is("351_2171110000"));
        }}, "XSTORE_BINVOICE_VERIFICATION_REVERSE");
        System.out.println("------------总数:" + count);

        List<Map> maps = mongoTemplate.find(new Query()
                        .addCriteria(Criteria.where("id").is("351_2171110000"))
                , Map.class, "XSTORE_BINVOICE_VERIFICATION_REVERSE");
        System.out.println("-------------");

        maps = mongoTemplate.find(new Query() {{
            addCriteria(Criteria.where("id").is("351_2171110000"));
        }}, Map.class, "XSTORE_BINVOICE_VERIFICATION_REVERSE");
        System.out.println("-------------");

    }

    @Test
    public void test02() {

//        mongoTemplate.save(new XXX() {{
//            setId(2);
//            setName("wenshiwei");
//        }}, "xxtable");
        System.out.println("------------------------------");
        List<Map> mapList = mongoTemplate.find(new Query() {{
            addCriteria(Criteria.where("name").is("abc"));
        }}, Map.class, "xxtable");
        System.out.println(mapList);

    }

}
