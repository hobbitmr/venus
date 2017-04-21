package ht.venus.web.page.activit;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

import ht.venus.web.entity.activiti.ActivitiGroupUserEntity;

/**   
 * @Title: Entity
 * @Description: 工作流程组
 * @author zhangdaihao
 * @date 2017-04-11 00:27:54
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
public class ActivitiGroupPage implements java.io.Serializable {
	/**保存-工作流程组和人员关系对应model*/
	private List<ActivitiGroupUserEntity> activitiGroupUserList = new ArrayList<ActivitiGroupUserEntity>();
	public List<ActivitiGroupUserEntity> getActivitiGroupUserList() {
		return activitiGroupUserList;
	}
	public void setActivitiGroupUserList(List<ActivitiGroupUserEntity> activitiGroupUserList) {
		this.activitiGroupUserList = activitiGroupUserList;
	}


	/**组ID*/
	private java.lang.String id;
	/**组名称*/
	private java.lang.String name;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  组ID
	 */
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  组ID
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  组名称
	 */
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  组名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
}
