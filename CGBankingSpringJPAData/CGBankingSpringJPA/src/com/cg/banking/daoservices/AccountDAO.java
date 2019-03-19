package com.cg.banking.daoservices;
import com.cg.banking.beans.*;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
public interface AccountDAO extends JpaRepository<Account, Long> {
	

}
