package com.alphasta.commons.base;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.github.abel533.sql.SqlMapper;

public class SqlService {
	
	private SqlSessionFactory SqlSessionFactory;

	public <T> List<T> select(String sql, Class<T> clazz) {
		SqlSession session = SqlSessionFactory.openSession();
		SqlMapper sqlMapper = new SqlMapper(session);
		return sqlMapper.selectList(sql, clazz);
	}

	public List<Map<String, Object>> select(String sql,
			Map<String, Object> params) {
		SqlSession session = SqlSessionFactory.openSession();
		SqlMapper sqlMapper = new SqlMapper(session);
		return sqlMapper.selectList(sql, params);
	}

	public <T> T selectOne(String sql ,Object param,Class<T> clazz){
		SqlSession session = SqlSessionFactory.openSession();
		SqlMapper sqlMapper = new SqlMapper(session);
		return sqlMapper.selectOne(sql, param, clazz);
	}
	public int delete(String sql, Object param) {
		int update = 0;
		SqlSession session = SqlSessionFactory.openSession();
		SqlMapper sqlMapper = new SqlMapper(session);
		if (param != null){
			update= sqlMapper.delete(sql, param);
		}
		else
			 sqlMapper.delete(sql);
		session.close();
		return update;
	}

	public int update(String sql, Object param) {
		int update = 0;
		SqlSession session = SqlSessionFactory.openSession();
		SqlMapper sqlMapper = new SqlMapper(session);
		if (param == null)
			update = sqlMapper.update(sql);
		else
			update = sqlMapper.update(sql, param);
		session.close();
		return update;
	}

	public int insert(String sql,Object param){
		int update = 0;
		SqlSession session = SqlSessionFactory.openSession();
		SqlMapper sqlMapper = new SqlMapper(session);
		if (param == null)
			update = sqlMapper.insert(sql);
		else
			update = sqlMapper.insert(sql, param);
		return update;
	}
	public SqlSessionFactory getSqlSessionFactory() {
		return SqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.SqlSessionFactory = sqlSessionFactory;
	}

}
