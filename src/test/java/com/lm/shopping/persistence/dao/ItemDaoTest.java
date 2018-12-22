package com.lm.shopping.persistence.dao;

import com.lm.shopping.persistence.model.Item;
import com.lm.shopping.persistence.model.ItemBuilder;
import org.apache.commons.lang3.RandomUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static io.github.benas.randombeans.api.EnhancedRandom.randomListOf;
import static org.assertj.core.api.Assertions.assertThat;

public class ItemDaoTest extends BaseDaoTest {

    @Inject private ItemDao dao;

    //============ insert and load by id

    @Test
    public void shouldInsertAndLoadById() {
        // given
        SqlSession session = sqlSessionFactory.openSession();
        Item toInsert = random(Item.class);
        Item inserted = dao.insert(toInsert, session);
        Item toLoad = new ItemBuilder()
                .withId(inserted.getId())
                .build();

        // when
        Item loaded = dao.loadById(toLoad, session);

        // then
        assertThat(loaded).isEqualToIgnoringGivenFields(inserted);
    }

    //============ load all

    @Test
    public void shouldLoadAll() {
        // given
        SqlSession session = sqlSessionFactory.openSession();
        List<Item> toInsert = randomListOf(RandomUtils.nextInt(5, 10), Item.class);
        List<Item> inserted = toInsert.stream().map(a -> dao.insert(a, session)).collect(Collectors.toList());

        // when
        List<Item> loaded = dao.loadAll(session);

        // then
        assertThat(inserted.stream().allMatch(
                insert -> loaded.stream().anyMatch(
                        load -> load.getId().equals(insert.getId()) &&
                                load.getName().equals(insert.getName()) &&
                                load.getCategory().equals(insert.getCategory()) &&
                                load.getPrice().equals(insert.getPrice()) &&
                                load.getImported().equals(insert.getImported())
                ))
        ).isTrue();
    }
}