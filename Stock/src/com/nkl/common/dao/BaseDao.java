package com.nkl.common.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.jdbc.Work;
import org.hibernate.transform.Transformers;
import org.hibernate.type.Type;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;



public class BaseDao extends HibernateDaoSupport {

	/**
	 * 添加对象
	 * @param o
	 */
	public void add(Object o) {
		super.getHibernateTemplate().save(o);
	}
	/**
	 * 删除对象
	 * @param z
	 */
	public void del(Object o) {
		super.getHibernateTemplate().delete(o);
	}
	/**
	 * 根据ID删除对象
	 * @param z
	 * @param id
	 */
	@SuppressWarnings({ "rawtypes" })
	public void del(Class c,Integer id) {
		del(get(c, id));
	}

	/**
	 * 根据ID获取对象
	 * @param z
	 * @param id
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object get(Class c,Integer id)
	{
		return super.getHibernateTemplate().get(c, id);
	}
	/**
	 * 更新对象
	 * @param o
	 */
	public void update(Object o) {
		super.getHibernateTemplate().update(o);
	}
	public void merge(Object o) {
		super.getHibernateTemplate().merge(o);
	}
	public void attachDirty(Object o) {
		super.getHibernateTemplate().saveOrUpdate(o);
	}
	
	/**
	 * 查询对象集合
	 * @param hql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List executeQueryHql(String hql,Object[] params) {
		return super.getHibernateTemplate().find(hql,params);
	}
	/**
	 * 查询对象集合数量
	 * @param hql
	 * @param params
	 * @return
	 */
	public long executeQueryCountHql(String hql,Object[] params) {
		return (Long)super.getHibernateTemplate().find(hql,params).get(0);
	}
	/**
	 * 根据对象按条件查询
	 * @param z
	 * @param o
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List findbyCra(final Class z,final Object o) {
		return super.getHibernateTemplate().executeFind(
			new HibernateCallback(){
				public Object doInHibernate(Session arg0) throws HibernateException, SQLException {
					Criteria criteria=arg0.createCriteria(z);
					criteria.add(Example.create(o));
					// TODO 自动生成方法存根
					return criteria.list();
				}	
			}
		);
	}
	
	/********************************DML风格批量插入/更新/删除操作，HQL分页方法******************************************/
	
	/**
	 * 设置参数
	 * @param query
	 * @param params
	 */
	public void setParam(Query query,Object[] params)
	{
		if(params!=null)
		{
			for (int i = 0 ; i < params.length ; i++)
			{
				query.setParameter( i, params[i]);
			}
		}
	}
	
	/**
	 * DML风格批量插入操作
	 * @param objects
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int executeInsertHql(final List objects){
		//通过一个HibernateCallback对象来执行查询
		return this.getHibernateTemplate().execute(new HibernateCallback(){
			//实现HibernateCallback接口必须实现的方法
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException
			{
				for (int i = 0; i < objects.size(); i++) {  
                    session.save(objects.get(i));         
                    if (i % 100 == 0) {  
                    	//清理缓存，否则可能再次查询的数据还是之前缓存的数据
                        session.flush();  
                        session.clear();  
                    }  
                }                  
                return null;  
			}
		});
	}
	
	/**
	 * DML风格批量更新/删除操作
	 * @param hql
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int executeUpdateHql(final String hql, final Object[] params){
		//通过一个HibernateCallback对象来执行查询
		Object o = getHibernateTemplate().execute(new HibernateCallback(){
						//实现HibernateCallback接口必须实现的方法
						public Object doInHibernate(Session session)
							throws HibernateException, SQLException
						{
							//清理缓存，否则可能再次查询的数据还是之前缓存的数据
							session.flush();
							session.clear();
							//执行Hibernate查询
							Query query = session.createQuery(hql);
							//为hql语句传入参数
							setParam(query, params);
							return query.executeUpdate()+"";
						}
					});
		return Integer.parseInt(o+"");
	}
	
	/**
	 * 使用hql语句进行分页查询
	 * @param hql 需要查询的hql语句
	 * @param params 如果hql有多个个参数需要传入，params就是传入hql的参数数组
	 * @param offset 第一条记录索引
	 * @param pageSize 每页需要显示的记录数
	 * @return 当前页的所有记录
	 */
	@SuppressWarnings("rawtypes")
	public List findByPageHql(final String hql, final Object[] params,
		final int offset, final int pageSize){
		//通过一个HibernateCallback对象来执行查询
		List list = getHibernateTemplate()
			.executeFind(new HibernateCallback()
			{
				//实现HibernateCallback接口必须实现的方法
				public Object doInHibernate(Session session)
					throws HibernateException, SQLException
				{
					//执行Hibernate分页查询
					Query query = session.createQuery(hql);
					//为hql语句传入参数
					setParam(query, params);
					List result = query.setFirstResult(offset)
						.setMaxResults(pageSize)
						.list();
					return result;
				}
			});
		return list;
	}
	

/*******************************原生态sql语句、原生态存储过程*****************************************/
	
	
	/**
	 * 原生态sql语句查询:标量和实体查询，返回标量和实体的Object数组集合
	 * @param sql
	 * @param entitys
	 * @param scalars
	 * @param types
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List executeQueryScalarAndEntitySql(final String sql,final String[] alias,final Class[] entitys,final String[] scalars,final Type[] types,final Object[] params)
	{
		//通过一个HibernateCallback对象来执行查询
		List list = getHibernateTemplate()
			.executeFind(new HibernateCallback()
			{
				//实现HibernateCallback接口必须实现的方法
				public Object doInHibernate(Session session)
					throws HibernateException, SQLException
				{
					//执行Hibernate分页查询
					SQLQuery query = session.createSQLQuery(sql);
					//为hql语句传入参数
					setParam(query, params);
					//添加标量
					if(scalars!=null)
					{
						for(int i=0;i<scalars.length;i++)
						{
							query.addScalar(scalars[i], types[i]);
						}
					}
					//添加实体
					if(alias!=null)
					{
						for(int i=0;i<alias.length;i++)
						{
							query.addEntity(alias[i], entitys[i]);
						}
					}
					return query.list();
				}
			});
		return list;
	}
	/**
	 * 原生态sql语句查询:标量查询，返回一个简单的标量值
	 * @param sql
	 * @param scalar
	 * @param type
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object executeQueryScalarSql(final String sql,final String scalar,final Type type,final Object[] params)
	{
		//通过一个HibernateCallback对象来执行查询
		Object o = getHibernateTemplate()
			.execute(new HibernateCallback()
			{
				//实现HibernateCallback接口必须实现的方法
				public Object doInHibernate(Session session)
					throws HibernateException, SQLException
				{
					//执行Hibernate分页查询
					SQLQuery query = session.createSQLQuery(sql);
					//为hql语句传入参数
					setParam(query, params);
					//添加标量
					if(scalar!=null)
					{
						query.addScalar(scalar, type);
					}
					return query.uniqueResult();
				}
			});
		return o;
	}
	/**
	 * 原生态sql语句查询:返回普通JavaBean的集合
	 * @param sql
	 * @param c
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List executeQueryJavaBeanSql(final String sql,final Class c,final Object[] params)
	{
		//通过一个HibernateCallback对象来执行查询
		List list = getHibernateTemplate()
			.executeFind(new HibernateCallback()
			{
				//实现HibernateCallback接口必须实现的方法
				public Object doInHibernate(Session session)
					throws HibernateException, SQLException
				{
					//执行Hibernate分页查询
					SQLQuery query = session.createSQLQuery(sql);
					//为hql语句传入参数
					setParam(query, params);
					
					query.setResultTransformer(Transformers.aliasToBean(c));
					
					query.addScalar("id", Hibernate.STRING);
					
					return query.list();
				}
			});
		return list;
	}
	/**
	 * 原生态sql语句查询：返回Map对象集合
	 * @param sql
	 * @param entitys
	 * @param scalars
	 * @param types
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List executeQueryMapSql(final String sql,final Object[] params)
	{
		//通过一个HibernateCallback对象来执行查询
		List list = getHibernateTemplate()
			.executeFind(new HibernateCallback()
			{
				//实现HibernateCallback接口必须实现的方法
				public Object doInHibernate(Session session)
					throws HibernateException, SQLException
				{
					//执行Hibernate分页查询
					SQLQuery query = session.createSQLQuery(sql);
					//为hql语句传入参数
					setParam(query, params);
					
					query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
					
					return query.list();
				}
			});
		return list;
	}
	/**
	 * 原生态sql语句查询：返回List对象集合
	 * @param sql
	 * @param entitys
	 * @param scalars
	 * @param types
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List executeQueryListSql(final String sql,final Object[] params)
	{
		//通过一个HibernateCallback对象来执行查询
		List list = getHibernateTemplate()
			.executeFind(new HibernateCallback()
			{
				//实现HibernateCallback接口必须实现的方法
				public Object doInHibernate(Session session)
					throws HibernateException, SQLException
				{
					//执行Hibernate分页查询
					SQLQuery query = session.createSQLQuery(sql);
					//为hql语句传入参数
					setParam(query, params);
					
					query.setResultTransformer(Transformers.TO_LIST);
					
					return query.list();
				}
			});
		return list;
	}
	/**
	 * 原生态sql语句更新
	 * @param sql
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int executeUpdateSql(final String sql,final Object[] params)
	{
		//通过一个HibernateCallback对象来执行查询
		Object o = getHibernateTemplate()
			.execute(new HibernateCallback()
			{
				//实现HibernateCallback接口必须实现的方法
				public Object doInHibernate(Session session)
					throws HibernateException, SQLException
				{
					//执行Hibernate分页查询
					SQLQuery query = session.createSQLQuery(sql);
					//为hql语句传入参数
					setParam(query, params);
					 
					return query.executeUpdate()+"";
				}
			});
		return Integer.parseInt(o+"");
	}

	
	/**
	 * 设置参数
	 * @param params
	 * @param pstmt
	 * @throws SQLException 
	 */
	private static void setParam(Object[] params,PreparedStatement pstmt) throws SQLException 
	{
		//判断是否有参数
		if( params != null ) 
        {
            for( int i = 0; i < params.length; i++ ) 
            {
            	pstmt.setObject(i+1, params[i]);  // 为预编译sql设置参数
            }
        }
	}
	
	/**
	 * 原生态sql语句更新：doWork方法执行更新操作
	 * @param sql
	 * @param params
	 */
	public void executeUpdateDoWork(final String sql,final Object[] params)
	{
		getSessionFactory().openSession().doWork(new Work() {
			
			public void execute(Connection conn) throws SQLException {
				PreparedStatement stmt=conn.prepareStatement(sql);
				setParam(params, stmt);
				stmt.executeUpdate();
			}
		});
	}
	
	/**
	 * 原生态sql语句：用于更新操作的存储过程
	 * @param pur
	 * @param params
	 * @return
	 */
	public Object executeCallUpdateSql(String pur,Object[] params)
	{
		Connection conn=null;
		CallableStatement cstmt=null;
        try { 
            conn = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
            cstmt=conn.prepareCall(pur);   
            setParam(params, cstmt);
            cstmt.execute(); 
        } 
        catch (Exception e) { 
           e.printStackTrace(); 
        } 
        finally {
			if (cstmt != null) {
				try{ cstmt.close();}catch(Exception e){}
			}
			if (conn != null) {
				 try{ conn.close();}catch(Exception e){}
			}
        }
        return null; 
	}
	/**
	 * 原生态sql语句：用于查询操作的存储过程
	 * @param pur
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List executeCallQuerySql(String pur,Object[] params)
	{
		List list=new ArrayList();
    	Connection conn=null;
		CallableStatement cstmt=null;
		ResultSet rs=null;
    	try { 
        	conn = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
            cstmt=conn.prepareCall(pur);   
            setParam(params, cstmt);
            cstmt.execute(); 
            
            rs=cstmt.getResultSet();
            if(rs!=null)
			{
            	list.add(ResultSupport.toResult(rs));//封装执行结果集
			}
        } 
        catch (Exception e) { 
           e.printStackTrace(); 
        } 
        finally {
        	if (rs != null) {
        		try{ rs.close();}catch(Exception e){}
			}
			if (cstmt != null) {
				try{ cstmt.close();}catch(Exception e){}
			}
			if (conn != null) {
				 try{ conn.close();}catch(Exception e){}
			}
           
        }
        return list; 
	}
	
	/**
	 * 原生态sql语句：设置总条数和当前页
     * @param className 实体类名
     * @param pur 存储过程语句
     * @param params 存储过程参数值
     * @param conn 连接
	 * @param pageNo 当前页码
	 * @param pageSize 每页显示的条数
	 * @param objectCount 总条数
	 */
	@SuppressWarnings("rawtypes")
	public void getCountSql(String pur,Object[] param,int[] pageNo, int pageSize,int[] objectCount)
	{
		//调用存储过程查询总条数
		List list=executeCallQuerySql(pur, param);
		if(list==null||list.size()==0)
		{
			objectCount[0]=0;
			pageNo[0]=0;
		}
		else
		{
			Result result=(Result)list.get(0);
			Map map=result.getRows()[0];
			objectCount[0]=Integer.parseInt(map.get("allCount").toString());
			// 判断当前页数问题
			if (pageNo[0] <= 0) {
				pageNo[0] = 1;
			}
			if(objectCount[0]<=0){
				pageNo[0] = 0;
			}
			else if (pageNo[0] > (objectCount[0] + pageSize - 1) / pageSize){
				pageNo[0] = (objectCount[0] + pageSize - 1) / pageSize;
			}
		}
	}
	
	/**
	 * 原生态sql语句：详细分页查询
	 * @param fields
	 * @param tables
	 * @param sCondition
	 * @param sOrder
	 * @param primaryKey
	 * @param pageNo
	 * @param pageSize
	 * @param objectCount
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getByPageSql(String fields,String tables,String sCondition,String sOrder,String primaryKey,int[] pageNo, int pageSize,int[] objectCount)
	{
		//调用存储过程查询总条数和设置当前页码
		String pur="{call selecCount(?,?,?,?)}";//四个参数分别是：表名、查询条件、排序、主键
		Object[] param={tables,sCondition,sOrder,primaryKey};
		getCountSql(pur, param, pageNo, pageSize, objectCount);
		if(objectCount[0]==0)
		{
			return null;
		}
		else
		{
			//调用存储过程查询当前页集合
			String pur2="{call selecPage(?,?,?,?,?,?,?)}";//七个参数分别是：查询字段 ,表名,查询条件,排序,当前页,每页条数,主键
			Object[] param2={fields,tables,sCondition,sOrder,pageNo[0]+"",pageSize+"",primaryKey};
			return executeCallQuerySql(pur2, param2);
		}
	}
	

}
