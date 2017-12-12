package com.mybatis.Controller;

import org.springframework.stereotype.Controller;

import com.mybatis.Model.Employee;
import com.mybatis.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


/**
 * @Author: janven
 * @Descrption: Created in ${Time} on 2017/11/28.
 */
@Controller
public class EmployeeController {

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

        return employeeService.getEmployee(id);


    }











}
