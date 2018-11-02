package events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import ch.qos.logback.classic.Logger;


public class BaseEventHandler<E> extends AbstractRepositoryEventListener<E> {

	@Autowired
	private PlatformTransactionManager transactionManager;
	public static ThreadLocal<TransactionStatus> threadLocalValue = new ThreadLocal<>();
    

	@Override
	protected void onBeforeCreate(E entity) {
		before("save");
		super.onBeforeCreate(entity);
		
	}

	@Override
	protected void onAfterCreate(E entity) {
		after("save");
		super.onAfterCreate(entity);
		
	}

	@Override
	protected void onBeforeDelete(E entity) {
		before("save");
		super.onBeforeDelete(entity);
	}

	@Override
	protected void onAfterDelete(E entity) {

		after("save");
		super.onAfterDelete(entity);
	}

	@Override
	protected void onBeforeSave(E entity) {
		before("save");
		
		super.onBeforeSave(entity);
	}

	private void before(String type) {
		System.out.println("before "+type+" using "+transactionManager.getClass().getName()+" of "+transactionManager);
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		threadLocalValue.set(status);
	}

	@Override
	protected void onAfterSave(E entity) {
		after("save");
		super.onAfterSave(entity);
		
		
	}

	private void after(String type) {
		System.out.println("after "+type+" using "+transactionManager.getClass().getName()+" of "+transactionManager);
		TransactionStatus currentTransactionStatus = threadLocalValue.get();
		if(!currentTransactionStatus.isCompleted())
		{
			if(!currentTransactionStatus.isRollbackOnly())
			{
				transactionManager.commit(currentTransactionStatus);	
			}
			else
			{
				transactionManager.rollback(currentTransactionStatus);
			}
		}
	}
	
	
}
