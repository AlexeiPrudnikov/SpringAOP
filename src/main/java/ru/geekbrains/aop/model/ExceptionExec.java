package ru.geekbrains.aop.model;

import org.springframework.stereotype.Component;
import ru.geekbrains.aop.annotation.RecoverException;

@Component
public class ExceptionExec {
    @RecoverException(noRecoverFor = NullPointerException.class)
    public int method1(){
        return 2 / (2 - 2);
    }
    @RecoverException(noRecoverFor = ArithmeticException.class)
    public boolean method2() {
        int i = 2 / (2 - 2);
        return true;
    }
}
