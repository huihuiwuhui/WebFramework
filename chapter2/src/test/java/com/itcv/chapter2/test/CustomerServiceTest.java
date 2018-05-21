package com.itcv.chapter2.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.itcv.chapter2.helper.DatabaseHelper;
import com.itcv.chapter2.model.Customer;
import com.itcv.chapter2.service.CustomerService;

public class CustomerServiceTest {

	private final CustomerService customerService;
	
	public CustomerServiceTest(){
		customerService = new CustomerService();
	}
	
	//初始化数据
	@Before
	public void init() throws Exception{
	   String file = "sql/customer.sql";
	   System.out.println("怎么不执行呢？");
	   DatabaseHelper.executeSqlFile(file);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void getCustomerListTest() throws Exception{
		List<Customer> customerList = customerService.getCustomerList();
		Assert.assertEquals(2,customerList.size());
	}
	
	@Test
	public void getCustomerTest() throws Exception{
		long id =1;
		Customer customer = customerService.getCustomer(id);
		Assert.assertNotNull(customer);
	}
	
	@Test
	public void createCustomerTest() throws Exception{
		Map<String,Object> fieldMap = new HashMap<String,Object>();
		fieldMap.put("name","customer100");
		fieldMap.put("contact", "John");
		fieldMap.put("telephone", "135755451");
		boolean result = customerService.createCustomer(fieldMap);
		Assert.assertTrue(result);
	}
	
	@Test
	public void updateCustomerTest() throws Exception{
		long id = 1;
		Map<String,Object> fieldMap = new HashMap<String,Object>();
		fieldMap.put("contact","Eric");
		boolean result = customerService.updateCustomer(id, fieldMap);
		Assert.assertTrue(result);
	}
	
	@Test
	public void deleteCustomerTest() throws Exception{
		long id =1;
		boolean result = customerService.deleteCustomer(id);
		Assert.assertTrue(result);
	}
	
	public static void main(String[] args) {
		StringBuilder columns = new StringBuilder("(contact,name,telephone,");
		//String columns ="(contact,name,telephone,";
		columns.replace(columns.lastIndexOf(","),columns.length(),")");
		System.out.println(columns);
		System.out.println(Customer.class.getSimpleName());
	}
}
