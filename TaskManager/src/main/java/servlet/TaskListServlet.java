package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.TaskDao;
import model.Task;

@WebServlet({ "/TaskListServlet", "/tasks" })
public class TaskListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//DBアクセス不可検知
		try {
			
			String searchKeyword = request.getParameter("q");
			String status = request.getParameter("status");

			//TaskDao呼び出し
			TaskDao dao = new TaskDao();
			List<Task> tasks = dao.search(searchKeyword, status);

			//取得したタスク一覧をリクエストスコープに格納してJSPへ
			request.setAttribute("tasks", tasks);
			request.getRequestDispatcher("/WEB-INF/jsp/tasks.jsp").forward(request, response);
			
		//アクセス失敗
		} catch (Exception e) {
			  request.setAttribute("errorMessage", "タスク一覧の取得に失敗しました。");
			  request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
			}

	}

}
