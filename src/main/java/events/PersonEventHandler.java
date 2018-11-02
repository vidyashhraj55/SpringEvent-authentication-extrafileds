package events;

import java.util.Date;

import org.springframework.security.core.context.SecurityContextHolder;

public class PersonEventHandler extends BaseEventHandler<Person> {

	
	@Override
	protected void onBeforeSave(Person entity) {
		 String username=SecurityContextHolder.getContext().getAuthentication().getName();
			entity.setModifiedby(username);
		 entity.setModifiedOn(new Date());
		super.onBeforeSave(entity);
		
	}

	@Override
	protected void onBeforeCreate(Person entity) {
		 String username=SecurityContextHolder.getContext().getAuthentication().getName();
		entity.setCreatedby(username);
		entity.setCreatedOn(new Date());
		entity.setModifiedby(username);
		entity.setModifiedOn(new Date());
		super.onBeforeCreate(entity);
	}

	@Override
	protected void onAfterCreate(Person entity) {
		
		super.onAfterCreate(entity);
	}

	@Override
	protected void onBeforeDelete(Person entity) {
		
		super.onBeforeDelete(entity);
	}

	@Override
	protected void onAfterDelete(Person entity) {
		
		super.onAfterDelete(entity);
	}

	@Override
	protected void onAfterSave(Person entity) {
		
		super.onAfterSave(entity);
	}

	

}
