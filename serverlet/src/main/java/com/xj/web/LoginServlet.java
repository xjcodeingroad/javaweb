package com.xj.web;

import com.xj.mapper.UserMapper;
import com.xj.pojo.User;
import com.xj.until.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginServelet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取request对象
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //调用mybaits
        // 获取sqlsessionfactory 对象
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
        //获取sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取mapper对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //调用方法
        User user = userMapper.select(username, password);
        //释放资源
        sqlSession.close();


        //response使用
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        if(user!=null){
            writer.write("登录成功");
        }
        else{
            writer.write("登陆失败");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);

    }
}
