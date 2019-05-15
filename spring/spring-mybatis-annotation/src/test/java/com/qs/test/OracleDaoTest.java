package com.qs.test;

import com.qs.bean.Flow;
import com.qs.config.DatabaseConfig;
import com.qs.dao.OracleDao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 
 * @author : qshomewy
 * @description :
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DatabaseConfig.class})
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
