package com.qs.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.qs.bean.Flow;
import com.qs.dao.OracleDao;

import java.util.List;

/**
 * @author : qs
 * @description :
 */

@RunWith(SpringRunner.class)
@ContextConfiguration({"classpath:springApplication.xml"})
public class OracleDaoTest {

    @Autowired
    private OracleDao oracleDao;

    @Test
    public void get() {
        List<Flow> flows = oracleDao.queryById(217584603977429772L);
        if (flows != null) {
            for (Flow flow : flows) {
                System.out.println(flow.getId() + " " + flow.getPlugId());
            }
        }
    }
}
