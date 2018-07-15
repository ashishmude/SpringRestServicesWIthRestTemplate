package nl.bb.openb.controllers.test;

import java.util.ArrayList;
import java.util.List;

import nl.bb.openb.dto.RequestDTO;
import nl.bb.openb.dto.ResponseDTO;
import nl.bb.openb.dto.TransactionDTO;
import nl.bb.openb.mappers.DTOMapper;
import nl.bb.openb.service.TransactionFacade;
import nl.bb.openb.service.TransactionFacadeImpl;
import nl.bb.openb.service.TransactionService;
import nl.bb.openb.service.TransactionServiceImpl;
import nl.openb.pojo.Transactions;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;

@RunWith(MockitoJUnitRunner.class)
public class TransactionControllerTest {
	@Mock
	TransactionService transactionService = new TransactionServiceImpl();
	@InjectMocks
	TransactionService transactionServiceCall = new TransactionServiceImpl();;
	@InjectMocks
	TransactionFacade transactionFacade = new TransactionFacadeImpl();

	@Mock
	RestTemplate restTemplate;
	
	@Test
	public void getAllTransactionTest(){
		Transactions transactions = TestUtils.getObjectFromJsonFile("transactionDetails.json", new TypeReference<Transactions>() {
		});
		
		 Mockito.when(restTemplate.getForObject(
	                Mockito.anyString(),
	                Mockito.anyObject()
	                ))
	                .thenReturn(transactions);
		 
		ResponseDTO responseBean = transactionServiceCall.getAllTransactions();
		
		Assert.assertNotNull(responseBean);
		Assert.assertTrue(responseBean.isSuccess());
		List<TransactionDTO> transactionList = (List<TransactionDTO>) responseBean.getResultObject();
		Assert.assertEquals(3, transactionList.size());
		
	}
	
	@Test
	public void getDetailsForTransactionTypeTest(){
		Transactions transactions = TestUtils.getObjectFromJsonFile("transactionDetails.json", new TypeReference<Transactions>() {
		});
		ResponseDTO responseDetails = new ResponseDTO();
		responseDetails.setSuccess(true);
		responseDetails.setResultObject(DTOMapper.convert(transactions));
		Mockito.when(transactionFacade.getAllTransactions()).thenReturn(responseDetails);
		
		RequestDTO<TransactionDTO> requestBean = new RequestDTO<TransactionDTO>();
		TransactionDTO requestData = new TransactionDTO();
		requestData.setTransactionType("SANDBOX_TAN");
		requestBean.setPayload(requestData);
		
		ResponseDTO responseBean = transactionFacade.getDetailsForTransactionType(requestBean);
		
		Assert.assertNotNull(responseBean);
		Assert.assertTrue(responseBean.isSuccess());
		List<TransactionDTO> transactionList = (List<TransactionDTO>) responseBean.getResultObject();
		Assert.assertEquals(2, transactionList.size());
	}
	
	@Test
	public void getAmountForTransactionType(){
		Transactions transactions = TestUtils.getObjectFromJsonFile("transactionDetails.json", new TypeReference<Transactions>() {
		});
		ResponseDTO responseDetails = new ResponseDTO();
		responseDetails.setSuccess(true);
		responseDetails.setResultObject(DTOMapper.convert(transactions));
		Mockito.when(transactionFacade.getAllTransactions()).thenReturn(responseDetails);
		
		
		RequestDTO<TransactionDTO> requestBean = new RequestDTO<TransactionDTO>();
		TransactionDTO requestData = new TransactionDTO();
		requestData.setTransactionType("SANDBOX_TAN");
		requestBean.setPayload(requestData);
		
		ResponseDTO responseBean = transactionFacade.getAmountForTransactionType(requestBean);
		
		Assert.assertNotNull(responseBean);
		Assert.assertTrue(responseBean.isSuccess());
		Assert.assertEquals(new Double(190.40), responseBean.getResultObject());
	}

	@Test
	public void getAmountForTransactionTypeError(){
		Transactions transactions = TestUtils.getObjectFromJsonFile("transactionDetails.json", new TypeReference<Transactions>() {
		});
		ResponseDTO responseDetails = new ResponseDTO();
		responseDetails.setSuccess(true);
		responseDetails.setResultObject(DTOMapper.convert(transactions));
		Mockito.when(transactionFacade.getAllTransactions()).thenReturn(responseDetails);
		
		
		RequestDTO<TransactionDTO> requestBean = new RequestDTO<TransactionDTO>();
		TransactionDTO requestData = new TransactionDTO();
		requestData.setTransactionType("");
		requestBean.setPayload(requestData);
		
		ResponseDTO responseBean = transactionFacade.getAmountForTransactionType(requestBean);
		
		Assert.assertNotNull(responseBean);
		Assert.assertFalse(responseBean.isSuccess());
		Assert.assertNotNull(responseBean.getErrorList());
		Assert.assertTrue(responseBean.getErrorList().size()>0);
	}
	
}
