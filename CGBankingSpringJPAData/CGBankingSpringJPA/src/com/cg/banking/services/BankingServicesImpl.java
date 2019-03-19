package com.cg.banking.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.banking.beans.Account;
import com.cg.banking.beans.Transaction;
import com.cg.banking.exceptions.AccountBlockedException;
import com.cg.banking.exceptions.AccountNotFoundException;
import com.cg.banking.exceptions.BankingServicesDownException;
import com.cg.banking.exceptions.InsufficientAmountException;
import com.cg.banking.exceptions.InvalidAccountTypeException;
import com.cg.banking.exceptions.InvalidAmountException;
import com.cg.banking.exceptions.InvalidPinNumberException;
import com.cg.banking.daoservices.AccountDAO;
import com.cg.banking.daoservices.TransactionDAO;
@Component("bankingServices")
public class BankingServicesImpl implements BankingServices {
	@Autowired
	private AccountDAO customerData;
	@Autowired
	private TransactionDAO transacData;
	
	
	private final static float minBalance=1000;
	private final static int maxInvalidPinAttempts=3;

	@Override
	public Account openAccount(String accountType, float initBalance)
			throws InvalidAmountException, InvalidAccountTypeException, BankingServicesDownException {
		if(initBalance<=minBalance) throw new InvalidAmountException("Low Balance!!");
		Account AccountDetails=new Account(accountType,initBalance);
		AccountDetails=customerData.save(AccountDetails);
		AccountDetails.setPinNumber((int)(Math.random()*10000));
		transacData.save(new Transaction(initBalance, "Deposit", AccountDetails));
		return AccountDetails;
	}
   @Override
	public float depositAmount(long accountNo, float amount)
			throws AccountNotFoundException, BankingServicesDownException,AccountBlockedException {
		Account customers=null;
		customers=getAccountDetails(accountNo);
		if(customers.getAccountStatus().equalsIgnoreCase("Blocked")) throw new AccountBlockedException("Account blocked!");
		else
			customers.setAccountBalance(customers.getAccountBalance()+amount);
		transacData.save(new Transaction(amount, "Deposit", customers));
		//customerData.update(account);
		return customers.getAccountBalance();
	}
  @Override
	public float withdrawAmount(long accountNo, float amount, int pinNumber) 
			throws InsufficientAmountException,
			AccountNotFoundException, InvalidPinNumberException, BankingServicesDownException, 
			AccountBlockedException {
		Account customers=null;
		customers=getAccountDetails(accountNo);
		if(customers.getAccountStatus().equalsIgnoreCase("Blocked"))
			throw new AccountBlockedException("Account Blocked");
		else if(customers.getPinNumber()!=pinNumber) {
			customers.incrPinAttempts();
			System.out.println("Invalid Pin,try again");
			
			if(customers.getPinAttempts()==maxInvalidPinAttempts) {
				customers.setAccountStatus("Blocked!!");
				throw new AccountBlockedException();
			}
			throw new InvalidPinNumberException();
			}
		else if(customers.getPinAttempts()>0 && !(customers.getAccountStatus().equalsIgnoreCase("Blocked!")))
			customers.resetPin();
		else if(customers.getAccountBalance()-amount<=minBalance)
			throw new InsufficientAmountException("BALANCE LOW");
		else
			customers.setAccountBalance(customers.getAccountBalance()-amount);
		transacData.save(new Transaction(amount, "Withdraw", customers));
		return customers.getAccountBalance();
	}
	@Override
	public boolean fundTransfer(long accountNoTo, long accountNoFrom, float transferAmount, int pinNumber)
			throws InsufficientAmountException, AccountNotFoundException, InvalidPinNumberException,
			BankingServicesDownException, AccountBlockedException {
		Account customerTo=null;
		Account customerFrom=null;
		customerTo=getAccountDetails(accountNoTo);
		customerFrom=getAccountDetails(accountNoFrom);
		if(customerFrom.getAccountBalance()-transferAmount<=minBalance)
			throw new InsufficientAmountException("Low balance");
		if(customerFrom.getPinNumber()!=pinNumber)
			throw new InvalidPinNumberException("Invalid Pin, please try again");
		else {
			customerFrom.setAccountBalance(withdrawAmount(customerFrom.getAccountNo(),
					transferAmount,customerFrom.getPinNumber()));
			customerTo.setAccountBalance(depositAmount(customerTo.getAccountNo(),transferAmount));
		}
		return true;
	}
	@Override
	public Account getAccountDetails(long accountNo) throws AccountNotFoundException, BankingServicesDownException {
		Account customers=null;
		customers=customerData.findById(accountNo).orElseThrow(()-> new AccountNotFoundException("ACCOUNT NOT FOUND"));
		if(customers==null)throw new AccountNotFoundException("Account not found");
		return customers;
	}
	@Override
	public List<Account> getAllAccountDetails() throws BankingServicesDownException {
		List<Account> customers=customerData.findAll();
		return customers;
	}
	@Override
	public List<Transaction> getAccountAllTransaction(long accountNo)
			throws BankingServicesDownException, AccountNotFoundException {
		return getAccountAllTransaction(accountNo);
	}
	@Override
	public String getAccountStatus(long accountNo) throws AccountBlockedException {
		Account customer=new Account();
		if(customer.getAccountStatus().equalsIgnoreCase("Blocked! "))
			throw new AccountBlockedException();
		else 
		
		return customer.getAccountStatus();
	}

}
