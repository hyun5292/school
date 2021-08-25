package com.spring.ex;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.addClass.StudentInformation123456789;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	// �̰� �����Ű�� index�� ������
	@RequestMapping("/index")
	public String goIndex() {
		// �׸��� index -> viewer�� �������ذž�
		return "index";
	}

	// index.jsp���� student���� �Ѱ��ָ� ���⼭ ���� ó������
	// ���⼭ �ٽ� �۾��ϰ�
	@RequestMapping(method = RequestMethod.POST, value = "/student")
	public String goStudent(HttpServletRequest httpServletRequest, Model model) {
		System.out.println("RequestMothet.POST");
		// �� �� id��� parameter�� id�� ���
		String id = httpServletRequest.getParameter("id");
		// �ý��ۿ� �������
		System.out.println("ID : " + id);
		// �׸��� studentId ��ü ����� model�� ������
		model.addAttribute("studentId", id);
		// �׸��� student/studentId -> viewer�� �������ذž�
		return "student/studentId";
	}

	// �̰� �����Ű�� index�� ������
	@RequestMapping("/index2")
	public String index() {
		// �׸��� index -> viewer�� �������ذž�
		return "index2";
	}
	
	@RequestMapping("/studentView2")
	//StudentInformation123456789 �̰� �̸� �ʹ� ���ڳ� �׷��� stuInfo�� ��Ī�� �༭ ����ҷ�
	public String studentView(@ModelAttribute("stuInfo") StudentInformation123456789 student) {
		// �׸��� studentView2 -> viewer�� �������ذž�
		return "studentView2";
	}
}
