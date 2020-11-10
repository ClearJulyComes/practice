package com.example.practice.daotests;

import com.example.practice.daointerface.DocRepository;
import com.example.practice.dao.DocRepositoryImpl;
import com.example.practice.model.Doc;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserInfoRepositoryTest {

    @TestConfiguration
    static class UserInfoRepositoryContextConfiguration {

        @Bean
        public DocRepository docRepository() {
            return new DocRepositoryImpl();
        }
    }

    @Autowired
    private DocRepository docRepository;

    @Test
    public void whenUpdate_returnSuccess(){
        Doc expected = new Doc(7, "Военный билет");
        expected.setId(2);
        Doc actual = docRepository.findByName("Военный билет");
        Assert.assertEquals(expected, actual);
    }
}
