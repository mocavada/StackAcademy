package com.busyqa.crm;

import com.busyqa.crm.pojo.employee.EmployeeAdminLoginStatus;
import com.busyqa.crm.pojo.employee.EmployeeAdminUser;
import com.busyqa.crm.pojo.employee.EmployeeRole;
import com.busyqa.crm.repo.EmployeeAdminUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.xml.bind.SchemaOutputResolver;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeAdminUserRepo employeeAdminUserRepo;

    public EmployeeService(EmployeeAdminUserRepo employeeAdminUserRepo) {
        this.employeeAdminUserRepo = employeeAdminUserRepo;

        if(this.employeeAdminUserRepo != null && !this.employeeAdminUserRepo.findAll().iterator().hasNext()) {

            this.employeeAdminUserRepo.saveAll(new ArrayList<EmployeeAdminUser>() {

                {
                    add(new EmployeeAdminUser("marc","pw123", EmployeeRole.AREA_MANAGER));
                    add(new EmployeeAdminUser("betty","pw123", EmployeeRole.AUDIT));
                    add(new EmployeeAdminUser("joe","pw123", EmployeeRole.AUDIT));
                    add(new EmployeeAdminUser("james","pw123", EmployeeRole.AUDIT));
                    add(new EmployeeAdminUser("jamuna","pw123", EmployeeRole.SYSTEM_ADMIN));

                }
            });
        }
    }




    public void accessPageControl(EmployeeRole role) {

        this.employeeAdminUserRepo.findFirst1ByRole(role);
    }


    /**
     * SHOW ALL USERS
     * @return
     * by Marc Cavada
     */
    public List<EmployeeAdminUser> getSortUsers() {
        List<EmployeeAdminUser> list = new ArrayList<>();
        employeeAdminUserRepo.findAll(new Sort(Sort.Direction.DESC, "userName")).forEach(list::add);

        return list;
    }

    /**
     * SHOW ALL USERS PER ROLE
     * @param role
     * @return
     * by Marc Cavada
     */
    public List<EmployeeAdminUser> showUsersPerRole(EmployeeRole role) {
        return this.employeeAdminUserRepo.findByRole(role);

    }


    /**
     * LOGIN
     * @param employeeAdminUser
     * @param session
     * @return
     * by Marc Cavada
     */
    public EmployeeAdminLoginStatus login(EmployeeAdminUser employeeAdminUser, HttpSession session) {

         EmployeeAdminLoginStatus employeeAdminLoginStatus = new EmployeeAdminLoginStatus();

         if(EmployeeRole.SYSTEM_ADMIN.equals(employeeAdminUser.getRole()) &&
                 session.getAttribute("id") != null &&
         session.getAttribute("login") != null &&
                 (boolean) session.getAttribute("login")) {
             employeeAdminLoginStatus.setLogin(true);
             System.out.println("LOGIN AS SYSTEM ADMIN ALREADY");


         } else {
             System.out.println("NOT LOGIN YET");

             if(employeeAdminUserRepo.findFirst1ByUserNameAndPasswordAndRole
                     (employeeAdminUser.getUserName(), employeeAdminUser.getPassword(), employeeAdminUser.getRole()) != null) {

                     session.setAttribute("id", session.getId());
                     session.setAttribute("login", true);
                     employeeAdminLoginStatus.setLogin(true);
                     System.out.println(" LOGIN");

             } else {
                 session.setAttribute("login", false);
                 employeeAdminLoginStatus.setLogin(false);
                 System.out.println(" LOGIN FAIL: Username & Password Not Match");
             }
         }

        return employeeAdminLoginStatus;

    }

    /**
     * CHECK IF LOGIN
     * @param session
     * @return
     * by Marc Cavada
     */
    public EmployeeAdminLoginStatus checkLoginSessionStatus(HttpSession session) {
        EmployeeAdminLoginStatus employeeAdminLoginStatus = new EmployeeAdminLoginStatus();

        if (session.getAttribute("id") != null && session.getAttribute("login") != null && (boolean) session.getAttribute("login")) {
            employeeAdminLoginStatus.setLogin(true);
        } else {
            employeeAdminLoginStatus.setLogin(false);
        }
        return employeeAdminLoginStatus;

    }
}
