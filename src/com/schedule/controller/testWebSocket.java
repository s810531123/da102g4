package com.schedule.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint("/webTest")
public class testWebSocket extends HttpServlet {
	
	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/plain");
		
		
	}
	
	
	
	
	
	
	@OnOpen
	public void onOpen(Session session) throws IOException{
		connectedSessions.add(session);
	}
	
	@OnMessage
	public void onMessage(Session userSession, String message) throws IOException{
		for (Session session : connectedSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(message);
		}
	}
	@OnClose
	public void onClose(Session userSession, CloseReason reason) throws IOException{
		connectedSessions.remove(userSession);
	}
	@OnError
	public void onError(Session userSession, Throwable e) throws IOException{
		System.out.println("Error: " + e.toString());
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
