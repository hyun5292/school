package com.spring.hamsamo.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.hamsamo.dto.BoardDTO;

@Repository
public class BoardRunDAO implements BoardDAO {
	@Autowired
	public SqlSessionTemplate temp;

	// list 가져와
	@Override
	public List<BoardDTO> GetBList(Map<String, Object> map) {
		return temp.selectList("Bmap.BList", map);
	}

	// 검색된 게시물 목록 불러오기
	@Override
	public List<BoardDTO> GetBSearchedList(Map<String, Object> map) {
		return temp.selectList("Bmap.BSearchedList", map);
	}

	// 게시물 댓글 불러오기
	@Override
	public List<BoardDTO> GetReplies(int bGroup) {
		return temp.selectList("Bmap.BReplies", bGroup);
	}

	// 게시물 선택 시 내용 보여주기
	@Override
	public List<BoardDTO> GetBContent(String bId) {
		return temp.selectList("Bmap.BContentForm", bId);
	}

	// 게시물 작성 후 DB 저장
	@Override
	public boolean BWrite(Map<String, String> map) {
		int rs = temp.insert("Bmap.BWrite", map);
		if (rs == 1)
			return true;
		else
			return false;
	}

	// 수정된 글 DB 저장
	@Override
	public boolean BModify(Map<String, String> map) {
		int rs = temp.insert("Bmap.BModify", map);
		if (rs == 1)
			return true;
		else
			return false;
	}

	// 게시물 삭제
	@Override
	public boolean BDelete(String bGroup) {
		int rs = temp.delete("Bmap.BDelete", bGroup);
		int rs_group = temp.update("Bmap.BUpdateGroup", bGroup);

		if ((rs == 1) && (rs_group == 1))
			return true;
		else
			return false;
	}

	// 댓글 DB 저장
	@Override
	public void BReply(Map<String, Object> map) {
		temp.insert("Bmap.BReply", map);
	}

	// 대댓글 DB 저장
	@Override
	public void BRReply(Map<String, Object> map) {
		temp.insert("Bmap.BRReply", map);
	}

	// 전체 페이지 개수 구하기
	@Override
	public int PageCnt() {
		return temp.selectOne("Bmap.PageCnt");
	}

	// 조회수
	@Override
	public void UpHit(Map<String, Object> map) {
		temp.update("Bmap.UpHit", map);
	}

	// 댓글 순서 처리
	public void ReplyReset(int bStep) {
		temp.update("Bmap.ReplyReset", bStep);
	}

	// 댓글 삭제
	@Override
	public void BReplyDelete(Map<String, Object> map) {
		temp.update("Bmap.BReplyDelete", map);
	}

	// 탈퇴한 회원 ID 삭제
	@Override
	public void UpdateMember(String mId) {
		temp.update("Bmap.BMemberDelete", mId);
	}

}
