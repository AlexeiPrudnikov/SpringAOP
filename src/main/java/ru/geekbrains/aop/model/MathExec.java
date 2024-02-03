package ru.geekbrains.aop.model;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.event.Level;
import org.springframework.stereotype.Component;
import ru.geekbrains.aop.annotation.Timer;

@Slf4j
@Component
//@Timer
public class MathExec {
    //@Timer
    public void method1() {
        long result = 0;
        for (int i = 0; i < 500_000_000; i++) {
            result += i;
        }
        System.out.println(result);
    }
    //@Timer
    public void method2() {
        long result = 0;
        for (int i = 0; i < 1_000_000_000; i++) {
            result += i;
        }
        System.out.println(result);
    }
    @Timer
    public void method3() {
        long result = 0;
        for (int i = 0; i < 2_147_483_647; i++) {
            result += i;
        }
        System.out.println(result);
    }
}
