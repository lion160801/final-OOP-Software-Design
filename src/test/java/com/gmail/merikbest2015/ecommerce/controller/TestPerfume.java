package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.EcommerceApplication;
import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.repository.PerfumeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@ContextConfiguration(classes = {EcommerceApplication.class})
@RunWith(value = SpringRunner.class)
public class TestPerfume {

    @Autowired
    private PerfumeRepository perfumeRepository;

    @Test
    public void testPerfume() {
        Perfume perfume = perfumeRepository.findById(1L).orElse(null);
        System.out.println();
    }
}
