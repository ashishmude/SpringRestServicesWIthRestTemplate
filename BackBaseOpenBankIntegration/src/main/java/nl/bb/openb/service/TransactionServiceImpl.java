package nl.bb.openb.service;

import java.util.ArrayList;
import java.util.List;

import nl.bb.openb.dto.ErrorDetailsDTO;
import nl.bb.openb.dto.ResponseDTO;
import nl.bb.openb.dto.TransactionDTO;
import nl.bb.openb.mappers.DTOMapper;
import nl.openb.pojo.Transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public class TransactionServiceImpl implements TransactionService {
	@Value("${TRANSACTIONS_URL}")
	private String transactionServiceUrl; 
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public ResponseDTO getAllTransactions() {
		ResponseDTO response = new ResponseDTO();
		try{
			Transactions transactions = restTemplate.getForObject(transactionServiceUrl, Transactions.class);
			handleResponse(response, transactions);
		}catch(Exception exception){
			handleError(response, exception);
		}
		return response;
	}

	private void handleResponse(ResponseDTO response, Transactions transactions) {
		List<TransactionDTO> transactionList = DTOMapper.convert(transactions);
		response.setSuccess(true);
		response.setResultObject(transactionList);
	}
	
	private void handleError(ResponseDTO response,
			Exception exception) {
		response = new ResponseDTO();
		List<ErrorDetailsDTO> errorList = new ArrayList<ErrorDetailsDTO>();
		ErrorDetailsDTO error = new ErrorDetailsDTO();
		error.setErrorMessage(exception.getMessage());
		errorList.add(error);
		response.setSuccess(false);
		response.setErrorList(errorList);
	}
	
}
