package com.spring.hamsamo.service;

import java.util.List;
import java.util.Map;

import com.spring.hamsamo.dto.BoardDTO;

public interface BoardService {
	// 게시물 목록 불러오기
	public List<BoardDTO> GetBList(Map<String, Object> map);

	// 검색된 게시물 목록 불러오기
	public List<BoardDTO> GetBSearchedList(Map<String, Object> map);

	// 해당 게시물 내용 가져오기
	public BoardDTO GetBContent(String bId);

	// 게시물 댓글 불러오기
	public List<BoardDTO> GetReplies(int bGroup);

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
