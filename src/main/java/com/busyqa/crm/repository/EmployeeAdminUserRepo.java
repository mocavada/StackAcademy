package com.busyqa.crm.repository;

import com.busyqa.crm.pojo.employee.EmployeeAdminUser;
import com.busyqa.crm.pojo.employee.EmployeeRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeAdminUserRepo extends JpaRepository<EmployeeAdminUser, Long> {
    EmployeeAdminUser findFirst1ByUserNameAndPasswordAndRole(String userName, String password, EmployeeRole role);
    List<EmployeeAdminUser> findByRole(EmployeeRole role);

//  BELOW METHODS ARE ONLY USED IN TEST  /

    EmployeeAdminUser findFirstByRole(EmployeeRole role);
    EmployeeAdminUser findFirst1ByRole(EmployeeRole role);


}
