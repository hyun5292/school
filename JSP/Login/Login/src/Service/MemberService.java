package Service;

import java.util.List;

import DaoDto.AccountDAO;
import DaoDto.AccountDTO;

public class MemberService {
	private AccountDAO dao;

	public MemberService() {
		dao = AccountDAO.getInstance();
	}

	public boolean login(String id, String pw) {
		int Chk = dao.userCheck(id, pw);
		
		if (Chk == AccountDAO.ACCOUNT_LOGIN_SUCCESS) {
			return true;
		} else
			return false;
	}

	public AccountDTO getMember(String id) {
		return dao.getMember(id);
	}

	public boolean update(AccountDTO dto) {// ȸ������ �ɷηη� �޾Ƽ�
		AccountDTO newDto = dao.getMember(dto.getId());
		if (newDto.getPw().equals(dto.getPw())) // ��й�ȣ�� ������
		{
			dao.updateMember(dto);
			return true;
		} else return false;
	}

	public List<AccountDTO> getMemberList() {
		return dao.accountAll();
	}

	public boolean join(String id, String pw, String name, String email) {
		if (dao.getMember(id) == null) {
			AccountDTO dto = new AccountDTO();
			dto.setId(id);
			dto.setPw(pw);
			dto.setName(name);
			// dto.setEmail(email);
			dao.insertMember(dto);
			return true;
		} else
			return false;
	}

}