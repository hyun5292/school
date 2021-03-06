package com.spring.hamsamo.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.hamsamo.dao.BoardDAO;
import com.spring.hamsamo.dto.BoardDTO;

@Service
public class BoardRunService implements BoardService {
	@Inject
	BoardDAO bDAO;

	// 게시물 목록 불러오기
	@Override
	public List<BoardDTO> GetBList(Map<String, Object> map) {
		return bDAO.GetBList(map);
	}
	
	// 검색된 게시물 목록 불러오기
	@Override
	public List<BoardDTO> GetBSearchedList(Map<String, Object> map) {
		return bDAO.GetBSearchedList(map);
	}

	// 게시물 댓글 불러오기
	@Override
	public List<BoardDTO> GetReplies(int bGroup) {
		return bDAO.GetReplies(bGroup);
	}

	// 해당 게시물 내용 가져오기
	@Override
	public BoardDTO GetBContent(String bId) {
		return (bDAO.GetBContent(bId)).get(0);
	}

	// 게시물 작성 후 DB 저장
	@Override
	public boolean BWrite(Map<String, String> map) {
		return bDAO.BWrite(map);
	}

	// 수정된 글 DB 저장
	@Override
	public boolean BModify(Map<String, String> map) {
		return bDAO.BModify(map);
	}

	// 게시물 삭제
	@Override
	public boolean BDelete(String bGroup) {
		return bDAO.BDelete(bGroup);

	}

	// 댓글 DB 저장
	@Override
	public void BReply(Map<String, Object> map) {
		bDAO.BReply(map);
	}

	// 대댓글 DB 저장
	@Override
	public void BRReply(Map<String, Object> map) {
		bDAO.BRReply(map);
	}

	// 전체 페이지 개수 구하기
	@Override
	public int PageCnt() {
		return bDAO.PageCnt();
	}

	// 조회수
	@Override
	public void UpHit(Map<String, Object> map) {
		bDAO.UpHit(map);
	}

	// 댓글 순서 처리
	public void ReplyReset(int bStep) {
		bDAO.ReplyReset(bStep);
	}

	// 댓글 삭제
	@Override
	public void BReplyDelete(Map<String, Object> map) {
		bDAO.BReplyDelete(map);
	}

	// 탈퇴한 회원 ID 삭제
	@Override
	public void UpdateMember(String mId) {
		bDAO.UpdateMember(mId);
		
	}

	

}
