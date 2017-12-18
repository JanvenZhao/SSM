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

import base.HttpResultMap;

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

    public Map list(){
        List<Employee> employees = employeeService.getEmployees();

        HttpResultMap map = new HttpResultMap();
        if (employees != null)
        {
            map.setSuccessObject(employees);
        }else {
            map.setFailureMessage("获取数据失败");
        }
        return map;

    }

    @ResponseBody
    @RequestMapping(value = "/getID")
    public Map getEmplyeeByID(@RequestParam("id") int  id){

        HttpResultMap map = new HttpResultMap();

        if (id == 0){
            map.setFailureMessage("id 不能为0");
        }else {
            Employee employee = employeeService.getEmployee(id);
            if (employee != null){
                map.setSuccessObject(employee);
            }else {
                map.setFailureMessage("数据获取失败");
            }
        }

        return map;

    }

    @ResponseBody
    @RequestMapping(value = "/addEmp")
    public Map getEmplyeeByID(@RequestParam("id") int  id,
                                   @RequestParam("firName") String fir,
                                   @RequestParam("lastName") String last,
                                   @RequestParam("email") String email){

        HttpResultMap map = new HttpResultMap();
        employeeService.addEmployee(id,last,fir,email);
        Employee employee = employeeService.getNewOne();

        if (employee != null){
            map.setSuccessObject(employee);
        }else {
            map.setFailureMessage("添加失败");
        }
        return map;

    }


    @ResponseBody
    @RequestMapping(value = "/delete")
    public Map deleteEmp(@RequestParam("id") int id){
        HttpResultMap map = new HttpResultMap();
        try {
            employeeService.deleteEmp(id);
            map.setSuccessObject("");
        }catch (Exception e){
            map.setFailureMessage("删除失败");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/modify")
    public Map deleteEmp(@RequestParam("id") int id,
                         @RequestParam("lastName") String last,
                         @RequestParam("email") String email){

        Employee employee = new Employee();
        employee.setEmail(email);
        employee.setLastName(last);
        employee.setId(id);

        HttpResultMap map = new HttpResultMap();

        Employee emp = employeeService.modifyEmp(employee);
        if (emp != null){
            map.setSuccessObject(emp);
        }else {
            map.setFailureMessage("更新数据失败");
        }

        return map;
    }
}
