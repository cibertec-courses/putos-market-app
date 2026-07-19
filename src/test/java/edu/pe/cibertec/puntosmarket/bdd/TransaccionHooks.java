package edu.pe.cibertec.puntosmarket.bdd;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TransaccionHooks {

    @Autowired
    private PlatformTransactionManager transactionManager;

    private TransactionStatus transactionStatus;

    @Before
    public void iniciarTrasaccion(){
        transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
    }

    @After
    public void revertirTrasaccion(){
        transactionManager.rollback(transactionStatus);
    }
}
