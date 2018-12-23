package com.lm.shopping.persistence.dao;

import com.lm.shopping.App;
import org.apache.ibatis.session.SqlSessionFactory;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.junit.Before;

import javax.inject.Inject;

public abstract class BaseDaoTest {

    @Inject SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() {
        ServiceLocatorUtilities.bind(App.configureBinder()).inject(this);
    }
}
