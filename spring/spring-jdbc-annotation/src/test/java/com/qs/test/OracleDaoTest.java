package com.qs.test;

import com.qs.bean.Flow;
import com.qs.config.DatabaseConfig;
import com.qs.dao.impl.OracleDao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    /*注入接口时: 如果接口有多个实现类 可以用这个指定具体的实现类*/
    @Qualifier("oracleDaoImpl")
    @Autowired
    private OracleDao oracleDao;

    @Test
    public void get() {
        List<Flow> flows = oracleDao.get();
        if (flows != null) {
            for (Flow flow : flows) {
                System.out.println(flow.getId() + " " + flow.getPlugId());
            }
        }
    }
}
