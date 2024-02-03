package ru.geekbrains.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.geekbrains.aop.model.MathExec;
@RequiredArgsConstructor
@Slf4j
@Component
public class AspectRunner {
    private final MathExec mathExec;
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        mathExec.method1();
        mathExec.method2();
        mathExec.method3();
    }
}
