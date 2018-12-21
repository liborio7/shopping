package com.lm.shopping.items.persistence.dao;

import com.lm.shopping.items.persistence.mapper.ItemMapper;
import com.lm.shopping.items.persistence.model.Item;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class ItemDao {
    private Logger logger = LoggerFactory.getLogger(ItemDao.class);

    @Inject private SqlSessionFactory sqlSessionFactory;

    //============ load by id ============//

    public Item loadById(Item entity) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            Item result = loadById(entity, sqlSession);
            sqlSession.commit();
            return result;
        }
    }

    Item loadById(Item entity, SqlSession sqlSession) {
        logger.debug("load by id: {}", entity);
        return sqlSession.getMapper(ItemMapper.class).loadById(entity);
    }

    //============ load all ============//

    public List<Item> loadAll() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            List<Item> result = loadAll(sqlSession);
            sqlSession.commit();
            return result;
        }
    }

    List<Item> loadAll(SqlSession sqlSession) {
        logger.debug("load all");
        return sqlSession.getMapper(ItemMapper.class).loadAll();
    }
}
