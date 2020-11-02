/*package com.example.practice.servicetests;

import com.example.practice.service.OfficeServiceImpl;
import com.example.practice.serviceinterface.OfficeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OfficeServiceTests {
    @TestConfiguration
    static class OfficeServiceContextConfiguration {

        @Bean
        public OfficeService officeService() {
            return new OfficeServiceImpl();
        }
    }

    @Test
    public void test(){
        Integer integer = 200;
        integer += 400;
        int i = 600;
        Assert.assertTrue(integer == i);
    }
} */
