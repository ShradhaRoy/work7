package com.cg.banking.test;
/*
 * package com.cg.banking.test;
 * 
 * import java.util.ArrayList; import java.util.HashMap;
 * 
 * import org.junit.AfterClass; import org.junit.Assert; import
 * org.junit.Before; import org.junit.BeforeClass; import org.junit.Test;
 * 
 * import com.cg.bankig.beans.Account; import com.cg.bankig.beans.Transaction;
 * import com.cg.bankig.exceptions.AccountBlockedException; import
 * com.cg.bankig.exceptions.AccountNotFoundException; import
 * com.cg.bankig.exceptions.BankingServicesDownException; import
 * com.cg.banking.services.BankingServices; import
 * com.cg.banking.services.BankingServicesImpl; import
 * com.cg.banking.util.AccountDBUtil;
 * 
 * public class BankingSystemTest { Account account=new Account();
 * 
 * private static BankingServices services;
 * 
 * @BeforeClass public static void setUpTestEnv() { services=new
 * BankingServicesImpl(); }
 * 
 * @Before public void setUpTestData() { Account account1=new
 * Account(100001,201,"Savings","Active",2000,account.transactions); Account
 * account2=new
 * Account(100002,202,"Savings","Active",3000,account.transactions);
 * //AccountDBUtil.customerAccountDetails.put(account1.getAccountNo(),account1);
 * //AccountDBUtil.customerAccountDetails.put(account2.getAccountNo(),account2);
 * //AccountDBUtil.ACCOUNT_NUMBER=102;
 * 
 * 
 * }
 * 
 * @Test(expected=AccountNotFoundException.class) public void
 * testGetAccountDetailsForInvalidAccountId() throws AccountNotFoundException,
 * BankingServicesDownException { services.getAccountDetails(100001); }
 * 
 * @Test(expected=AccountNotFoundException.class) public void
 * testGetAccountDetailsForValidAccountId() throws AccountNotFoundException,
 * BankingServicesDownException { Account expectedAccount = new
 * Account(100001,201,"Savings","Active",2000,account.transactions); Account
 * actualAccount=services.getAccountDetails(101);
 * Assert.assertEquals(expectedAccount, actualAccount); }
 * 
 * @Test(expected=AccountNotFoundException.class) public void
 * testGetAccountDetailsforInvalidAmount() throws AccountNotFoundException,
 * BankingServicesDownException { services.getAccountDetails(100001); }
 * 
 * @Test(expected=AccountNotFoundException.class) public void
 * testAccountStatusForValidAccountNumber() throws AccountNotFoundException,
 * BankingServicesDownException, AccountBlockedException{ String
 * expectedStatus="Active"; String actualStatus=services.accountStatus(5001);
 * Assert.assertEquals(expectedStatus, actualStatus); }
 * 
 * @Test public void testGetAllAccountDetailsforInvalidAmount() throws
 * AccountNotFoundException, BankingServicesDownException { Account account1=new
 * Account( "Savings", "Active",2000); Account account2=new Account("Savings",
 * "Blocked",3000);
 * 
 * ArrayList<Account> expectedAccountList=new ArrayList<Account>();
 * expectedAccountList.add(account1); expectedAccountList.add(account2);
 * ArrayList<Account>
 * actualAccountList=(ArrayList<Account>)services.getAllAccountDetails();
 * Assert.assertEquals(expectedAccountList, actualAccountList); }
 * 
 * @AfterClass public static void tearDownTestEnv() { services=null; } }
 * 
 */