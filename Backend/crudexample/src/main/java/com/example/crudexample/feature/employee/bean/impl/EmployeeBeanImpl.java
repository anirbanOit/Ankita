package com.example.crudexample.feature.employee.bean.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crudexample.api.message.response.AbstractResponse;
import com.example.crudexample.dto.EmployeeDTO;
import com.example.crudexample.dto.StatusDTO;
import com.example.crudexample.feature.employee.api.message.response.AddUserResponse;
import com.example.crudexample.feature.employee.api.message.response.AllUsersDetailResponse;
import com.example.crudexample.feature.employee.api.message.response.DeleteUserResponse;
import com.example.crudexample.feature.employee.api.message.response.GetUserResponse;
import com.example.crudexample.feature.employee.bean.EmployeeBean;
import com.example.crudexample.handler.EmployeeHandler;
import com.example.crudexample.handler.RequestValidator;
import com.example.crudexample.utils.APIGroupCode;
import com.example.crudexample.utils.AppUtil;
import com.example.crudexample.utils.log.AppLog;

@Service("userBean")
public class EmployeeBeanImpl implements EmployeeBean {

	@Autowired(required = true)
	private EmployeeHandler employeeHandler;

	@Autowired(required = true)
	private RequestValidator requestValidator;

	private final AppLog appLog;

	@Inject
	public EmployeeBeanImpl() {
		appLog = AppLog.getInstance(getClass());
	}

	@Override
	public AbstractResponse processGetAllUserProfile(String accessorIdStr, String sessionToken) {
		
		final String errorCodePrefix = APIGroupCode.EMPLOYEE_GET_ALL_USERS.getGroupCode()
				+ APIGroupCode.EMPLOYEE_GET_ALL_USERS.getApiCode() + "000";

		String beanServiceName = "processGetAllUserProfile";

		appLog.printLog(Level.DEBUG, beanServiceName, " Accessor ID:" + accessorIdStr);

		// Validating Permissions & Session Token
		requestValidator.validateAuthToken(sessionToken, beanServiceName, errorCodePrefix);
		requestValidator.validateEMRId(accessorIdStr, errorCodePrefix);

		List<EmployeeDTO> employeeDTOs = employeeHandler.performGetUsersProfileDetail(errorCodePrefix);

		AllUsersDetailResponse getAllUsersDetailResponse = new AllUsersDetailResponse();
		appLog.printLog(Level.DEBUG, beanServiceName, AppUtil.convertObjectToJson(getAllUsersDetailResponse));
		getAllUsersDetailResponse.setEmployeeDTO(employeeDTOs);

		return getAllUsersDetailResponse;
	}

	@Override
	public AbstractResponse processAddUser(String sessionToken, EmployeeDTO employeeDTO) {
		final String errorCodePrefix = APIGroupCode.EMPLOYEE_ADD_USER.getGroupCode()
				+ APIGroupCode.EMPLOYEE_ADD_USER.getApiCode() + "000";

		String beanServiceName = "processGetAllUserProfile";
		StatusDTO statusDTO= employeeHandler.performAddUser(errorCodePrefix, employeeDTO);

		AddUserResponse addUserResponse = new AddUserResponse();
		appLog.printLog(Level.DEBUG, beanServiceName, AppUtil.convertObjectToJson(addUserResponse));
		addUserResponse.setStatusDTO(statusDTO);

		return addUserResponse;
	}
	
	@Override
	public AbstractResponse processGetUser(String accessorIdStr,
			String sessionToken, String id) {
		final String errorCodePrefix = APIGroupCode.EMPLOYEE_GET_USER.getGroupCode()
				+ APIGroupCode.EMPLOYEE_GET_USER.getApiCode() + "000";
		
		String beanServiceName = "processGetUser";

		appLog.printLog(Level.DEBUG, beanServiceName, " Accessor ID:" + accessorIdStr);
		
		// Validating Permissions & Session Token
		requestValidator.validateAuthToken(sessionToken, beanServiceName, errorCodePrefix);
		requestValidator.validateEMRId(accessorIdStr, errorCodePrefix);

		EmployeeDTO employeeDTOs = employeeHandler.performGetUser(errorCodePrefix, id);

		GetUserResponse getUserResponse = new GetUserResponse();
		appLog.printLog(Level.DEBUG, beanServiceName, AppUtil.convertObjectToJson(getUserResponse));
		getUserResponse.setEmployeeDTO(employeeDTOs);

		return getUserResponse;
	}

	@Override
	public AbstractResponse processDeleteUser(String sessionToken,
			String accessorIdStr, String id) {
		final String errorCodePrefix = APIGroupCode.EMPLOYEE_DELETE_USER.getGroupCode()
				+ APIGroupCode.EMPLOYEE_DELETE_USER.getApiCode() + "000";

		String beanServiceName = "processDeleteUser";

		appLog.printLog(Level.DEBUG, beanServiceName, " Accessor ID:" + accessorIdStr);
		
		// Validating Permissions & Session Token
		requestValidator.validateAuthToken(sessionToken, beanServiceName, errorCodePrefix);
		
		StatusDTO statusDTO= employeeHandler.performDeleteUser(errorCodePrefix, id);

		DeleteUserResponse deleteUserResponse = new DeleteUserResponse();
		appLog.printLog(Level.DEBUG, beanServiceName, AppUtil.convertObjectToJson(deleteUserResponse));
		deleteUserResponse.setStatusDTO(statusDTO);

		return deleteUserResponse;
	}

}
