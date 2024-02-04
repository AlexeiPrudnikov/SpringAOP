package ru.geekbrains.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.geekbrains.aop.model.*;
@RequiredArgsConstructor
@Slf4j
@Component
public class AspectRunner {
    private final MathExec mathExec;
    private final ExceptionExec exceptionExec;
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        try {
            mathExec.method1();
            mathExec.method2();
            mathExec.method3();
            System.out.println(exceptionExec.method1());
            System.out.println(exceptionExec.method2());
        }
        catch (Exception ex){
            log.atError().log(ex.getMessage());
        }

    }
}
