package com.ses.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import javax.activation.DataSource;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ses.dto.LogDTO;
import com.ses.dto.SiteListDTO;

@Repository
public class LogDAO {
	@Autowired
	public SqlSessionTemplate temp;

	// list ������
	public List<LogDTO> GetLList(Map<String, Object> map) {
		return temp.selectList("Lmap.LList", map);
	}

	// list �˻�
	public List<LogDTO> SearchList(Map<String, Object> map) {
		return temp.selectList("Lmap.Search", map);
	}

	// �˻��� ������ ���� ���ϱ�
	public int SearchedListCnt(Map<String, Object> map) {
		return temp.selectOne("Lmap.SearchCnt", map);
	}

	// ��ü ������ ���� ���ϱ�
	public int ListCnt(Map<String, Object> map) {
		return temp.selectOne("Lmap.ListCnt", map);
	}

	// ������ ���� �� ���ο� �α�
	public boolean InputLog(Map<String, Object> map) {
		int rs = temp.insert("Lmap.InputLog", map);
		if (rs == 1)
			return true;
		else
			return false;
	}
}