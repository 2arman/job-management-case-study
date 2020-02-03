package net.optile.challenge.jobmanagement.util;

import org.springframework.boot.test.context.TestComponent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * @author Arman
 * Date: 2/3/20
 * Time: 12:00 PM
 **/
@Component
public class TransactionalProvider {
    @Transactional
    public <Request, Request2> void doWithTransaction(BiConsumer<Request, Request2> func, Request t, Request2 t2) {
        func.accept(t, t2);
    }
    @Transactional(readOnly = true)
    public <Response> Response doWithTransactionReadOnly(Supplier<Response> func) {
        return func.get();
    }

    @Transactional(readOnly = true)
    public void doWithTransactionReadOnly(Runnable func) {
        func.run();
    }
}
