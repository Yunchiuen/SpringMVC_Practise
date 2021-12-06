package com.mvc.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.entity.Exam;
import com.mvc.entity.ExamName;
import com.mvc.entity.ExamPay;
import com.mvc.entity.ExamSlot;
import com.mvc.validator.ExamValidator;

@Controller
@RequestMapping("/exam")
public class ExamController {
	//CopyOnWriteArrayList支援多執行序CRUD
	private static List<Exam> exams = new CopyOnWriteArrayList<Exam>();
	private static List<ExamName> examNames=new ArrayList<>();
	private static List<ExamSlot> examSlots =new ArrayList<>();
	private static List<ExamPay> examPays =new ArrayList<>();
	{
		examNames.add(new ExamName("808", "1z0-808"));
		examNames.add(new ExamName("809", "1z0-809"));
		examNames.add(new ExamName("900", "1z0-900"));
		examNames.add(new ExamName("819", "1z0-819"));
		
		examSlots.add(new ExamSlot("A", "上午(A)"));
		examSlots.add(new ExamSlot("B", "中午(B)"));
		examSlots.add(new ExamSlot("C", "下午(C)"));
		examSlots.add(new ExamSlot("D", "晚上(D)"));
		
		examPays.add(new ExamPay("true", "已繳"));
		examPays.add(new ExamPay("false", "未繳"));
	}
	
	@Autowired
	private ExamValidator examValidator;
	
	@RequestMapping(value = { "/index", "/" })
	public String index(Model model) {
		Exam e = new Exam();
		model.addAttribute("exam", e);//給表單使用
		model.addAttribute("exams", exams);//給資料呈現使用
		model.addAttribute("examNames", examNames);//給資料呈現使用
		model.addAttribute("examSlots", examSlots);//給資料呈現使用
		model.addAttribute("examPays", examPays);//給資料呈現使用
		model.addAttribute("action", "create");
		//統計資料
		model.addAttribute("stat1", stat1());
		model.addAttribute("stat2", stat2());
		return "exam";
	}

	// CRUD

	@RequestMapping(value = "/create")
	public String create(Exam exam,BindingResult result,Model model) {
		
		//把驗證exam物件
		examValidator.validate(exam, result);
		//驗證結果是否有錯誤
		if(result.hasErrors()) {
			model.addAttribute("exams", exams);//給資料呈現使用
			model.addAttribute("examNames", examNames);//給資料呈現使用
			model.addAttribute("examSlots", examSlots);//給資料呈現使用
			model.addAttribute("examPays", examPays);//給資料呈現使用
			model.addAttribute("action", "create");
			//統計資料
			model.addAttribute("stat1", stat1());
			model.addAttribute("stat2", stat2());
			return "exam";
		}
		
		exams.add(exam);//新增
		return "redirect:/mvc/exam/";//重導到首頁
		
	}
	
	@RequestMapping(value = "/get/{id}")
	public String get(@PathVariable("id") String id,Model model) {
		Optional<Exam> optExam= exams.stream().filter(e -> e.getId().equals(id)).findFirst();
		model.addAttribute("exam", optExam.isPresent()?optExam.get():new Exam());//給表單使用
		model.addAttribute("examNames", examNames);//給資料呈現使用
		model.addAttribute("examSlots", examSlots);//給資料呈現使用
		model.addAttribute("examPays", examPays);//給資料呈現使用
		model.addAttribute("exams", exams);//給資料呈現使用
		model.addAttribute("action", "update");
		//統計資料
		model.addAttribute("stat1", stat1());
		model.addAttribute("stat2", stat2());
		return "exam";
	}
	
	@RequestMapping(value = "/update")
	public String update(Exam exam) {
		Optional<Exam> optExam= exams.stream().filter(e -> e.getId().equals(exam.getId())).findFirst();
		if(optExam.isPresent()) {
			//oExam原本的資料
			//表單傳來Exam修改的資料
			Exam oExam=optExam.get();
			oExam.setName(exam.getName());
			oExam.setSlot(exam.getSlot());
			oExam.setPay(exam.getPay());
			oExam.setNote(exam.getNote());
		}
		return "redirect:/mvc/exam/";//重導到首頁
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") String id) {
		/*
		Optional<Exam> optExam= exams.stream().filter(e -> e.getId().equals(id)).findFirst();
		if(optExam.isPresent()) {
			exams.remove(optExam.get());
		}
		*/
		exams.removeIf(e -> e.getId().equals(id));
		return "redirect:/mvc/exam/";//重導到首頁
	}
	//統計資料
	//1.各科考試報名人數
	public Map<String, Long> stat1() {
		return exams.stream().
				collect(Collectors.groupingBy((e -> e.getName()) ,Collectors.counting()));

	}
	//統計資料
	//考試付款狀況
	public Map<String, Long> stat2() {
		return exams.stream().
				collect(Collectors.groupingBy(Exam:: getPay ,Collectors.counting()));

	}
	
}
