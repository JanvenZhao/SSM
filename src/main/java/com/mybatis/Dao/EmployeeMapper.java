package com.mybatis.Dao;

import org.apache.ibatis.annotations.*;
import com.mybatis.Model.Employee;
import java.util.*;

/**/
/**
 * @Author: janven
 * @Descrption: Created in ${Time} on 2017/11/28.
 */

public interface EmployeeMapper {

    @Select("select id,lastName,email from employee where id=#{id}")
    Employee getEmployeeById(int id);

    @Select("select * from employee")
    List<Employee> getAllEmployees();
}
