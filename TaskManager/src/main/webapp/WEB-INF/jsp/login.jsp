<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <title>ログイン</title>
</head>
<body class="bg-light">
  <div class="container py-5" style="max-width: 520px;">
    <div class="card shadow-sm">
      <div class="card-body p-4">
        <h1 class="h4 mb-4">ログイン</h1>
        <%-- サーバー側で設定されたエラーメッセージがあれば表示 --%>
        <%
          String error = (String) request.getAttribute("error");
          if (error != null) {
        %>
          <div class="alert alert-danger" role="alert"><%= error %></div>
        <%
          }
        %>
		<%-- アプリの配置パスを自動取得 --%>
        <form action="<%= request.getContextPath() %>/login" method="post">
          <div class="mb-3">
            <label class="form-label">ID</label>
            <%-- required属性：ブラウザ側での必須入力チェック --%>
            <input type="text" class="form-control" name="userId" required>
          </div>

          <div class="mb-3">
            <label class="form-label">パスワード</label>
            <input type="password" class="form-control" name="password" required>
          </div>

          <button type="submit" class="btn btn-primary w-100">ログイン</button>
        </form>
      </div>
    </div>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
