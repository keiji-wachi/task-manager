<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>エラー</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container py-5" style="max-width: 720px;">
  <div class="card shadow-sm">
    <div class="card-body p-4">
      <h1 class="h4 mb-3 text-danger">エラーが発生しました</h1>

      <p class="mb-4">
      <%-- 各Servletで設定した errorMessage があれば表示。無ければ汎用メッセージを表示 --%>
        <%= request.getAttribute("errorMessage") != null
            ? request.getAttribute("errorMessage")
            : "処理中にエラーが発生しました。お手数ですが再度お試しください。" %>
      </p>

      <div class="d-flex gap-2">
        <a class="btn btn-primary" href="<%= request.getContextPath() %>/tasks">タスク一覧へ</a>
        <a class="btn btn-outline-secondary" href="<%= request.getContextPath() %>/login">ログインへ</a>
      </div>
    </div>
  </div>
</div>
</body>
</html>
