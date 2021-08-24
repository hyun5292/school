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

	// list 가져와
	public List<QnaDTO> GetQList(Map<String, Object> map) {
		return temp.selectList("Qmap.QList", map);
	}

	// 메인화면에 띄울 4개 가져오기
	public List<QnaDTO> getFour() {
		return temp.selectList("Qmap.GetFour");
	}

	// 문의하기 클릭 시 내용 보여주기
	public QnaDTO GetQna(int Qnum) {
		return temp.selectOne("Qmap.GetQna", Qnum);
	}

	// 전체 페이지 개수 구하기
	public int ListCnt() {
		return temp.selectOne("Qmap.ListCnt");
	}

	// 검색된 페이지 개수 구하기
	public int SearchedListCnt(Map<String, Object> map) {
		return temp.selectOne("Qmap.SearchCnt", map);
	}

	// list 검색
	public List<QnaDTO> SearchList(Map<String, Object> map) {
		return temp.selectList("Qmap.Search", map);
	}

	//새로운 문의 입력
	public boolean NewQna(Map<String, Object> map) {
		int rs = temp.insert("Qmap.NewQna", map);
		if (rs == 1)
			return true;
		else
			return false;
	}
}
