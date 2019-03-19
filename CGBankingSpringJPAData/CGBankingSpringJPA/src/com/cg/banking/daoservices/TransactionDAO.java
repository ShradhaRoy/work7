package com.cg.banking.daoservices;
import com.cg.banking.beans.*;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDAO extends JpaRepository<Transaction, Integer>{
	

}
