package com.mybatis.Controller;

import org.springframework.stereotype.Controller;

import com.mybatis.Model.Employee;
import com.mybatis.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import javax.validation.constraints.Null;

/**
 * @Author: janven
 * @Descrption: Created in ${Time} on 2017/11/28.
 */
@Controller
public class EmployeeController {

    protected Logger log = Logger.getLogger(EmployeeController.class);


    @Autowired
    private EmployeeService employeeService;

    @ResponseBody
    @RequestMapping(value="/listAll",method= RequestMethod.GET)

    public Map list(Map<String,Object> map){
        List<Employee> employees = employeeService.getEmployees();
       /*测试用
      if(employees==null)
         System.out.println("null");
      else
         System.out.println(employees);*/

        map.put("employees",employees);
        return map;

    }

    @ResponseBody
    @RequestMapping(value = "/getID")
    public Employee getEmplyeeByID(@RequestParam("id") int  id){

        if (id == 0){
            log.error("id 不能为0");
        }

        return employeeService.getEmployee(id);


    }

    @ResponseBody
    @RequestMapping(value = "/addEmp")
    public Employee getEmplyeeByID(@RequestParam("id") int  id,
                                   @RequestParam("firName") String fir,
                                   @RequestParam("lastName") String last,
                                   @RequestParam("email") String email){

        employeeService.addEmployee(id,last,fir,email);

        return employeeService.getNewOne();

    }


    @ResponseBody
    @RequestMapping(value = "/delete")
    public Map deleteEmp(@RequestParam("id") int id){
        Map map = new HashMap();
        map.put("message","删除成功");
        employeeService.deleteEmp(id);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/modify")
    public Employee deleteEmp(@RequestParam("id") int id,
                         @RequestParam("lastName") String last,
                         @RequestParam("email") String email){

        Employee employee = new Employee();
        employee.setEmail(email);
        employee.setLastName(last);
        employee.setId(id);
        return employeeService.modifyEmp(employee);

    }


}
