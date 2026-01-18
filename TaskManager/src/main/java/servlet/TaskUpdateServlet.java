package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.TaskDao;

@WebServlet("/tasks/update")
public class TaskUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		 request.setCharacterEncoding("UTF-8");
		    int id = Integer.parseInt(request.getParameter("id"));
		    String status = request.getParameter("status");

		    //ステータス更新
		    new TaskDao().updateStatus(id, status);
		    
		    //PRGパターン：更新後は一覧へリダイレクト（二重送信防止
		    response.sendRedirect(request.getContextPath() + "/tasks");
		} catch (Exception e) {
			  request.setAttribute("errorMessage", "ステータスの更新に失敗しました。");
			  request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
			}
		  }
	}

