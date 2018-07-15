package nl.bb.openb.controllers;

import nl.bb.openb.dto.RequestDTO;
import nl.bb.openb.dto.ResponseDTO;
import nl.bb.openb.dto.TransactionDTO;
import nl.bb.openb.service.TransactionFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ashish_Mude
 *
 */
@RestController
public class TransactionController {
	@Autowired
	TransactionFacade transactionFacade;
	
	@RequestMapping(value="/getTransactions", method=RequestMethod.GET, produces="application/json")
	public ResponseDTO getAllTransaction(){
		return transactionFacade.getAllTransactions();
	}
	
	@RequestMapping(value="/getDetailsForTransactionType", method=RequestMethod.POST, produces="application/json")
	public ResponseDTO getDetailsForTransactionType(@RequestBody RequestDTO<TransactionDTO> requestBean){
		return transactionFacade.getDetailsForTransactionType(requestBean);
	}
	
	@RequestMapping(value="/getAmountForTransactionType", method=RequestMethod.POST, produces="application/json")
	public ResponseDTO getAmountForTransactionType(@RequestBody RequestDTO<TransactionDTO> requestBean){
		return transactionFacade.getAmountForTransactionType(requestBean);
	}
}
