package com.cg.banking.main;
import com.cg.banking.beans.*;
import java.util.Scanner;

import javax.naming.Context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cg.banking.exceptions.*;
import com.cg.banking.daoservices.*;

import com.cg.banking.services.BankingServices;
import com.cg.banking.services.BankingServicesImpl;

public class MainClass {

	public static void main(String[] args) {
		long accNo,accNoFrom,accNoTo;
		int choice,pinNo;
		float amt;
		ApplicationContext context=new ClassPathXmlApplicationContext("bankingBeans.xml");
		BankingServices services=(BankingServices) context.getBean("bankingServices");
		Account AccountDetails=null;
		Account customer1=null;
		Account customer2=null;
		try {
			customer1=services.openAccount("Savings",2000);
			customer1.setAccountStatus("Active");
		}catch(InvalidAccountTypeException | InvalidAmountException | BankingServicesDownException e) {
			e.printStackTrace();
		}
		try {
			customer2 = services.openAccount("Savings",3000);
			customer2.setAccountStatus("Active");
		}catch(InvalidAccountTypeException | InvalidAmountException | BankingServicesDownException e) {
			e.printStackTrace();
		}
		System.out.println("Account Details: "+customer1);
		System.out.println("Account Details: "+customer2);
		Scanner sc=new Scanner(System.in);
		
		while(true) {
			System.out.println("Enter your choice of service: \n 1.Withdrawal  \n 2.Deposit  \n 3.Transfer fund");
			choice=sc.nextInt();
		switch(choice) {
		case 1:
			{System.out.println("***********************WITHDRAWAL**************************");
		System.out.println("Enter Account Number: ");
		accNo=sc.nextLong();
		System.out.println("Enter Pin NO.");
		pinNo=sc.nextInt();
		System.out.println("Enter amount to withdraw");
		amt=sc.nextFloat();
			try {
				System.out.println(services.withdrawAmount(accNo,amt,pinNo));
			} catch (InsufficientAmountException | AccountNotFoundException | InvalidPinNumberException
					| BankingServicesDownException | AccountBlockedException e) {
				e.printStackTrace();
			}
			try {
				System.out.println("Account Details after withdrawal: "+services.getAccountDetails(accNo));
			} catch (AccountNotFoundException | BankingServicesDownException e) {
					e.printStackTrace();
			}
			break;}
		case 2:{
		System.out.println("-------------------Deposit-----------------");
		System.out.println("Enter account number");
		accNo=sc.nextLong();
		System.out.println("enter amount to deposit");
		amt=sc.nextFloat();
		try {
			System.out.println(services.depositAmount(accNo, amt));
			System.out.println("Deposite successful ");
			System.out.println("Account details after deposit: "+services.getAccountDetails(accNo));
		}catch(AccountNotFoundException | BankingServicesDownException | AccountBlockedException e) {
			e.printStackTrace();
		}
		break;}
		case 3:
		{
		System.out.println();
		System.out.println("***************Fund Transfer***************");
		System.out.println("Enter the account number to withdraw amount");
		accNoFrom = sc.nextLong();
		System.out.println("enter PIN number");
		pinNo=sc.nextInt();
		System.out.println("enter the account number for deposit");
		accNoTo=sc.nextLong();
		System.out.println("enter transfer amount");
		amt=sc.nextFloat();
		try {
			System.out.println("fund transfer status: "+services.fundTransfer(accNoTo, accNoFrom, amt, pinNo));
			System.out.println("Account details after fund transfer: (to) \n"+services.getAccountDetails(accNoTo)+"\n"+"\n(from)\n"
			+services.getAccountDetails(accNoFrom));
		}
		catch(InsufficientAmountException | AccountNotFoundException | InvalidPinNumberException | 
				BankingServicesDownException | AccountBlockedException e) {
			e.printStackTrace();
		}
		sc.close();
}}}}}
