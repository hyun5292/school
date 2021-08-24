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

import com.ses.dto.SiteListDTO;

@Repository
public class SiteListDAO {
	@Autowired
	public SqlSessionTemplate temp;

	// list ������
	public List<SiteListDTO> GetSLList(Map<String, Object> map) {
		return temp.selectList("SLmap.SLList", map);
	}

	// list �˻�
	public List<SiteListDTO> SearchList(Map<String, Object> map) {
		return temp.selectList("SLmap.Search", map);
	}

	// �˻��� ������ ���� ���ϱ�
	public int SearchedListCnt(Map<String, Object> map) {
		return temp.selectOne("SLmap.SearchCnt", map);
	}

	// ��ü ������ ���� ���ϱ�
	public int ListCnt(Map<String, Object> map) {
		return temp.selectOne("SLmap.ListCnt", map);
	}

	// ���� ����
	public boolean CancelSNS(Map<String, Object> map) {
		int rs = temp.update("SLmap.CancelSNS", map);
		if (rs == 1)
			return true;
		else
			return false;
	}

	// Ư�� �� �˻�
	public SiteListDTO SearchOne(Map<String, Object> map) {
		return temp.selectOne("SLmap.SearchOne", map);
	}

	// �ش� ȸ���� ���� ���� ���� ���ϱ�
	public int MemberPageCnt(String mId) {
		return temp.selectOne("SLmap.MemberPageCnt", mId);
	}
}