package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	
	//文字化け防止
	  request.setCharacterEncoding("UTF-8");

	  //入力フォームの取得
	  String userId = request.getParameter("userId");
	  String password = request.getParameter("password");

	 //入力値判定
	  boolean ok = "admin".equals(userId) && "pass".equals(password);

	  if (ok) {
		  
		//成功:ログイン状態をサーバーに保存
	    request.getSession().setAttribute("loginUser", userId);
	    
	    //URLを/tasksに変え画面遷移
	    response.sendRedirect(request.getContextPath() + "/tasks");
	    
	  } else {
		 //失敗:エラーメッセージ設定
	    request.setAttribute("error", "IDまたはパスワードが違います");
	    //設定したエラーメッセージを持って、forwardで再画面表示
	    request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	  }
	}

}
