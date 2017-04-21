package ht.venus.web.entity.activiti;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 工作流程
 * @author zhangdaihao
 * @date 2017-04-10 22:43:34
 * @version V1.0   
 *
 */
@Entity
@Table(name = "sys_activiti_flow", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ActivitiFlowEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**流程名称*/
	private java.lang.String name;
	/**是否已经发布(0-未发布,1已发布)*/
	private java.lang.Integer state;
	/**所属业务标签*/
	private java.lang.String tag;
	/**备注*/
	private java.lang.String remark;
	/**修改人*/
	private java.lang.String textUpdateUser;
	/**修改人账号*/
	private java.lang.String textUpdateBy;
	/**修改日期*/
	private java.util.Date textUpdateDate;
	/**制订人*/
	private java.lang.String textOrderUser;
	/**制订人账号*/
	private java.lang.String textOrderBy;
	/**制订日期*/
	private java.util.Date textOrderDate;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  id
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程名称
	 */
	@Column(name ="NAME",nullable=false,length=36)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否已经发布(0-未发布,1已发布)
	 */
	@Column(name ="STATE",nullable=true,precision=10,scale=0)
	public java.lang.Integer getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否已经发布(0-未发布,1已发布)
	 */
	public void setState(java.lang.Integer state){
		this.state = state;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属业务标签
	 */
	@Column(name ="TAG",nullable=true,length=36)
	public java.lang.String getTag(){
		return this.tag;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属业务标签
	 */
	public void setTag(java.lang.String tag){
		this.tag = tag;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="REMARK",nullable=true,length=200)
	public java.lang.String getRemark(){
		return this.remark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  修改人
	 */
	@Column(name ="TEXT_UPDATE_USER",nullable=true,length=24)
	public java.lang.String getTextUpdateUser(){
		return this.textUpdateUser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改人
	 */
	public void setTextUpdateUser(java.lang.String textUpdateUser){
		this.textUpdateUser = textUpdateUser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  修改人账号
	 */
	@Column(name ="TEXT_UPDATE_BY",nullable=true,length=36)
	public java.lang.String getTextUpdateBy(){
		return this.textUpdateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改人账号
	 */
	public void setTextUpdateBy(java.lang.String textUpdateBy){
		this.textUpdateBy = textUpdateBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  修改日期
	 */
	@Column(name ="TEXT_UPDATE_DATE",nullable=true)
	public java.util.Date getTextUpdateDate(){
		return this.textUpdateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  修改日期
	 */
	public void setTextUpdateDate(java.util.Date textUpdateDate){
		this.textUpdateDate = textUpdateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  制订人
	 */
	@Column(name ="TEXT_ORDER_USER",nullable=false,length=24)
	public java.lang.String getTextOrderUser(){
		return this.textOrderUser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  制订人
	 */
	public void setTextOrderUser(java.lang.String textOrderUser){
		this.textOrderUser = textOrderUser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  制订人账号
	 */
	@Column(name ="TEXT_ORDER_BY",nullable=false,length=36)
	public java.lang.String getTextOrderBy(){
		return this.textOrderBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  制订人账号
	 */
	public void setTextOrderBy(java.lang.String textOrderBy){
		this.textOrderBy = textOrderBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  制订日期
	 */
	@Column(name ="TEXT_ORDER_DATE",nullable=false)
	public java.util.Date getTextOrderDate(){
		return this.textOrderDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  制订日期
	 */
	public void setTextOrderDate(java.util.Date textOrderDate){
		this.textOrderDate = textOrderDate;
	}
}
