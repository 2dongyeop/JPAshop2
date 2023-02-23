package jpabook2.jpashop2.service;

import jpabook2.jpashop2.domain.item.Book;
import jpabook2.jpashop2.domain.item.Item;
import jpabook2.jpashop2.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {
    
    @Autowired ItemService itemService;
    @Autowired ItemRepository itemRepository;
    
    @Test
    public void 상품저장() throws Exception {
        //given -- 조건
        Item item = new Book();
        item.setName("testItem");

        //when -- 동작
        Long itemId = itemService.saveItem(item);
        Item findItem = itemRepository.findOne(item.getId());

        //then -- 검증
//        assertEquals(itemId, findItem.getId());
        Assertions.assertThat(itemId).isEqualTo(findItem.getId());
    }
}