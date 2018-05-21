package com.itcv.chapter2.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itcv.chapter2.util.CollectionUtil;
import com.itcv.chapter2.util.PropsUtil;

/**
 * ���ݿ��������ӳ�֮ǰ�Ĵ���
 * @author zf
 *
 */
public class DatabaseHelperBak {

	private static final Logger logger = LoggerFactory.getLogger(DatabaseHelperBak.class);
	
	private static final ThreadLocal<Connection> CONNECTION_HOLDER = new ThreadLocal<Connection>();
	private static final QueryRunner QUERY_RUNNER = new QueryRunner();
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
	}
	
	/**
	 * ��ȡ���ݿ�����
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		conn = CONNECTION_HOLDER.get();
		if (conn == null) {
			try {
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			} catch (SQLException e) {
				logger.error("get connection failure", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.set(conn);
			}
		}
		return conn;
	}

	/**
	 * �ر����ݿ�����
	 * @param conn
	 */
	public static void closeConnection(){
		Connection conn = CONNECTION_HOLDER.get();
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("close connection failure",e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.remove();
			}
		}
	}
	
	/**
	 * ��ѯʵ���б�
	 * @param entityClass
	 * @param sql
	 * @param params
	 * @return
	 */
	public static <T> List<T> queryEntityList(Class<T> entityClass,String sql,Object... params) {
		List<T> entityList;
		Connection conn = getConnection();
		try {
			entityList = QUERY_RUNNER.query(conn,sql,new BeanListHandler<T>(entityClass),params);
		} catch (SQLException e) {
			logger.error("query entity list failure",e);
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
		return entityList;
	}
	
	/**
	 * ��ѯʵ��
	 * @param entityClass
	 * @param sql
	 * @param params
	 * @return
	 */
	public static <T>T queryEntity(Class<T> entityClass, String sql, Object...params) {
		T entity;
		try {
			Connection conn = getConnection();
			entity = QUERY_RUNNER.query(conn, sql,new BeanHandler<T>(entityClass),params);
		} catch (SQLException e) {
			logger.error("query entity failure",e);
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
		return entity;
	}
	
	/**
	 * ִ�в�ѯ���
	 * @param sql
	 * @param params
	 * @return
	 */
	public static List<Map<String,Object>> executeQuery(String sql,Object...params) {
		List<Map<String,Object>> result;
		try {
			Connection conn = getConnection();
			result = QUERY_RUNNER.query(conn, sql,new MapListHandler(),params);
		} catch (SQLException e) {
			logger.error("execute query failure",e);
			throw new RuntimeException(e);
		}
		return result;
	}
	
	/**
	 * ִ�и�����䣨����update��insert��delete��
	 * @param sql
	 * @param params
	 * @return
	 */
	public static int executeUpdate(String sql,Object... params) {
		int rows = 0;
		try {
			Connection conn = getConnection();
			rows = QUERY_RUNNER.update(conn,sql,params);
		} catch (SQLException e) {
			logger.error("execute update failure", e);
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
		return rows;
	}
	
	/**
	 * ����ʵ��
	 * @param entityClass
	 * @param fieldMap
	 * @return
	 */
	public static <T> boolean insertEntity(Class<T> entityClass,Map<String,Object> fieldMap) {
		if(CollectionUtil.isEmpty(fieldMap)){
			logger.error("can not insert entity: fieldMap is empty");
			return false;
		}
		
		String sql = "INSERT INTO "+ getTableName(entityClass);
		StringBuilder columns = new StringBuilder("(");
		StringBuilder values = new StringBuilder("(");
		for (String fieldName : fieldMap.keySet()) {
			columns.append(fieldName).append(",");
			values.append("?, ");
		}
		columns.replace(columns.lastIndexOf(", "),columns.length(),")");
		values.replace(values.lastIndexOf(", "), values.length(), ")");
		sql += columns + " VALUES " + values;
		
		Object [] params = fieldMap.values().toArray();
		return executeUpdate(sql,params) == 1;
	}
	
	/**
	 * ����ʵ��
	 * @param entityClass
	 * @param id
	 * @param fieldMap
	 * @return
	 */
	public static <T> boolean updateEntity(Class<T> entityClass, long id,Map<String,Object> fieldMap) {
		if (CollectionUtil.isEmpty(fieldMap)) {
			logger.error("can not update entity: fieldMap is empty");
			return false;
		}
		
		String sql = "UPDATE "+ getTableName(entityClass) + " SET ";
		StringBuilder columns = new StringBuilder();
		for (String fieldName : fieldMap.keySet()) {
			columns.append(fieldName).append("=?, ");
		}
		sql += columns.substring(0,columns.lastIndexOf(", ")) + " WHERE id=? ";
		
		List<Object> paramList = new ArrayList<Object>();
		paramList.addAll(fieldMap.values());
		paramList.add(id);
		Object [] params = paramList.toArray();
		
		return executeUpdate(sql, params) == 1;
	}
	
	/**
	 * ɾ��ʵ��
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public static <T> boolean delteEntity(Class<T> entityClass, long id) {
		String sql = "DELETE FROM " + getTableName(entityClass) + " WHERE id=? ";
		return executeUpdate(sql, id) ==1;
	}
	
	private static String getTableName(Class<?> entityClass) {
		return entityClass.getSimpleName();
	}
}
