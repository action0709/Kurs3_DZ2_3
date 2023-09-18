package com.SkyPro.Kurs3_DZ2_3.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("/info")
public class InfoController {
    private static final Logger logger = LoggerFactory.getLogger(InfoController.class);
@Value("${server.port}")
    private  int port;
    @GetMapping("get-port")
    public int getPort(){
       return port;
   }
    @GetMapping("/calculate")
public void calculate(){
        long startTime = System.currentTimeMillis();
       Integer reduce=Stream.iterate(1, a -> a + 1)
               .limit(1_000_000)
               .reduce(0, (a, b) -> a + b);
        long timeConsumed = System.currentTimeMillis()-startTime;
        logger.info("время работы: " + timeConsumed);

        startTime = System.currentTimeMillis();
        Integer reduce2= Stream.iterate(1, a -> a + 1)
                .parallel()
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b);
        long timeConsumed1 = System.currentTimeMillis()-startTime;
        logger.info("время работы после оптимизации: " + timeConsumed1);


    }
}
