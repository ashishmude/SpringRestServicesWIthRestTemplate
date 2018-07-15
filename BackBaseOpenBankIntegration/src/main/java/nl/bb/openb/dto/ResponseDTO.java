package nl.bb.openb.dto;

import java.util.List;

/**
 * @author Ashish_Mude
 *
 */
public class ResponseDTO{
	private boolean isSuccess;
	private List<ErrorDetailsDTO> errorList;
	private Object resultObject;
	
	
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public List<ErrorDetailsDTO> getErrorList() {
		return errorList;
	}
	public void setErrorList(List<ErrorDetailsDTO> errorList) {
		this.errorList = errorList;
	}
	public Object getResultObject() {
		return resultObject;
	}
	public void setResultObject(Object resultObject) {
		this.resultObject = resultObject;
	}
	
	
}
