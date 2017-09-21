package com.example.crudexample.feature.employee.api.controller;

import javax.inject.Inject;
import javax.validation.Valid;
import org.apache.log4j.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.crudexample.api.message.response.AbstractResponse;
import com.example.crudexample.dto.EmployeeDTO;
import com.example.crudexample.feature.employee.bean.EmployeeBean;
import com.example.crudexample.utils.AppUtil;
import com.example.crudexample.utils.constant.AppConstant;
import com.example.crudexample.utils.log.AppLog;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	private final EmployeeBean employeeBean;
	private final AppLog appLog;

	@Inject
	public EmployeeController(final EmployeeBean employeeBean) {
		this.appLog = AppLog.getInstance(EmployeeController.class);
		this.employeeBean = employeeBean;
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> getUser() {
		return ResponseEntity.status(HttpStatus.OK).body("User Information");
	}

	@CrossOrigin
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<AbstractResponse> getAllUser(@Valid @RequestParam("admin_id") final String accessorIdStr,
			@RequestHeader(AppConstant.REQUEST_HEADER_TOKEN) final String sessionToken) {
		String methodName = "getAllUser";

		appLog.printLog(Level.DEBUG, methodName, "Role: " + accessorIdStr);

		AbstractResponse getAllUserResponse = employeeBean.processGetAllUserProfile(accessorIdStr, sessionToken);

		appLog.printLog(Level.DEBUG, methodName, "Response: " + AppUtil.convertObjectToJson(getAllUserResponse));

		return ResponseEntity.status(HttpStatus.OK).body(getAllUserResponse);
	}

	
@CrossOrigin
@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public ResponseEntity<AbstractResponse> addUser(@RequestHeader(AppConstant.REQUEST_HEADER_TOKEN) final String sessionToken , @Valid @RequestBody EmployeeDTO employeeDTO) {
	String methodName = "addUser";

	AbstractResponse addUserResponse = employeeBean.processAddUser(sessionToken, employeeDTO);

	appLog.printLog(Level.DEBUG, methodName, "Response: " + AppUtil.convertObjectToJson(addUserResponse));

	return ResponseEntity.status(HttpStatus.OK).body(addUserResponse);
}

@CrossOrigin
@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public ResponseEntity<AbstractResponse> getUser(@Valid @RequestParam("admin_id") final String accessorIdStr,
		@RequestHeader(AppConstant.REQUEST_HEADER_TOKEN) final String sessionToken, final String id) {
	String methodName = "getUser";

	appLog.printLog(Level.DEBUG, methodName, "Role: " + accessorIdStr);

	AbstractResponse getUserResponse = employeeBean.processGetUser(accessorIdStr, sessionToken, id);

	appLog.printLog(Level.DEBUG, methodName, "Response: " + AppUtil.convertObjectToJson(getUserResponse));

	return ResponseEntity.status(HttpStatus.OK).body(getUserResponse);
}

@CrossOrigin
@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public ResponseEntity<AbstractResponse> deleteUser(@RequestHeader(AppConstant.REQUEST_HEADER_TOKEN) final String sessionToken , @Valid @RequestParam("admin_id") final String accessorIdStr, final String id) {
	String methodName = "deleteUser";

	AbstractResponse addUserResponse = employeeBean.processDeleteUser(sessionToken, accessorIdStr, id);

	appLog.printLog(Level.DEBUG, methodName, "Response: " + AppUtil.convertObjectToJson(addUserResponse));

	return ResponseEntity.status(HttpStatus.OK).body(addUserResponse);
}
}
