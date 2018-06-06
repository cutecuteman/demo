package com.etc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

/**
 * ���ݿ�����ĸ�����
 */
public class DBUtil {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/cmsdb?useunicode=true&characterEncoding=utf-8";
	private static final String USER = "root"; // �û���
	private static final String PASSWORD = "root";// ����

	/**
	 * ��ȡ���Ӷ���
	 * 
	 * @return ���Ӷ���
	 */
	public static Connection getConn() {

		Connection conn = null;
		try {

			Class.forName(DRIVER);
			// �õ����Ӷ���
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

		} catch (Exception e) {
			throw new RuntimeException("���ݿ�����ʧ��!", e);
		}
		return conn;
	}

	/**
	 * �ͷ���Դ
	 * 
	 * @param rs
	 *            �����
	 * @param pstmt
	 *            ��������
	 * @param conn
	 *            ���Ӷ���
	 */
	public static void close(ResultSet rs, PreparedStatement pstmt,
			Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException("�ͷ���Դʧ��!", e);
		}
	}

	/**
	 * ���ò���
	 * 
	 * @param sql
	 * @param conn
	 * @param pstmt
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	private static PreparedStatement setPstmt(String sql, Connection conn,
			PreparedStatement pstmt, Object... param) throws SQLException {
		pstmt = conn.prepareStatement(sql);
		if (param != null) {
			for (int i = 0; i < param.length; i++) {
				pstmt.setObject(i + 1, param[i]);
			}
		}
		return pstmt;
	}

	/**
	 * ͨ�õ����ݿ�(��,ɾ,��)��������
	 * 
	 * @param sql
	 *            sql���
	 * @param param
	 *            sql������
	 * @return ��Ӱ������
	 */
	public static int execute(String sql, Object... param) {
		Connection conn = getConn();
		try {
			return execute(sql, conn, param);
		} finally {
			close(null, null, conn);
		}
	}

	/**
	 * ͨ�õ���ɾ�Ĳ���(�������)
	 * 
	 * @param sql
	 * @param conn
	 * @param param
	 * @return
	 */
	public static int execute(String sql, Connection conn, Object... param) {
		PreparedStatement pstmt = null;
		try {
			pstmt = setPstmt(sql, conn, pstmt, param);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			//������õõ��쳣��Ϣ
			e.printStackTrace();
			throw new RuntimeException("���ݿ����ʧ��!", e);
		} finally {
			close(null, pstmt, null);
		}
	}

	/**
	 * ͨ�ò�ѯ����
	 * 
	 * @param sql  Ҫ��ѯ��sql���
	 * @param cla  Class����
	 * @param param  ����
	 * @return
	 */
	public static Object select(String sql, Class cla, Object... param) {
		Connection conn = getConn();
		try {
			return select(sql, conn, cla, param);
		} finally {
			close(null, null, conn);
		}
	}

	/**
	 * ������Ĳ�ѯ����
	 * 
	 * @param sql
	 * @param conn
	 * @param cla
	 * @param param
	 * @return
	 */
	public static Object select(String sql, Connection conn, Class cla,
			Object... param) {
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<Object> list = new ArrayList<Object>();
		try {
			pstmt = setPstmt(sql, conn, pstmt, param);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// ?rs ����� cla Class����
				//object��ʵ�������ݱ�ṹ��Ӧ��һ��ʵ���¼,object�����Ǹ�ʵ�������
				//�������convert�ǽ��������cla�������ת��
				Object object = convert(rs, cla);
				list.add(object);
			}
			return list;
		} catch (SQLException e) {
			throw new RuntimeException("���ݿ��ѯʧ��!", e);
		} finally {
			close(rs, pstmt, null);
		}
	}

	/**
	 * ��ȡ������¼ֵ,�ǵ�����¼ע��,����count(1)
	 * 
	 * @param sql
	 * @param param
	 * @return
	 */
	public static Object getFirst(String sql, Object... param) {
		Connection conn = getConn();
		try {
			return getFirst(sql, conn, param);
		} finally {
			close(null, null, conn);
		}
	}

	/**
	 * ��ȡ������¼ ����
	 * 
	 * @param sql
	 * @param conn
	 * @param param
	 * @return
	 */
	public static Object getFirst(String sql, Connection conn, Object... param) {
		List list = (List) select(sql, conn, Object.class, param);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * ���������
	 * 
	 * @param tran
	 * @return
	 */
	public static Object transaction(ITransaction tran) {
		Connection conn = getConn();
		try {
			conn.setAutoCommit(false);
			Object obj = tran.execute(conn);
			conn.commit();
			return obj;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException("�ع�ʧ��!", e);
			}
			throw new RuntimeException("����ִ��ʧ��", e);
		} finally {
			close(null, null, conn);
		}
	}

	/**
	 * ��ѯ�����ת��(��ϵ���ݿ� <=>java�����������)
	 * 
	 * @param rs �������
	 * @param cla  Class�����
	 * @return
	 */
	public static Object convert(ResultSet rs, Class cla) {
		try {
			//getName ���� �������������ṹ����
			if (cla.getName().equals("java.lang.Object")) {
				return rs.getObject(1);
			}
			// ����ʵ�����ʵ�� Class�����ķ���������ָ�������ʵ��
			// new Goods();  new News();  new person();  new Users();
			//newInstance �����ʵ����� �޲����Ĺ���
			Object object = cla.newInstance(); 
			//// �����ͷ��Ϣ����
			//rs.getMetaData() ��ȡ�� ResultSet ������еı�š����ͺ����ԡ�
			///
			ResultSetMetaData metaData = rs.getMetaData();
			// ѭ��Ϊʵ�����ʵ�������Ը�ֵ getColumnCount�õ��еĸ���
			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				// ��ȡ����  name
				String name = metaData.getColumnLabel(i);
				//// ����Ҫע�⣺����[��ѯ���������]������������һ�¡������ѭ������������.  rs.getObject(i) ������еĲ�ѯ����Ͷ���ƥ��
				// select empNo as eNo,empName as eName from employee
				BeanUtils.setProperty(object, name, rs.getObject(i));
			}
			return object;
		} catch (Exception e) {
			throw new RuntimeException("��������ʧ��!", e);
		}
	}

	/**
	 * ��ҳ���� mysql;
	 * 
	 * @param sql
	 * @param page
	 * @param pageSize
	 * @param cla
	 * @param param
	 * @return
	 */
	public static PageData getPage(String sql, Integer page, Integer pageSize,
			Class cla, Object... param) {
		String selSql = "select count(1) from (" + sql + ") t";
		if (page == null) {
			page = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		Integer count = Integer.parseInt(getFirst(selSql, param).toString());
		int start = (page - 1) * pageSize;
		selSql = sql + " limit " + start + "," + pageSize;
		List list = (List) select(selSql, cla, param);
		PageData data = new PageData(list, count, pageSize, page);
		return data;
	}

	/**
	 * ��ҳ���� sqlserver
	 * 
	 * @param page
	 * @param pageSize
	 * @param cla
	 * @param identity
	 * @return
	 */
	public static PageData getPage(Integer page, Integer pageSize, Class cla,
			String identity) {
		String name = cla.getName().substring(
				cla.getName().lastIndexOf(".") + 1);// �������������������ȡ���ݿ����
		String selSql = "select count(*) from " + name;// ��ȡ����
		if (page == null) {
			page = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		int start = (page - 1) * pageSize;
		Integer count = Integer.parseInt(getFirst(selSql, null).toString());
		selSql = "select top " + pageSize + " * from " + name + " where "
				+ identity + " not in (select top " + start + " " + identity
				+ " from " + name + " )"; // ƴ�Ӳ�ѯ���
		List list = (List) select(selSql, cla, null);
		PageData data = new PageData(list, count, pageSize, page);
		return data;
	}

}
