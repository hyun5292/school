package com.spring.hamsamo.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.hamsamo.dto.BoardDTO;

@Repository
public interface BoardDAO {
	// list 가져와
	public List<BoardDTO> GetBList(Map<String, Object> map);

	// 검색된 게시물 목록 불러오기
	public List<BoardDTO> GetBSearchedList(Map<String, Object> map);

	// 게시물 댓글 불러오기
	public List<BoardDTO> GetReplies(int bGroup);

	// 게시물 선택 시 내용 보여주기
	public List<BoardDTO> GetBContent(String bId);

	// 게시물 작성 후 DB 저장
	public boolean BWrite(Map<String, String> map);

	// 수정된 글 DB 저장
	public boolean BModify(Map<String, String> map);

	// 게시물 삭제
	public boolean BDelete(String bGroup);

	// 댓글 DB 저장
	public void BReply(Map<String, Object> map);

	// 대댓글 DB 저장
	public void BRReply(Map<String, Object> map);

	// 전체 페이지 개수 구하기
	public int PageCnt();

	// 조회수
	public void UpHit(Map<String, Object> map);

	// 댓글 순서 처리
	public void ReplyReset(int bStep);

	// 댓글 삭제
	public void BReplyDelete(Map<String, Object> map);

	// 탈퇴한 회원 ID 삭제
	public void UpdateMember(String mId);

}
