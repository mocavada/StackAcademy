package com.busyqa.crm;


import com.busyqa.crm.pojo.employee.EmployeeLoginStatus;
import com.busyqa.crm.pojo.employee.EmployeeAdminUser;
import com.busyqa.crm.pojo.employee.EmployeeRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @RequestMapping(
            method = RequestMethod.GET,
            value = "/admin/users")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeAdminUser> getSortUsers() {
        return this.employeeService.getSortUsers();


    }

    /**
     * SHOW ALL USERS PER ROLE
     * @param role
     * @return
     * by Marc Cavada
     */
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/admin/{role}")
    public @ResponseBody List<EmployeeAdminUser> showUsersPerRole(@PathVariable("role")EmployeeRole role) {
        return this.employeeService.showUsersPerRole(role);
    }


    /**
     * @param employeeAdminUser
     * @param session
     * @return
     * by Marc Cavada
     */
    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE, value = "/admin/login")
    public EmployeeLoginStatus login(@RequestBody EmployeeAdminUser employeeAdminUser, HttpSession session) {

        System.out.println(session.getId()
                + ":" + employeeAdminUser.getUserName() + " :"
                + employeeAdminUser.getPassword() + " :"
                + employeeAdminUser.getPassword());

        return employeeService.login(employeeAdminUser, session);
    }



    /**
     * CHECK IF LOGIN
     * @param session
     * @return HttpSession status
     * by Marc Cavada
     */
    @RequestMapping(method = RequestMethod.GET, value = "/admin/checklogin")
    public EmployeeLoginStatus checkLogin(HttpSession session) {
        return this.employeeService.checkLoginSessionStatus(session);
    }



}
