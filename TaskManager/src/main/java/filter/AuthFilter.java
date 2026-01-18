package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


//tasks 配下へのアクセス時にログイン状態をチェックする認証フィルター

@WebFilter(urlPatterns = {"/tasks", "/tasks/*"})
public class AuthFilter implements Filter {

  @Override
  public void doFilter(jakarta.servlet.ServletRequest request,
                       jakarta.servlet.ServletResponse response,
                       FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;

    // セッションにログイン情報があるかチェック
    Object loginUser = req.getSession().getAttribute("loginUser");

    // ログインしてなければログイン画面へ
    if (loginUser == null) {
      res.sendRedirect(req.getContextPath() + "/login");
      return;
    }

    // ログインしていれば次へ通す
    chain.doFilter(request, response);
  }
}
