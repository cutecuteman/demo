package com.etc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.etc.entity.Users;
import com.etc.service.UsersSerivce;
import com.etc.service.impl.UsersSerivceImpl;
import com.etc.util.DBUtil;
import com.etc.util.PageData;
import com.google.gson.Gson;

/**
 * Servlet implementation class ArticleController
 */
@WebServlet("/UsersController")
public class UsersController extends HttpServlet {

    private static final long serialVersionUID = 1L;
 // 创建Service对象
    UsersSerivce us = new UsersSerivceImpl();

    protected void query(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // ���÷���
        List<Users> list = us.getUsers();

        // ����Ҫ����ҳ�����ת,jspȥ,��jsp��ʾ����;
        // ��Ҫ�������ݹ�ȥ��jsp�ļ�
        // ������������ķ���request
        // �������� request���������
        // request.setAttribute(key, value);

        // ������һ��ҳ��Ϳ���ͨ��һ����Ӧ��request.getAttribute("key");
        request.setAttribute("list", list);

        // forward ת��
        request.getRequestDispatcher("mvc/showUsers.jsp").forward(request, response);
    }

    protected void frontExit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().removeAttribute("frontusers");
        response.sendRedirect("front/index.jsp");
    }

    protected void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // �õ��û�������û���������
        String userName = request.getParameter("userName");
        String userPwd = request.getParameter("userPwd");
        // ����userService�ķ���
        Users u = us.login(userName, userPwd);
        
       
        
        if (null != u) {
            // �û�����������ȷ
            // �ж���״̬ userState == 1 �� �ȼ� userLevel == 2����Ա
            if (u.getUserState() == 1 && u.getUserLevel() == 2) {
                // ���û���¼����Ϣ�洢��session��
                HttpSession session = request.getSession();
                // ���û���Ϣ�洢��session������
                session.setAttribute("users", u);
                // ��¼�ɹ� ��ת��̨�Ľ���
                // http://localhost:7777/cms1.1/ArticleController?op=query
                // 1 ת��
                request.getRequestDispatcher("ArticleController?op=query").forward(request, response);
                // 2�ض���
            }
            // �ж���״̬ userState == 1 �� �ȼ� userLevel == 1 ��ͨ�û�
            else if (u.getUserState() == 1 && u.getUserLevel() == 1) {
                HttpSession session = request.getSession();
                // ���û���Ϣ�洢��session������
                session.setAttribute("frontusers", u);

                System.out.println("u level = 1");
                // ��ת��ǰ̨ front/index.jspҳ��
                request.getRequestDispatcher("front/index.jsp").forward(request, response);
                // ������� ���� ��ͨ�û�......(Ԥ��) ��ͨ�û�����ת��ǰ̨ȥ
            }
        } else {
            // �û�������������� ,��ת����¼ҳ��ȥ
            // 1 ת��
            // 2�ض���
            response.sendRedirect("mvc/login.jsp");
        }
        
        
    }
    protected void queryUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
        PrintWriter out=response.getWriter();
        boolean userByName = us.getUserByName(userName);
//        if("小白".equals(userName)) {
          if(userByName) {
            out.print("存在用户名");
        }else {
            out.print("不存在用户名");
        }
       out.close();
    }
    protected void queryByPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int page = 1;
        int pageSize = 5;
        String userNameLike = "";
        if (page < 1) {
            page = 1;
        }
        /*
         * else if (page>) {
         * 
         * }
         */
        // ��ȡҳ�洫�ݹ�����page[ҳ��]����
        if (null != request.getParameter("page")) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        // ��ȡҳ�洫�ݹ�����pageSize[ÿҳ��ʾ�ļ�¼��]����
        if (null != request.getParameter("pageSize")) {
            pageSize = Integer.parseInt(request.getParameter("pageSize"));
        }
        if (null != request.getParameter("userNameLike")) {
            userNameLike = request.getParameter("userNameLike");
            // 处理get乱码方式一
            // userNameLike = new
            // String(userNameLike.getBytes("iso-8859-1"),"utf-8");
            System.out.println("userNameLike" + userNameLike);
        }

        // ��ҳ��ѯ
        PageData<Users> pd = us.getUserByPage(page, pageSize, userNameLike);

        // �洢��Ϣ
        request.setAttribute("pd", pd);
        request.setAttribute("userNameLike", userNameLike);

        // forward ת��
        request.getRequestDispatcher("mvc/showUsers_page.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // servlet��session ��������һ��HttpSession
        // ��������ı��� ,λ��Ӧ����request.getParameter֮ǰ
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        // TODO Auto-generated method stub
        // ������ op ֵ������� query������ѯ
        String op = request.getParameter("op");
        if ("query".equals(op)) {
            
            query(request, response);
        } else if ("add".equals(op)) {

        } else if ("login".equals(op)) {
            login(request, response);
        } else if ("frontExit".equals(op)) {
            // ����ͨ�û���session�е���Ϣɾ��
            frontExit(request, response);
        } else if ("queryByPage".equals(op)) {
            queryByPage(request, response);
        }
         else if ("queryUser".equals(op)) {
             queryUser(request, response);
        }
        else if ("echarts".equals(op)) {
            echarts(request, response);
       }
    }
    /**
     * 图表
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void echarts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //省略我们的Entity,Dao,Service,直接到Controller;
        List<Users> list=(List<Users>)DBUtil.select("select userId,userName,userState from users", Users.class);
        Gson gson=new Gson();
        String gsonStr = gson.toJson(list);
        response.getWriter().print(gsonStr);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
