package com.vdb.service;

import com.vdb.model.Employee;
import com.vdb.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootTest
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @MockitoBean
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void signUpTest() throws ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");

        Date date=simpleDateFormat.parse("09-09-2025");

        Employee employee=new Employee(1,"vivek","pune",989898893,99890.90,date,"vivek@gmail.com","vivek");

        employeeService.signUp(employee);

        Mockito.verify(employeeRepository,Mockito.times(1)).save(employee);

    }

    @Test
    public void findAll() throws ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");

        Date date=simpleDateFormat.parse("09-09-2025");

        List<Employee> employeeList=Stream.of(new Employee(1,"vivek","pune",989898893,99890.90,date,"vivek@gmail.com","vivek")
        ,new Employee(2,"sachin","pune",989898893,99890.90,date,"sachin@gmail.com","sachin")).toList();

        Mockito.when(employeeRepository.findAll()).thenReturn(employeeList);

        Assertions.assertEquals(2,employeeService.findAll().size());

    }

    @Test
    public void findByIdTest() throws ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");

        Date date=simpleDateFormat.parse("09-09-2025");

        Mockito.when(employeeRepository.findById(1)).thenReturn(Optional.of(new Employee(1, "vivek", "pune", 989898893, 99890.90, date, "vivek@gmail.com", "vivek")));

        employeeService.findById(1);

        Mockito.verify(employeeRepository,Mockito.times(1)).findById(1);

    }

    @Test
    public void updateTest() throws ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");

        Date date=simpleDateFormat.parse("09-09-2025");

        Employee employee=new Employee(1,"vivek","pune",989898893,99890.90,date,"vivek@gmail.com","vivek");

        employeeService.update(employee);

        Mockito.verify(employeeRepository,Mockito.times(1)).save(employee);
    }


    @Test
    public void deleteByIdTest() throws ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");

        Date date=simpleDateFormat.parse("09-09-2025");

        Employee employee=new Employee(1,"vivek","pune",989898893,99890.90,date,"vivek@gmail.com","vivek");

        employeeService.deleteById(employee.getEmpId());

        Mockito.verify(employeeRepository,Mockito.times(1)).deleteById(employee.getEmpId());

    }

}
