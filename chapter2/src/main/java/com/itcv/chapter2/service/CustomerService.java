package com.itcv.chapter2.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itcv.chapter2.helper.DatabaseHelper;
import com.itcv.chapter2.model.Customer;
import com.itcv.chapter2.util.PropsUtil;


/**
 * 提供客户数据服务
 * @author zf 
 *
 */
public class CustomerService {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
	/*
	private static final String DRIVER;
	private static final String URL;
	private static final String USERNAME;
	private static final String PASSWORD;
	
	static {
		Properties conf = PropsUtil.loadProps("config.properties");
		DRIVER = conf.getProperty("driver");
		URL = conf.getProperty("url");
		USERNAME = conf.getProperty("jdbc.username");
		PASSWORD = conf.getProperty("password");
		
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			logger.error("can not load jdbc driver",e);
		}
		
	}*/

	/**
	 * 获取客户列表
	 * @param keyword
	 * @return
	 */
	//修改前
	/*public List<Customer> getCustomerList(){
		Connection conn = null;
		try {
			List<Customer> customerList = new ArrayList<Customer>();
			String sql = "SELECT * FROM customer";
			conn = DatabaseHelper.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()){
				Customer customer = new Customer();
				customer.setId(rs.getLong("id"));
				customer.setName(rs.getString("name"));
				customer.setContact(rs.getString("contact"));
				customer.setTelephone(rs.getString("telephone"));
				customer.setEmail(rs.getString("email"));
				customer.setRemark(rs.getString("remark"));
				customerList.add(customer);
			}
			return customerList;
		} catch (SQLException e) {
			logger.error("execute sql failure",e);
		} finally {
			DatabaseHelper.closeConnection(conn);
		}
		return null;
	}*/
	public List<Customer> getCustomerList() {
		Connection conn = DatabaseHelper.getConnection();
		String sql = "SELECT * FROM customer";
		return DatabaseHelper.queryEntityList(Customer.class, sql);
	}
	
	
	/**
	 * 获取客户
	 * @param id
	 * @return
	 */
	public Customer getCustomer(long id){
		//return DatabaseHelper.queryEntity(entityClass, sql, params);
		return null;
	}
	
	/**
	 * 创建客户
	 * @param fieldMap
	 * @return
	 */
	public boolean createCustomer(Map<String,Object> fieldMap){
		
		return DatabaseHelper.insertEntity(Customer.class, fieldMap);
	}
	
	/**
	 * 更新客户
	 * @param id
	 * @param fieldMap
	 * @return
	 */
	public boolean updateCustomer(long id,Map<String,Object> fieldMap){
		return DatabaseHelper.updateEntity(Customer.class, id, fieldMap);
	}
	
	/**
	 * 删除客户
	 * @param id
	 * @return
	 */
	public boolean deleteCustomer(long id){
		return DatabaseHelper.delteEntity(Customer.class, id);
	}
	
}
