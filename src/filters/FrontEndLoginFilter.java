package filters;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FrontEndLoginFilter implements Filter {
	
	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
		Object stp = session.getAttribute("stp");
		if (stp == null) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/front-end/Login.jsp");
			return;
		} else {
			chain.doFilter(request, response);
		}
	}
}
