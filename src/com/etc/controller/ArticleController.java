package com.etc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.etc.entity.Article;
import com.etc.service.ArticleSerivce;
import com.etc.service.impl.ArticleSerivceImpl;

/**
 * Servlet implementation class ArticleController
 */
@WebServlet("/ArticleController")
public class ArticleController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    // ����Service����
    ArticleSerivce as = new ArticleSerivceImpl();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleController() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void query(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // ���÷���
        List<Article> list = as.getArticles();

        // 这里要进行页面的跳转,jsp去,在jsp显示数据;
        // 需要传递数据过去给jsp文件
        // 传递数据最常见的方法request
        // 设置属性 request对象的属性
        // request.setAttribute(key, value);

        // 到了下一个页面就可以通过一个对应鹅request.getAttribute("key");
        request.setAttribute("list", list);
        // forward 转发
        request.getRequestDispatcher("mvc/showArticles-jstl.jsp").forward(request, response);
    }

    protected void queryLike(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 模糊查询:
        // 获取表单传递过来的参数
        String articleLike = request.getParameter("articleLike");
        List<Article> list = as.getArticlesLike(articleLike);
        // 到了下一个页面就可以通过一个对应鹅request.getAttribute("key");
        request.setAttribute("list", list);
        // 将模糊查询的关键字再传递回来给jsp和页面
        request.setAttribute("articleLike", articleLike);
        // forward 转发
        request.getRequestDispatcher("mvc/showArticles-jstl.jsp").forward(request, response);
    }

    protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取用户传递过来的参数 articleId
        String articleId = request.getParameter("articleId");

        boolean flag = as.delArticle(Integer.parseInt(articleId));

        // 可以根据flag的值提示用户是否删除成功
        // 页面跳转
        request.getRequestDispatcher("ArticleController?op=query").forward(request, response);
    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // request.getParameter("articleTitle") 括号内的参数为表单元素名字;
        // 返回值是一个字符串类型
        // 文章的标题
        String articleTitle = request.getParameter("articleTitle");
        // 文章的内容
        String articleContent = request.getParameter("articleContent");
        // 文章的作者
        String articleAuthor = request.getParameter("articleAuthor");

        Article article = new Article(articleTitle, articleContent, articleAuthor);

        // 调用方法
        boolean flag = as.addArticle(article);

        // forward 转发
        // ArticleController?op=query ?分隔符 key=value 参数=值
        // 转发到servlet 而不是jsp
        request.getRequestDispatcher("ArticleController?op=query").forward(request, response);
    }

    protected void frontQueryLike(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 前台界面的数据展示
        // 获取表单传递过来的参数
        String articleLike = request.getParameter("articleLike");
        if (null == articleLike) {
            System.out.println("article == null");
            articleLike = "";
        }
        List<Article> list = as.getTopArticlesLike(articleLike);

        // 到了下一个页面就可以通过一个对应鹅request.getAttribute("key");
        request.setAttribute("list", list);
        // 到了下一个页面就可以通过一个对应鹅request.getAttribute("key");
        System.out.println("list :" + list.size());

        // 将模糊查询的关键字再传递回来给jsp和页面
        request.setAttribute("articleLike", articleLike);
        // forward 转发
        request.getRequestDispatcher("front/index.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置请求的编码 ,位置应该在request.getParameter之前
        request.setCharacterEncoding("utf-8");
        // TODO Auto-generated method stub
        // 参数名 op 值如果等于 query才做查询
        String op = request.getParameter("op");

        if ("frontQueryLike".equals(op)) {
            frontQueryLike(request, response);
        } else {
            // 加入session的判断
            HttpSession session = request.getSession();
            Object obj = session.getAttribute("users");
            if (null != obj) {
                // query 是否是管理员操作?
                if ("query".equals(op)) {
                    // 调用方法
                    query(request, response);
                }
                // 管理员
                else if ("add".equals(op)) {
                    add(request, response);
                } else if ("del".equals(op)) {
                    del(request, response);
                } else if ("queryLike".equals(op)) {
                    queryLike(request, response);
                }
            } else {
                // 没有登录 跳转到登录界面去
                response.sendRedirect("mvc/login.jsp");

            }
        }
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
