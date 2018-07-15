package nl.bb.openb.mappers;

import java.util.ArrayList;
import java.util.List;

import nl.bb.openb.dto.TransactionDTO;
import nl.bb.openb.util.CommonUtils;
import nl.openb.pojo.Details;
import nl.openb.pojo.OtherAccount;
import nl.openb.pojo.ThisAccount;
import nl.openb.pojo.Transaction;
import nl.openb.pojo.Transactions;

public class DTOMapper {
	public static List<TransactionDTO> convert(Transactions transactions){
		List<TransactionDTO> transactionsList = new ArrayList<>();
		if(null !=transactions && CommonUtils.isListNotNullAndNotEmpty(transactions.getTransactions())){
			for(Transaction transaction :transactions.getTransactions()){
				TransactionDTO transactionDTO = new TransactionDTO();
				transactionDTO.setId(transaction.getId());
				mapthisAccountData(transaction.getThisAccount(), transactionDTO);
				mapOtherAccountData(transaction.getOtherAccount(), transactionDTO);
				mapTransactionDetails(transaction.getDetails(), transactionDTO);
				transactionsList.add(transactionDTO);
			}
		}
		return transactionsList;
	}

	private static void mapthisAccountData(ThisAccount thisAccount,
			TransactionDTO transactionDTO) {
		if(null != thisAccount){
			transactionDTO.setAccountId(thisAccount.getId());
		}
	}

	private static void mapOtherAccountData(OtherAccount otherAccount, TransactionDTO transactionDTO) {
		if(null != otherAccount){
			transactionDTO.setCounterpartyAccount(otherAccount.getNumber());
			transactionDTO.setCounterpartyName(null != otherAccount.getHolder() && null!= otherAccount.getHolder().getName()? otherAccount.getHolder().getName() : null);
			transactionDTO.setCounterPartyLogoPath(null != otherAccount.getMetadata() && null!= otherAccount.getMetadata().getImageURL()? otherAccount.getMetadata().getImageURL().toString() : null);
		}
	}
	
	private static void mapTransactionDetails(Details transactionDetails,
			TransactionDTO transactionDTO) {
		if(null != transactionDetails){
			transactionDTO.setInstructedAmount(null != transactionDetails.getValue() && null != transactionDetails.getValue().getAmount() ? transactionDetails.getValue().getAmount() : null);
			transactionDTO.setInstructedCurrency(null != transactionDetails.getValue() && null != transactionDetails.getValue().getCurrency() ? transactionDetails.getValue().getCurrency() : null);
			transactionDTO.setTransactionAmount(null != transactionDetails.getValue() && null != transactionDetails.getValue().getAmount() ? transactionDetails.getValue().getAmount() : null);
			transactionDTO.setTransactionCurrency(null != transactionDetails.getValue() && null != transactionDetails.getValue().getCurrency() ? transactionDetails.getValue().getCurrency() : null);
			transactionDTO.setTransactionType(null != transactionDetails.getType() ? transactionDetails.getType().toString() : null);
			transactionDTO.setDescription(null != transactionDetails.getDescription() ? transactionDetails.getDescription().toString() : null);
		}
	};
}
