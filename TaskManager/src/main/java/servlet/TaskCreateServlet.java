package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.TaskDao;

@WebServlet("/tasks/create")
public class TaskCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		 request.setCharacterEncoding("UTF-8");

		    String title = request.getParameter("title");
		    String status = request.getParameter("status");

		    //空タイトルのデータ登録検知
		    if (title == null || title.trim().isEmpty()) {
		      response.sendRedirect(request.getContextPath() + "/tasks/new");
		      return;
		    }

		    TaskDao dao = new TaskDao();
		    dao.insert(title.trim(), status);
		    
		    response.sendRedirect(request.getContextPath() + "/tasks");
		    
		} catch (Exception e) {
			  request.setAttribute("errorMessage", "新規登録に失敗しました。");
			  request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
			}
		  }

}
