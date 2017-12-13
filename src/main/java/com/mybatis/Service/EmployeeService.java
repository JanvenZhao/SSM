package com.mybatis.Service;

import com.mybatis.Model.Employee;
import java.util.*;

/**
 * @Author: janven
 * @Descrption: Created in ${Time} on 2017/11/28.
 */
public interface EmployeeService {


    /*单个查询*/
    Employee getEmployee(int id);
    /*批量查询*/
    List<Employee> getEmployees();
    /*增*/
    void addEmployee(int id,String lastName,String firstName,String email);
    /*获取最新的一个*/
    Employee getNewOne();
    /*删除*/
    void deleteEmp(int id);
    /*修改*/
    Employee modifyEmp(Employee employee);
}
