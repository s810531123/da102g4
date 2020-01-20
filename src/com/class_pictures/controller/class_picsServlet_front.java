package com.class_pictures.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.class_pictures.model.Class_picturesService;
import com.class_pictures.model.Class_picturesVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class class_picsServlet_front extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException{
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
		
			System.out.println("3");
			try {
				/**************1.接收請求參數，處理錯誤格式*****************/
					
				String cs_pic_num = req.getParameter("cs_pic_num");
				String pic_cs = req.getParameter("pic_cs");	
				
				
				if(cs_pic_num == null) {
					
					if(pic_cs == null || pic_cs.trim().length() == 0) {
						errorMsgs.add("照片種類:請勿空白");
					}
					
					
					Class_picturesService cpsvc = new Class_picturesService(); 				
					Class_picturesVO cpvo = new Class_picturesVO();
					cpvo = 	cpsvc.getByPic_CS(pic_cs);
					req.setAttribute("Cs_num", cpvo.getCs_num());;
					req.setAttribute("Cs_pic_um_", cpvo.getCs_pic_num());
					req.setAttribute("Pic", cpvo.getPic());
					req.setAttribute("Ul_date", cpvo.getUl_date());
					req.setAttribute("Pic_cs", cpvo.getPic_cs());
					req.setAttribute("class_picturesVO", cpvo);
					
				}
				
				
				else if(pic_cs == null) {
					
					if(cs_pic_num == null ||cs_pic_num.trim().length() == 0) {
						errorMsgs.add("照片編號:請勿空白");
					}
					
					Class_picturesService cpsvc = new Class_picturesService();
					Class_picturesVO cpvo = new Class_picturesVO();
					cpvo = cpsvc.getOneClass_pictures(new Integer(cs_pic_num.trim()));
					req.setAttribute("Cs_num", cpvo.getCs_num());
					req.setAttribute("Cs_pic_num",cpvo.getCs_pic_num());
					req.setAttribute("Pic",cpvo.getPic());
					req.setAttribute("Pic_cs",cpvo.getPic_cs());
					req.setAttribute("Ul_date",cpvo.getUl_date());
					req.setAttribute("class_picturesVO", cpvo);
 				}

				if(!errorMsgs.isEmpty()) {

					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/class_pics/search_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/**************3.查詢完成後轉交**************************/
				String url = "/front-end/class_pics/search_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/**************4.處理其他錯誤****************************/
			}catch(Exception e) {
				errorMsgs.add("無法取得資料");
				RequestDispatcher failureView  = req.getRequestDispatcher("/front-end/class_pics/search_page.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer cs_pic_num = null;
			try {
				/*************1.接收請求參數，處理錯誤格式*******************/
				try {
					cs_pic_num = new Integer(req.getParameter("cs_pic_num").trim());
				}catch(NumberFormatException e) {
					errorMsgs.add("請輸入正確照片編號");
				}catch(NullPointerException ee) {
					errorMsgs.add("照片編號請勿空白");
				}
				/*************2.開始修改資料*****************************/
				Class_picturesService class_picturesSvc = new Class_picturesService();
				Class_picturesVO class_picturesVO = class_picturesSvc.getOneClass_pictures(cs_pic_num);
				/*************3.修改後轉交******************************/
				req.setAttribute("class_picturesVO", class_picturesVO);
				String url ="/front-end/class_pics/search_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************4.處理其他錯誤****************************/
			}catch(Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/class_pics/search_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer cs_pic_num = null;
			
			try {
				/*************1.接收請求參數，處理錯誤格式******************/
				
				String cs_num = req.getParameter("cs_num").trim();
				if(cs_num == null || cs_num.trim().length() == 0) {
					errorMsgs.add("班級編號請勿空白");
				}
				
				String pic_cs = req.getParameter("pic_cs").trim();
				if(pic_cs == null || cs_num.trim().length() == 0) {
					errorMsgs.add("照片類別請勿空白");
				}
				
				Part picSrc = req.getPart("pic");
				InputStream in = picSrc.getInputStream();
				byte[] pic = get_pics(in);
				
				
				java.sql.Timestamp ul_date = null;
				String ul_date1 = req.getParameter("ul_date");
				try {
					ul_date1 += ":00";
					ul_date = java.sql.Timestamp.valueOf(ul_date1);
				}catch(IllegalArgumentException e) {
					errorMsgs.add("請輸入照片時間");
				}
				
				Class_picturesVO class_picturesVO = new Class_picturesVO();
				class_picturesVO.setCs_pic_num(cs_pic_num);
				class_picturesVO.setCs_num(cs_num);
				class_picturesVO.setPic_cs(pic_cs);
				class_picturesVO.setPic(pic);
				class_picturesVO.setUl_date(ul_date);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("class_picturesVO", class_picturesVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/class_pics/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/****************2.開始修改資料********************/
				Class_picturesService class_picturesSvc = new Class_picturesService();
				class_picturesVO = class_picturesSvc.updateClass_pictures(cs_pic_num, cs_num, ul_date, pic, pic_cs);
				/****************3.修改完成後轉交******************/
				req.setAttribute("class_picturesVO", class_picturesVO);
				String url = "/front-end/class_pics/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/****************4.處理其他資料********************/
			}catch(Exception e) {
				errorMsgs.add("修改資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/class_pics/select_page.jsp");
				failureView.forward(req, res);
			}
		
		}
		
		
		if("insert".equals(action)) {
			System.out.println("insert");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/****************1.接收請求參數，處理錯誤格式*********/
				String cs_num = req.getParameter("cs_num");
//				if(cs_num == null || cs_num.trim().length() == 0) {
//					errorMsgs.add("班級編號請勿空白");
//				}
				
				
				String pic_cs = req.getParameter("pic_cs");
				if(pic_cs == null || pic_cs.trim().length() == 0) {
					errorMsgs.add("照片類別請勿空白");
				}
				
				
				Part picSrc = req.getPart("pic");
				InputStream in = picSrc.getInputStream();
				byte[] pic = get_pics(in);
				
				
				java.sql.Timestamp ul_date = null;
				String ul_date1 = req.getParameter("ul_date");
				try {
					ul_date1 += ":00";
					ul_date = java.sql.Timestamp.valueOf(ul_date1);
				}catch(IllegalArgumentException e) {
					errorMsgs.add("請輸入照片時間");
				}
				
				Class_picturesVO class_picturesVO = new Class_picturesVO();
				class_picturesVO.setCs_num(cs_num);
				class_picturesVO.setPic_cs(pic_cs);
				class_picturesVO.setPic(pic);
				class_picturesVO.setUl_date(ul_date);
				
				if(!errorMsgs.isEmpty()) {
					System.out.println("err");
					
					req.setAttribute("errInsert", "errInsert");
					req.setAttribute("class_picturesVO", class_picturesVO );
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/class_pics/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/***********2.開始新增資料****************************/
				Class_picturesService class_picturesSvc = new Class_picturesService();
				class_picturesVO = class_picturesSvc.addClass_pictures(cs_num, ul_date, pic, pic_cs);
				/***********3.新增完成後轉交**************************/
				String url = "/front-end/class_pics/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/***********4.處理其他錯誤***************************/
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/class_pics/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/**********1.接收請求參數********************/
				System.out.println("pic_num : " + req.getParameter("cs_pic_num"));
				Integer cs_pic_num = new Integer(req.getParameter("cs_pic_num"));
				System.out.println(cs_pic_num);
				/**********2.開始刪除資料********************/
				Class_picturesService class_picturesSvc = new Class_picturesService();
				class_picturesSvc.deleteClass_pictures(cs_pic_num);
				/**********3.刪除完成後轉交*******************/
				String url = "/front-end/class_pics/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/**********4.處理其他錯誤********************/
			}catch(Exception e) {
				System.out.println("err");
				errorMsgs.add("刪除資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/class_pics/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
	
	public static byte[] get_pics(InputStream in) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] data = new byte[8192];
		int i;
		while((i = in.read(data,0,data.length))!= -1) {
			bos.write(data,0,i);
	}
		bos.close();
		in.close();
		return bos.toByteArray();
	}
}
