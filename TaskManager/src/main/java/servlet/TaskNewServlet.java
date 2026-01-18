package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/tasks/new")
public class TaskNewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// JSP表示のみのため、本来は例外処理は不要だが、学習用として共通エラーページへ遷移
		try {
		//タスク登録画面へ遷移
	    request.getRequestDispatcher("/WEB-INF/jsp/task_new.jsp")
        .forward(request, response);
	    
		} catch (Exception e) {
			  request.setAttribute("errorMessage", "新規登録に失敗しました。");
			  request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
			}
	}

}
