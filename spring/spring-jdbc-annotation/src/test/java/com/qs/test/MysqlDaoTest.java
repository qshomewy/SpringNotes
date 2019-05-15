package com.qs.test;

import com.qs.bean.Relation;
import com.qs.config.DatabaseConfig;
import com.qs.dao.impl.MysqlDao;

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
public class MysqlDaoTest {

    @Autowired
    private MysqlDao mysqlDao;

    @Test
    public void get() {
        List<Relation> relations = mysqlDao.get();
        if (relations != null) {
            for (Relation relation : relations) {
                System.out.println(relation.getId() + " " + relation.getName());
            }
        }
    }
}
