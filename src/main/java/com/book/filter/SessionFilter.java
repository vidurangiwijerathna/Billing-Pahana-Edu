package com.book.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter("/*")
public class SessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // No initialization needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String uri = req.getRequestURI();

        // List of paths that do NOT require login
        boolean isPublicPath = uri.endsWith("login.jsp") ||
                uri.endsWith("register.jsp") ||
                uri.endsWith("index.jsp") ||
                uri.endsWith("/") ||
                uri.contains("/auth") ||
                uri.contains("/resources") ||
                uri.contains("css") ||
                uri.contains("js") ||
                uri.contains("images");

        boolean loggedIn = (session != null && session.getAttribute("user") != null);

        if (loggedIn || isPublicPath) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }

    @Override
    public void destroy() {
        // No cleanup needed
    }
}
