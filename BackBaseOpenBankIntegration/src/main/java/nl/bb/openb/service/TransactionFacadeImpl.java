package nl.bb.openb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import nl.bb.openb.dto.ErrorDetailsDTO;
import nl.bb.openb.dto.RequestDTO;
import nl.bb.openb.dto.ResponseDTO;
import nl.bb.openb.dto.TransactionDTO;
import nl.bb.openb.util.CommonUtils;

import org.springframework.beans.factory.annotation.Autowired;

public class TransactionFacadeImpl implements TransactionFacade {

	@Autowired
	TransactionService transactionService;
	
	@Override
	public ResponseDTO getDetailsForTransactionType(
			RequestDTO<TransactionDTO> requestBean) {
		String requestedPaymentType;
		ResponseDTO responseDTO = null;
		try{
			if(null != requestBean.getPayload() && !CommonUtils.isStringNullOrEmpty(requestBean.getPayload().getTransactionType())){
				requestedPaymentType = requestBean.getPayload().getTransactionType();	
			}else{
				throw new Exception("Bad Request");
			}
			responseDTO = getAllTransactions();
			if(responseDTO.isSuccess()){
				List<TransactionDTO> allTransactions = (List<TransactionDTO>) responseDTO.getResultObject();				
				responseDTO.setResultObject(filterListBasedOnTransactionType(allTransactions, requestedPaymentType));
				responseDTO.setSuccess(true);
			}
		}catch(Exception exception){
			responseDTO = handleError(responseDTO, exception);
		}
		return responseDTO;
	}

	public ResponseDTO getAllTransactions() {
		return transactionService.getAllTransactions();
	}

	private List<TransactionDTO> filterListBasedOnTransactionType(List<TransactionDTO> allTransactions, String requestedPaymentType) {
		return allTransactions.stream()
                .filter(transaction -> requestedPaymentType.equalsIgnoreCase(transaction.getTransactionType()))
                .collect(Collectors.toList());
	}

	@Override
	public ResponseDTO getAmountForTransactionType(
			RequestDTO<TransactionDTO> requestBean) {
		String requestedPaymentType;
		ResponseDTO responseDTO = null;
		try{
			if(null != requestBean.getPayload() && !CommonUtils.isStringNullOrEmpty(requestBean.getPayload().getTransactionType())){
				requestedPaymentType = requestBean.getPayload().getTransactionType();	
			}else{
				throw new Exception("Bad Request");
			}
			responseDTO = getAllTransactions();
			if(responseDTO.isSuccess()){
				List<TransactionDTO> allTransactions = (List<TransactionDTO>) responseDTO.getResultObject();				
				List<TransactionDTO> filteredTransactionsBasedOnType = filterListBasedOnTransactionType(allTransactions, requestedPaymentType);
				responseDTO.setResultObject(getTotalTransactionAmount(filteredTransactionsBasedOnType));
				responseDTO.setSuccess(true);
			}
		}catch(Exception exception){
			responseDTO = handleError(responseDTO, exception);
		}
		return responseDTO;
	}

	private Double getTotalTransactionAmount(
			List<TransactionDTO> filteredTransactionsBasedOnType) {
		
		
		/* to get total difference amount of all transaction e.g. -1 + 1 + 1 = 1
		  Double totalTransactionAmount = filteredTransactionsBasedOnType.stream().filter(transaction-> !CommonUtils.isStringNullOrEmpty(transaction.getTransactionAmount()))
							.mapToDouble(transaction -> Double.parseDouble(transaction.getTransactionAmount())).sum();*/
		
		//to get total amount of all transaction e.g. -1 + 1 + 1 = 3
		Double totalTransactionAmount = filteredTransactionsBasedOnType.stream().filter(transaction-> !CommonUtils.isStringNullOrEmpty(transaction.getTransactionAmount()))
				.mapToDouble(transaction -> Math.abs(Double.parseDouble(transaction.getTransactionAmount()))).sum();
		return totalTransactionAmount;
	}
	
	private ResponseDTO handleError(ResponseDTO response,
			Exception exception) {
		if(null == response){
			response = new ResponseDTO();
		}
		List<ErrorDetailsDTO> errorList = new ArrayList<ErrorDetailsDTO>();
		ErrorDetailsDTO error = new ErrorDetailsDTO();
		error.setErrorMessage(exception.getMessage());
		errorList.add(error);
		response.setSuccess(false);
		response.setErrorList(errorList);
		return response;
	}

}
