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
}
