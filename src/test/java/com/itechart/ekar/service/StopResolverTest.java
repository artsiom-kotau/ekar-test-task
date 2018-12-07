package com.itechart.ekar.service;

import com.itechart.ekar.service.countmanager.StopResolver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@TestPropertySource(properties = {"min.value=0", "max.value=100"})
public class StopResolverTest {

    @Autowired
    private StopResolver stopResolver;

    @Value("${min.value}")
    private Integer minValue;

    @Value("${max.value}")
    private Integer maxValue;

    @Test
    public void couldntBeChangedIfLessThanMin() {
        assertFalse(stopResolver.couldBeChanged(Integer.MIN_VALUE));
    }

    @Test
    public void couldntBeChangedIfEqulaMin() {
        assertFalse(stopResolver.couldBeChanged(minValue));
    }

    @Test
    public void couldBeChanged() {
        assertTrue(stopResolver.couldBeChanged((minValue + maxValue) / 2));
    }

    @Test
    public void couldntBeChangedIfMoreThanMax() {
        assertFalse(stopResolver.couldBeChanged(maxValue));
    }

    @Test
    public void couldntBeChangedIfEqulaMax() {
        assertFalse(stopResolver.couldBeChanged(maxValue));
    }

    @TestConfiguration
    public static class StopResolverTestConfig {

        @Bean
        public StopResolver stopResolver() {
            return new StopResolver();
        }

    }

}