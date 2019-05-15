package com.qs.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.qs.bean.Relation;
import com.qs.dao.MysqlDao;

import java.util.List;

/**
 * @author : qs
 * @description :
 */

@RunWith(SpringRunner.class)
@ContextConfiguration({"classpath:springApplication.xml"})
public class MysqlDaoTest {

    @Autowired
    private MysqlDao mysqlDao;

    @Test
    public void get() {
        List<Relation> relations = mysqlDao.queryById("691");
        if (relations != null) {
            for (Relation relation : relations) {
                System.out.println(relation.getId() + " " + relation.getName());
            }
        }
    }
}
