package com.god.h2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class H2ApplicationTests {

    @Test
    public void contextLoads() {
    }

    public static void main(String[] args) {
        String s = new String("1");
        Long l = null;
        if (s.equals(l.toString())) {
            System.out.println(true);
        }
        System.out.println(s);
        List<Long> list = new ArrayList<>();
        if (list.isEmpty()) {
            System.out.println("123");
        }
    }

}
