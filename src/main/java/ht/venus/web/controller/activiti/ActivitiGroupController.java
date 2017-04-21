package ht.venus.web.controller.activiti;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

import ht.venus.web.entity.activiti.ActivitiGroupEntity;
import ht.venus.web.page.activiti.ActivitiGroupPage;
import ht.venus.web.service.activiti.ActivitiGroupServiceI;
import ht.venus.web.entity.activiti.ActivitiGroupUserEntity;
/**   
 * @Title: Controller
 * @Description: 工作流程组
 * @author zhangdaihao
 * @date 2017-04-11 00:30:48
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/activitiGroupController")
public class ActivitiGroupController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ActivitiGroupController.class);

	@Autowired
	private ActivitiGroupServiceI activitiGroupService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	
	
	/**
	 * 工作流程组列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("ht/venus/web/activiti/activitiGroupList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(ActivitiGroupEntity activitiGroup,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ActivitiGroupEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, activitiGroup);
		this.activitiGroupService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除工作流程组
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ActivitiGroupEntity activitiGroup, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		activitiGroup = systemService.getEntity(ActivitiGroupEntity.class, activitiGroup.getId());
		message = "删除成功";
		activitiGroupService.delete(activitiGroup);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加工作流程组
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ActivitiGroupEntity activitiGroup,ActivitiGroupPage activitiGroupPage, HttpServletRequest request) {
		String message = null;
		List<ActivitiGroupUserEntity> activitiGroupUserList =  activitiGroupPage.getActivitiGroupUserList();
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(activitiGroup.getId())) {
			message = "更新成功";
			activitiGroupService.updateMain(activitiGroup, activitiGroupUserList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			activitiGroupService.addMain(activitiGroup, activitiGroupUserList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 工作流程组列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ActivitiGroupEntity activitiGroup, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(activitiGroup.getId())) {
			activitiGroup = activitiGroupService.getEntity(ActivitiGroupEntity.class, activitiGroup.getId());
			req.setAttribute("activitiGroupPage", activitiGroup);
		}
		return new ModelAndView("ht/venus/web/activiti/activitiGroup");
	}
	
	
	/**
	 * 加载明细列表[工作流程组和人员关系对应model]
	 * 
	 * @return
	 */
	@RequestMapping(params = "activitiGroupUserList")
	public ModelAndView activitiGroupUserList(ActivitiGroupEntity activitiGroup, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = activitiGroup.getId();
		//===================================================================================
		//查询-工作流程组和人员关系对应model
	    String hql0 = "from ActivitiGroupUserEntity where 1 = 1 AND groupId = ? ";
		try{
		    List<ActivitiGroupUserEntity> activitiGroupUserEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("activitiGroupUserList", activitiGroupUserEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("ht/venus/web/activiti/activitiGroupUserList");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ActivitiGroupEntity> list() {
		List<ActivitiGroupEntity> listActivitiGroups=activitiGroupService.getList(ActivitiGroupEntity.class);
		return listActivitiGroups;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ActivitiGroupEntity task = activitiGroupService.get(ActivitiGroupEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ActivitiGroupEntity activitiGroup, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ActivitiGroupEntity>> failures = validator.validate(activitiGroup);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		activitiGroupService.save(activitiGroup);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = activitiGroup.getId();
		URI uri = uriBuilder.path("/rest/activitiGroupController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ActivitiGroupEntity activitiGroup) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ActivitiGroupEntity>> failures = validator.validate(activitiGroup);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		activitiGroupService.saveOrUpdate(activitiGroup);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		activitiGroupService.deleteEntityById(ActivitiGroupEntity.class, id);
	}
}
