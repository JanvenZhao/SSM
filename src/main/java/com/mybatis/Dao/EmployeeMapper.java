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

    @Insert("insert into employee (lastName,email,firstName) values (#{lastName},#{email},'')")
    void addEmployee(Employee employee);

    @Select("select id,lastName,email from employee order by id desc limit 1")
    Employee reurnTheNewOne();

    @Delete("delete from employee where id=#{id}")
    void deleteEmp(int id);

    @Update("update employee set lastName=#{lastName},email=#{email} where id=#{id}")
    void modifyEmp(Employee emp);
}
