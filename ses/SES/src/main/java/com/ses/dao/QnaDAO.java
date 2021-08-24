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
import com.ses.dto.QnaDTO;
import com.ses.dto.SiteListDTO;

@Repository
public class QnaDAO {
	@Autowired
	public SqlSessionTemplate temp;

	// list ������
	public List<QnaDTO> GetQList(Map<String, Object> map) {
		return temp.selectList("Qmap.QList", map);
	}

	// ����ȭ�鿡 ��� 4�� ��������
	public List<QnaDTO> getFour() {
		return temp.selectList("Qmap.GetFour");
	}

	// �����ϱ� Ŭ�� �� ���� �����ֱ�
	public QnaDTO GetQna(int Qnum) {
		return temp.selectOne("Qmap.GetQna", Qnum);
	}

	// ��ü ������ ���� ���ϱ�
	public int ListCnt() {
		return temp.selectOne("Qmap.ListCnt");
	}

	// �˻��� ������ ���� ���ϱ�
	public int SearchedListCnt(Map<String, Object> map) {
		return temp.selectOne("Qmap.SearchCnt", map);
	}

	// list �˻�
	public List<QnaDTO> SearchList(Map<String, Object> map) {
		return temp.selectList("Qmap.Search", map);
	}

	//���ο� ���� �Է�
	public boolean NewQna(Map<String, Object> map) {
		int rs = temp.insert("Qmap.NewQna", map);
		if (rs == 1)
			return true;
		else
			return false;
	}
}
