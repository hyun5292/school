package memo.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;

import memo.dto.MemoDto;
import sqlmap.MybatisManager;

public class MemoDao {
	public List<MemoDto> listMemo(){
		SqlSession session = MybatisManager.getInstance().openSession();
		List<MemoDto> list = session.selectList("memo.listAll");
		
		return list;
	}
}
