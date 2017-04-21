<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addActivitiGroupUserBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delActivitiGroupUserBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addActivitiGroupUserBtn').bind('click', function(){   
 		 var tr =  $("#add_activitiGroupUser_table_template tr").clone();
	 	 $("#add_activitiGroupUser_table").append(tr);
	 	 resetTrNum('add_activitiGroupUser_table');
    });  
	$('#delActivitiGroupUserBtn').bind('click', function(){   
      	$("#add_activitiGroupUser_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_activitiGroupUser_table'); 
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addActivitiGroupUserBtn" href="#">添加</a> <a id="delActivitiGroupUserBtn" href="#">删除</a> 
</div>
<div style="width: auto;height: 300px;overflow-y:auto;overflow-x:scroll;">
<table border="0" cellpadding="2" cellspacing="0" id="activitiGroupUser_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td align="center" bgcolor="#EEEEEE">操作</td>
				  <td align="left" bgcolor="#EEEEEE">成员ID</td>
	</tr>
	<tbody id="add_activitiGroupUser_table">	
	<c:if test="${fn:length(activitiGroupUserList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
				  <td align="left"><input name="activitiGroupUserList[0].userid" maxlength="36" type="text" style="width:120px;" ></td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(activitiGroupUserList)  > 0 }">
		<c:forEach items="${activitiGroupUserList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
				<input name="activitiGroupUserList[${stuts.index }].id"  value="${poVal.id }" type="hidden" >
				   <td align="left"><input name="activitiGroupUserList[${stuts.index }].userid" maxlength="36" value="${poVal.userid }" type="text" style="width:120px;"></td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
</div>