package nl.bb.openb.service;

import nl.bb.openb.dto.RequestDTO;
import nl.bb.openb.dto.ResponseDTO;
import nl.bb.openb.dto.TransactionDTO;

import org.springframework.web.bind.annotation.RequestBody;

public interface TransactionFacade {
	public ResponseDTO getDetailsForTransactionType(@RequestBody RequestDTO<TransactionDTO> requestBean);
	public ResponseDTO getAmountForTransactionType(@RequestBody RequestDTO<TransactionDTO> requestBean);
	public ResponseDTO getAllTransactions();
}
