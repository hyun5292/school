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

	// 이걸 실행시키면 index로 가겠지
	@RequestMapping("/index")
	public String goIndex() {
		// 그리고 index -> viewer를 설정해준거야
		return "index";
	}

	// index.jsp에서 student한테 넘겨주면 여기서 일을 처리하지
	// 여기서 다시 작업하고
	@RequestMapping(method = RequestMethod.POST, value = "/student")
	public String goStudent(HttpServletRequest httpServletRequest, Model model) {
		System.out.println("RequestMothet.POST");
		// 이 때 id라는 parameter를 id에 담고
		String id = httpServletRequest.getParameter("id");
		// 시스템에 출력해줘
		System.out.println("ID : " + id);
		// 그리고 studentId 객체 만든걸 model에 담아줬어
		model.addAttribute("studentId", id);
		// 그리고 student/studentId -> viewer를 설정해준거야
		return "student/studentId";
	}

	// 이걸 실행시키면 index로 가겠지
	@RequestMapping("/index2")
	public String index() {
		// 그리고 index -> viewer를 설정해준거야
		return "index2";
	}
	
	@RequestMapping("/studentView2")
	//StudentInformation123456789 이거 이름 너무 길자나 그래서 stuInfo로 별칭을 줘서 사용할래
	public String studentView(@ModelAttribute("stuInfo") StudentInformation123456789 student) {
		// 그리고 studentView2 -> viewer를 설정해준거야
		return "studentView2";
	}
}
