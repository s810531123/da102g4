package com.paymentform.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.descriptor.web.SessionConfig;

import com.afterschoolclass.model.ASC_Service;
import com.paymentform.model.PMF_Service;
import com.paymentform.model.PMF_VO;
import com.paymentproject.model.PMP_Service;
import com.paymentproject.model.PMP_VO;
import com.student.model.StudentVO;
import com.sun.xml.internal.ws.api.policy.PolicyResolver.ServerContext;

public class PMF_Servlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	public void sendMail(String to, String subject, String messageText) {
		
		   try {
			   // 設定使用SSL連線至 Gmail smtp Server
			   Properties props = new Properties();
			   props.put("mail.smtp.host", "smtp.gmail.com");
			   props.put("mail.smtp.socketFactory.port", "465");
			   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			   props.put("mail.smtp.auth", "true");
			   props.put("mail.smtp.port", "465");

	       // ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
	       // ●須將myGmail的【安全性較低的應用程式存取權】打開
		     final String myGmail = "ixlogic.wu@gmail.com";
		     final String myGmail_password = "BBB45678";
			   Session session = Session.getInstance(props, new Authenticator() {
				   protected PasswordAuthentication getPasswordAuthentication() {
					   return new PasswordAuthentication(myGmail, myGmail_password);
				   }
			   });

			   Message message = new MimeMessage(session);
			   message.setFrom(new InternetAddress(myGmail));
			   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
			  
			   //設定信中的主旨  
			   message.setSubject(subject);
			   //設定信中的內容 
			   message.setText(messageText);

			   Transport.send(message);
			   System.out.println("傳送成功!");
	     }catch (MessagingException e){
		     System.out.println("傳送失敗!");
		     e.printStackTrace();
	     }
	   }
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");

		// 新增
		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();		

			req.setAttribute("errorMsgs", errorMsgs);

			Date today = new Date();

			String pml_num = req.getParameter("pml_num");
			
			String choose = req.getParameter("choose");		

			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

			String date = req.getParameter("date");				

			String todaystr = ft.format(today);
			
			if (choose == null || choose.trim().length() == 0 ) {

				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/payment/PMF_ADD.jsp");

				errorMsgs.add("繳費對象不得為空");
				
				failureView.forward(req, res);	
				
				return;
		
			}
			else if (pml_num == null || pml_num.trim().length() == 0 ) {

				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/payment/PMP.jsp");

				errorMsgs.add("繳費單項目不得為空");
				
				failureView.forward(req, res);		
				
				return;
		
			}

			else if (date == null || date.trim().length() == 0 ) {

				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/payment/PMF_ADD.jsp");

				errorMsgs.add("日期不得為空");
				
				failureView.forward(req, res);
				
				return;
				
			}

			int today_yyyy = Integer.parseInt(todaystr.substring(0, 4));

			int today_mm = Integer.parseInt(todaystr.substring(5, 7));

			int today_dd = Integer.parseInt(todaystr.substring(8, 10));

			int date_yyyy = Integer.parseInt(date.substring(0, 4));

			int date_mm = Integer.parseInt(date.substring(5, 7));

			int date_dd = Integer.parseInt(date.substring(8, 10));			

			if (today_yyyy > date_yyyy) {

				errorMsgs.add("日期不得寫入今日以前");

			} else if (today_yyyy == date_yyyy) {

				if (today_mm > date_mm) {
					errorMsgs.add("日期不得寫入今日以前");
				} else if (today_mm == date_mm) {

					if (today_dd > date_dd) {
						errorMsgs.add("日期不得寫入今日以前");
					}
				}

			}

			if (!errorMsgs.isEmpty()) {

				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/payment/PMF_ADD.jsp");

				failureView.forward(req, res);

				return;
			}	
			
			//System.out.println("choose="+choose+"date="+date+"pml_num="+pml_num);

			PMP_Service dao = new PMP_Service();
			
			req.setAttribute("success","新增繳費:  "+ choose+"  在"+ date+"  繳交"+dao.getOneDept(Integer.parseInt(pml_num)).getPml_item() +"  成功");
			
			getServletContext().setAttribute("pml_num", pml_num);

			getServletContext().setAttribute("date", date);
			
			getServletContext().setAttribute("choose", choose);

			RequestDispatcher failureView = req.getRequestDispatcher("/back-end/payment/PMF_ADD.jsp");

			failureView.forward(req, res);

			return;

		}
		else if ("select".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String st_num = req.getParameter("St_num");

			if (st_num == null || st_num.trim().length() == 0) {
				errorMsgs.add("查詢的學號不得為空");
			} else {
				PMF_Service dao = new PMF_Service();
				List<PMF_VO> list = dao.getOnePMF(st_num);
				req.setAttribute("st_num", list);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/payment/PMF_Home.jsp");
				failureView.forward(req, res);
				return;
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/payment/PMF_Home.jsp");
				failureView.forward(req, res);
				return;
			}

		}
		else if ("select3".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String st_num = req.getParameter("St_num");

			if (st_num == null || st_num.trim().length() == 0) {
				errorMsgs.add("查詢的學號不得為空");
			} else {
				PMF_Service dao = new PMF_Service();
				List<PMF_VO> list = dao.getOnePMF(st_num);
				req.setAttribute("st_num", list);
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/payment/PMF_front.jsp");
				failureView.forward(req, res);
				return;
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/payment/PMF_front.jsp");
				failureView.forward(req, res);
				return;
			}

		}
		else if ("select2".equals(action)) {		
			
			String pf_num= req.getParameter("pf_num");			
				PMF_Service dao =  new PMF_Service();
				List<PMP_VO> list = dao.getPmlByPfnum(Integer.parseInt(pf_num));
				req.setAttribute("getpmpvo", list);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/payment/PML.jsp");
				failureView.forward(req, res);
		}
else if ("select4".equals(action)) {		
			
			String pf_num= req.getParameter("pf_num");			
				PMF_Service dao =  new PMF_Service();
				List<PMP_VO> list = dao.getPmlByPfnum(Integer.parseInt(pf_num));
				req.setAttribute("getpmpvo", list);
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/payment/PML_front.jsp");
				failureView.forward(req, res);
		}
		
else if ("paying".equals(action)) {		
	
	PMF_Service dao = new PMF_Service();
	
	List<String> errorMsgs = new LinkedList<String>();

	req.setAttribute("errorMsgs", errorMsgs);
	
	String cardnumber = req.getParameter("cardnumber");
	
	String choose = req.getParameter("choose");

	String name1 = req.getParameter("name1");
	
	String name2 = req.getParameter("name2");
	
	
	
	String exampleRadios = req.getParameter("exampleRadios");
	
	if (name1 == null || name1.trim().length() == 0) {
		errorMsgs.add("姓氏不得為空");
	}
	
	if (name2 == null || name2.trim().length() == 0) {
		errorMsgs.add("名字不得為空");
	}
	
	if (cardnumber == null || cardnumber.trim().length() == 0) {
		errorMsgs.add("卡號不得為空");
	}
	
	if (cardnumber == null || cardnumber.trim().length() == 0) {
		errorMsgs.add("卡號不得為空");
	}
	if (choose == null || choose.trim().length() == 0) {
		errorMsgs.add("請選銀行");
	}	
	if (exampleRadios == null || exampleRadios.trim().length() == 0) {
		errorMsgs.add("請選擇繳費方式");
	}
	
	PMF_VO pmfvo = (PMF_VO) dao.GetOnePMF(Integer.parseInt(req.getParameter("pfl_num4")));
	
	req.setAttribute("pfl_num2",req.getParameter("pfl_num4"));
	
	req.setAttribute("pfl_num5", pmfvo.getPml_trail().toString());
	
	if (!errorMsgs.isEmpty()) {

		RequestDispatcher failureView = req.getRequestDispatcher("/front-end/payment/paying.jsp");

		failureView.forward(req, res);

		return;
	}		
	
	dao.updateStatus(Integer.parseInt(req.getParameter("pfl_num4")));
	HttpSession session = req.getSession();
	StudentVO stp = (StudentVO) session.getAttribute("stp");
	
	
		String to = "da102g4@gmail.com";	    
	    String subject = "繳費成功";	    
	    String ch_name = stp.getSt_name();;
	    String passRandom = "111";
	    String messageText = "您好， " + ch_name +"\n"+pmfvo.getMonth().toString().substring(0,7)+"月，總金額:"+pmfvo.getPml_trail()+"已繳清";    
	    sendMail(to, subject, messageText);
	    
	    
	    
		RequestDispatcher failureView = req.getRequestDispatcher("/front-end/payment/PMF_front.jsp");
		failureView.forward(req, res);
}
else if ("paying2".equals(action)) {		
	req.setAttribute("pfl_sum2", req.getParameter("pfl_sum"));
	req.setAttribute("pfl_num2", req.getParameter("pfl_num"));
	
	
	
		RequestDispatcher failureView = req.getRequestDispatcher("/front-end/payment/paying.jsp");
		failureView.forward(req, res);
}

		

	}

}
