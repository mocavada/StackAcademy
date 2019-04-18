package com.busyqa.crm;

import com.busyqa.crm.pojo.employee.EmployeeAdminUser;
import com.busyqa.crm.pojo.employee.EmployeeRole;
import com.busyqa.crm.repo.EmployeeAdminUserRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeTests {

    @Autowired
    private EmployeeAdminUserRepo employeeAdminUserRepo;

    @Autowired
    private EmployeeService employeeService;




    /**
     * Test JpaRepository Methods.
     * By: Marc Cavada
     */
    @Test
    public void testAccessControl() {

        employeeAdminUserRepo.save(new EmployeeAdminUser("auditOne","password", EmployeeRole.AUDIT));
        employeeAdminUserRepo.save(new EmployeeAdminUser("admin","password", EmployeeRole.SYSTEM_ADMIN));
        employeeAdminUserRepo.save(new EmployeeAdminUser("sales","password", EmployeeRole.SALES_TEAM));
        employeeAdminUserRepo.save(new EmployeeAdminUser("adminTwo","password", EmployeeRole.AUDIT));
        employeeAdminUserRepo.flush();


        System.out.println("Find First By Role "  + employeeAdminUserRepo.findFirstByRole(EmployeeRole.AUDIT));
        System.out.println("Find First 1 By Role "  + employeeAdminUserRepo.findFirst1ByRole(EmployeeRole.AUDIT));

        System.out.println("----- SHOW LIST OF USER BY ROLE -----");

        employeeAdminUserRepo.findByRole(EmployeeRole.AUDIT).forEach(System.out::println);

//        System.out.println("----- SHOW LIST OF ALL USER -----");
//
//        employeeAdminUserRepo.findAllByOrOrderByUserNameAsc().forEach(System.out::println);

    }

}
