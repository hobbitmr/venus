package ht.venus.core.activiti;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.task.IdentityLink;

public class MyTaskListener implements TaskListener {

	@Override
	public void notify(DelegateTask arg0) {
		// TODO Auto-generated method stub
		
		for(IdentityLink a:arg0.getCandidates()){
			System.out.println(a.getTaskId()+" ===:=== GroupId:"+a.getGroupId());
			System.out.println(a.getTaskId()+" ===:=== UserId:"+a.getUserId());
			System.out.println(a.getTaskId()+" ===:=== Type:"+a.getType());
		}
		
		System.out.println("===>tablebame:"+arg0.getVariable("tablebame"));
		System.out.println("===>param:"+arg0.getVariable("param"));
	}

}
