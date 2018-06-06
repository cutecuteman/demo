package com.etc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebFilter(urlPatterns= {"/mvc/addArticle.jsp","/mvc/showUsers_page.jsp","/mvc/showUsers.jsp",})
@WebFilter(urlPatterns= {"/font/*"})
public class BackSessionFilter implements Filter {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc) throws IOException, ServletException {
        // TODO Auto-generated method stub
        // 设置请求编码
        req.setCharacterEncoding("utf-8");
        // 设置相应编码
        res.setCharacterEncoding("utf-8");
        System.out.println("doFilter");
        // 判断用户是否登录
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        String requestURI = request.getRequestURI();//判断请求的路径是什么,在过滤
        System.out.println("requestURI:"+requestURI);
        //取出url地址最后一段/后的文字
        String  url=requestURI.substring(requestURI.lastIndexOf("/")+1);
        //System.out.println(requestURI.substring(requestURI.lastIndexOf("/")));
        System.out.println("url:"+url);
        if (null!=session.getAttribute("users") ) {
            fc.doFilter(req, res);
        } else {
            //没有登录,去登陆
            if("login.jsp".equals(url)) {
                fc.doFilter(req, res);
            }else {
                response.sendRedirect("login.jsp");
                /**
                 * response.sendRedirect("login.jsp");
//                response.sendRedirect("/cms1.6/mvc/login.jsp");
//                request.getRequestDispatcher("").forward(request, response);
                 */
            }
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
