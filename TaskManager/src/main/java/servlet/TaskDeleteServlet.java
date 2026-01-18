package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.TaskDao;

@WebServlet("/tasks/delete")
public class TaskDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		request.setCharacterEncoding("UTF-8");

		//該当idを削除、idが存在しない場合何もしない
	    String idStr = request.getParameter("id");
	    if (idStr != null) {
	      int id = Integer.parseInt(idStr);
	      new TaskDao().deleteById(id);
	    }
	    response.sendRedirect(request.getContextPath() + "/tasks");
	    
		} catch (Exception e) {
			  request.setAttribute("errorMessage", "タスクの削除に失敗しました。");
			  request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		}
	}

}
