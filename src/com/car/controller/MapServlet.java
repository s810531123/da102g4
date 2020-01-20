package com.car.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@ServerEndpoint("/map")
public class MapServlet extends HttpServlet {
	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		
		
	}
	
	
	public double ran() {
		
		double x = Math.random() * 10;
		if(x > 5 ) {
			double random = Math.random() * 0.0001;
			return random;
		}else {
			double random_ = Math.random() * -0.0001;
			return random_;
		}
	}
	
	@OnOpen
	public void onOpen(Session userSession) throws IOException {
		connectedSessions.add(userSession);
		System.out.println("open");
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		System.out.println(message);
		
		/***手機輸入位置****/
//		Gson gson = new Gson();
//		JsonObject  jOb = gson.fromJson(message, JsonObject.class);
//		String strLat = jOb.get("lat").getAsString();
//		String strLng = jOb.get("lng").getAsString();
//		
//		try {
//			int lat = new Integer(strLat);
//			int lng = new Integer(strLng);
//			
//			jOb = new JsonObject();
//			jOb.addProperty("lat", lat);
//			jOb.addProperty("lng", lng);
//			
//			for (Session session : connectedSessions) {
//				if (session.isOpen())
//					session.getAsyncRemote().sendText(jOb.toString());
//			}
//		}catch(NumberFormatException e) {
//			System.out.println(e.getMessage());
//		}
		
		/*******************/
		
		/***抓取手機GPS位置***/
		Gson gson = new Gson();
		JsonObject  jOb = gson.fromJson(message, JsonObject.class);
		Double lat = jOb.get("lat").getAsDouble();
		Double lng = jOb.get("lng").getAsDouble();
		
		DecimalFormat df = new DecimalFormat("##.00000");
		lat = Double.parseDouble(df.format(lat));
		lng = Double.parseDouble(df.format(lng));
				
		jOb = new JsonObject();
		jOb.addProperty("lat", lat);
		jOb.addProperty("lng", lng);
		for (Session session : connectedSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(jOb.toString());
		}
		/*******************/
		
		/****假位置 繞一圈 下下策****/
//		DecimalFormat df = new DecimalFormat("##.00000");
//		Double lat = 24.9698051;
//		Double lng = 121.1912047;
//		int east = 0;
//		int south = 0;
//		int west = 0;
//		int north = 0;
//		
//		
//		
//		while(east < 86) {
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			
//			east++;
////			lat += ran();
//			lng += 0.00005;
//			
//			lat = Double.parseDouble(df.format(lat));
//			lng = Double.parseDouble(df.format(lng));
//			
//			String str = "{\"lat\": " + lat + ", \"lng\": " + lng + "}"; 
//			for (Session session : connectedSessions) {
//				if (session.isOpen())
//					session.getAsyncRemote().sendText(str);
//			}
//		}
//		
//		while(south < 62) {
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			
//			south++;
//			lat -= 0.00005;
////			lng += 0.00005;
//			
//			lat = Double.parseDouble(df.format(lat));
//			lng = Double.parseDouble(df.format(lng));
//			
//			String str = "{\"lat\": " + lat + ", \"lng\": " + lng + "}"; 
//			for (Session session : connectedSessions) {
//				if (session.isOpen())
//					session.getAsyncRemote().sendText(str);
//			}
//		}
//		
//		while(west < 87) {
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			
//			west++;
////			lat += ran();
//			lng -= 0.00005;
//			
//			lat = Double.parseDouble(df.format(lat));
//			lng = Double.parseDouble(df.format(lng));
//			
//			String str = "{\"lat\": " + lat + ", \"lng\": " + lng + "}"; 
//			for (Session session : connectedSessions) {
//				if (session.isOpen())
//					session.getAsyncRemote().sendText(str);
//			}
//		}
//		
//		while(north < 62) {
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			
//			north++;
//			lat += 0.00005;
////			lng += 0.00005;
//			
//			lat = Double.parseDouble(df.format(lat));
//			lng = Double.parseDouble(df.format(lng));
//			
//			String str = "{\"lat\": " + lat + ", \"lng\": " + lng + "}"; 
//			for (Session session : connectedSessions) {
//				if (session.isOpen())
//					session.getAsyncRemote().sendText(str);
//			}
//		}
		
		
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		connectedSessions.remove(userSession);
		System.out.println("onclose");
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}
	
}
