package com.run.dozer;

import com.alibaba.fastjson.JSON;
import com.run.dto.TestAttributeOne;
import com.run.dto.TestAttributeTwo;
import org.dozer.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author : hewei
 * @date : 2017/12/8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext.xml"})
public class DozerTest {
    @Resource
    private Mapper mapper;

    @Test
    public void testDozerTest() {
        TestAttributeOne one = new TestAttributeOne();
        TestAttributeTwo two = new TestAttributeTwo();
        one.setId("1");
        one.setDueTime(new Date());
        one.setInstalment(1);
        two = mapper.map(one,TestAttributeTwo.class);
        System.out.println("one:" + JSON.toJSONString(one));
        System.out.println("two:" + JSON.toJSONString(two));
    }

    @Test
    public void testSout () {
        System.out.println("******************************************");
    }

}
