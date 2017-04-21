<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="activitiFlowList" title="工作流程" actionUrl="activitiFlowController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="流程名称" field="name"   width="120"></t:dgCol>
   <t:dgCol title="是否已经发布(0-未发布,1已发布)" field="state"   width="120"></t:dgCol>
   <t:dgCol title="所属业务标签" field="tag"   width="120"></t:dgCol>
   <t:dgCol title="备注" field="remark"   width="120"></t:dgCol>
   <t:dgCol title="修改人" field="textUpdateUser"   width="120"></t:dgCol>
   <t:dgCol title="修改人账号" field="textUpdateBy"   width="120"></t:dgCol>
   <t:dgCol title="修改日期" field="textUpdateDate" formatter="yyyy-MM-dd hh:mm:ss"  width="120"></t:dgCol>
   <t:dgCol title="制订人" field="textOrderUser"   width="120"></t:dgCol>
   <t:dgCol title="制订人账号" field="textOrderBy"   width="120"></t:dgCol>
   <t:dgCol title="制订日期" field="textOrderDate" formatter="yyyy-MM-dd hh:mm:ss"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="activitiFlowController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="activitiFlowController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="activitiFlowController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="activitiFlowController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>