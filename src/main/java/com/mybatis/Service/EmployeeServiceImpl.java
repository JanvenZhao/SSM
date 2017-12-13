package com.mybatis.Service;

import com.mybatis.Model.Employee;

import com.mybatis.Dao.EmployeeMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: janven
 * @Descrption: Created in ${Time} on 2017/11/28.
 */

@Service

public class EmployeeServiceImpl implements EmployeeService{

    public EmployeeServiceImpl() {
        System.out.printf("init EmployeeServiceImpl");
    }

    @Autowired
    private EmployeeMapper employeeMapper;


    public Employee getEmployee(int id) {
        return employeeMapper.getEmployeeById(id);
    }

    public List<Employee> getEmployees() {
        return employeeMapper.getAllEmployees();
    }

    public void addEmployee(int id,String lastName,String firstName,String email){
        Employee employee = new Employee();
        employee.setLastName(lastName);
        employee.setEmail(email);
        employeeMapper.addEmployee(employee);
    }

    public Employee getNewOne(){
        return employeeMapper.reurnTheNewOne();
    }

    public void deleteEmp(int id){
        employeeMapper.deleteEmp(id);
    }


    public  Employee modifyEmp(Employee employee){
         employeeMapper.modifyEmp(employee);
         return employeeMapper.getEmployeeById(employee.getId());
    }


}
