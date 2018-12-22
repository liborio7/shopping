package com.lm.shopping.common;

import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.io.BufferedReader;
import java.io.IOException;

@Singleton
public class AppSqlSessionFactory extends DefaultSqlSessionFactory {
    private final Logger logger = LoggerFactory.getLogger(AppSqlSessionFactory.class);

    public AppSqlSessionFactory() throws IOException {
        super(new XMLConfigBuilder(Resources.getResourceAsStream("mybatis-config.xml"), null, null).parse());
        initDatabase();
    }

    private void initDatabase() {
        try {
            new ScriptRunner(openSession(true).getConnection()).runScript(new BufferedReader(Resources.getResourceAsReader("migration.sql")));

        } catch (Exception e) {
            logger.error("error while initializing database", e);
        }
    }

}
