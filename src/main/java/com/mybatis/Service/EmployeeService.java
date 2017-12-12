package com.mybatis.Service;

import com.mybatis.Model.Employee;
import java.util.*;

/**
 * @Author: janven
 * @Descrption: Created in ${Time} on 2017/11/28.
 */
public interface EmployeeService {
    Employee getEmployee(int id);
    List<Employee> getEmployees();
}
