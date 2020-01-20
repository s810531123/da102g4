package com.class_pictures.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;

public class do_download extends HttpServlet {

private static final long serialVersionUID = 1L;

public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("jpg/png/gif");
	javax.servlet.ServletOutputStream out = response.getOutputStream();
	String filepath=request.getRealPath("/") + "uploadfile/";
	String filename=new String(request.getParameter("filename").getBytes("ISO8859_1"),"GB2312").toString();
	System.out.println("DownloadFile filepath:" + filepath);
	System.out.println("DownloadFile filename:" + filename);
	java.io.File file = new java.io.File(filepath + filename);
	if (!file.exists()) {
		System.out.println(file.getAbsolutePath() + " 檔案不存在!");
		return;
	}
	// 讀取檔案流
	java.io.FileInputStream fileInputStream = new java.io.FileInputStream(file);
	// 下載檔案
	// 設定響應頭和下載儲存的檔名
	if (filename != null && filename.length() > 0) {
		response.setContentType("application/x-msdownload");
		response.setHeader("Content-Disposition", "attachment; filename=" + new String(filename.getBytes("gb2312"),"iso8859-1") + "");
		if (fileInputStream != null) {
			int filelen = fileInputStream.available();
			//檔案太大時記憶體不能一次讀出,要迴圈
			byte a[] = new byte[filelen];
			fileInputStream.read(a);
			out.write(a);
		}
		fileInputStream.close();
		out.close();
	}
}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC -//W3C//DTD HTML 4.01 Transitional//EN>");
		out.println("<HTML>");
		out.println(" <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println(" <BODY>");
		out.print(" This is ");
		out.print(this.getClass().getName());
		out.println(", using the POST method");
		out.println(" </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
}

