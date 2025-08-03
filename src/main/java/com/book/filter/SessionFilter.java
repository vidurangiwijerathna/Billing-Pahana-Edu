package com.book.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class SessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String loginURI = req.getContextPath() + "/login.jsp";
        String loginServlet = req.getContextPath() + "/login";
        String registerServlet = req.getContextPath() + "/register";

        boolean loggedIn = req.getSession(false) != null && req.getSession(false).getAttribute("user") != null;
        boolean loginRequest = req.getRequestURI().equals(loginURI) || req.getRequestURI().equals(loginServlet);
        boolean registerRequest = req.getRequestURI().equals(registerServlet);
        boolean resourceRequest = req.getRequestURI().startsWith(req.getContextPath() + "/resources/");

        if (loggedIn || loginRequest || registerRequest || resourceRequest) {
            chain.doFilter(request, response);  // Continue to requested resource
        } else {
            res.sendRedirect(loginURI);  // Redirect to login page if not logged in
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // No initialization needed
    }

    @Override
    public void destroy() {
        // No cleanup needed
    }
}
