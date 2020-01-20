package android.alt.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import android.alt.model.Alt;
import android.alt.model.AltDAO;
import android.alt.model.AltDAO_interface;

public class AltServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset = UTF-8";

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input:" + jsonIn);

		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		AltDAO_interface altDAO = new AltDAO();
		String action = jsonObject.get("action").getAsString();

		if ("insert".equals(action)) {
			Alt alt = gson.fromJson(jsonObject.get("alt").getAsString(), Alt.class);
			
			List<Alt> altList = altDAO.getAltBySt_num_today(alt.getSt_num());
			
			
		
			if (altList == null || altList.size() == 0) {
				writeText(res, String.valueOf(altDAO.insert(alt)));
				altDAO.insertASC(alt);
			} else {
				writeText(res, String.valueOf(false));
			}

		} else if ("getAll".equals(action)) {
			List<Alt> altList = altDAO.getAll();

			writeText(res, gson.toJson(altList));

		} else if ("getAltBySt_num".equals(action)) {
			String st_num = jsonObject.get("st_num").getAsString();
			List<Alt> altList = altDAO.getAltBySt_num(st_num);
			writeText(res, gson.toJson(altList));			
		} else if ("updateARR_LEA".equals(action)) {
			String st_num = jsonObject.get("st_num").getAsString();
			List<Alt> altList = altDAO.getAltBySt_num_today(st_num);
			 System.out.println(altList.get(0).getAlt_lea() == null);
			if (altList != null && altList.size() != 0 && altList.get(0).getAlt_lea() == null) {
       
				writeText(res, String.valueOf(altDAO.updateARR_LEA(st_num)));
			} else {
				writeText(res, String.valueOf(false));
			}

		} else if ("getAltBySt_num_today".equals(action)) {
			String st_num = jsonObject.get("st_num").getAsString();
			List<Alt> altList = altDAO.getAltBySt_num_today(st_num);
			writeText(res, gson.toJson(altList));
		} else {
			writeText(res, "");
		}

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
		System.out.println("outText: " + outText);
	}

}
