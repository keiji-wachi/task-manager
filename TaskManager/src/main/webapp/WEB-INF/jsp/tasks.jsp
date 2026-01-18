<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Task" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>タスク一覧</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<%-- Servletで設定したタスク一覧を取得 --%>
<%
  List<Task> tasks = (List<Task>) request.getAttribute("tasks");
%>

<div class="container py-4">
  <div class="d-flex justify-content-between align-items-center mb-3">
    <h1 class="h4 mb-0">タスク一覧</h1>
    <form class="row g-2 mb-3" method="get" action="<%= request.getContextPath() %>/tasks">
  <div class="col-md-6">
    <input type="text" class="form-control" name="q" placeholder="タイトル検索"
           value="<%= request.getParameter("q") != null ? request.getParameter("q") : "" %>">
  </div>

  <div class="col-md-3">
    <%
      String st = request.getParameter("status");
      if (st == null) st = "ALL";
    %>
    <select class="form-select" name="status">
      <option value="ALL" <%= "ALL".equals(st) ? "selected" : "" %>>全て</option>
      <option value="NEW" <%= "NEW".equals(st) ? "selected" : "" %>>未着手</option>
      <option value="IN_PROGRESS" <%= "IN_PROGRESS".equals(st) ? "selected" : "" %>>対応中</option>
      <option value="DONE" <%= "DONE".equals(st) ? "selected" : "" %>>完了</option>
    </select>
  </div>

  <div class="col-md-3 d-grid">
    <button class="btn btn-primary" type="submit">検索</button>
  </div>
</form>
    <div class="d-flex gap-2">
      <a class="btn btn-success" href="<%= request.getContextPath() %>/tasks/new">＋ 新規登録</a>
      <a class="btn btn-outline-secondary" href="<%= request.getContextPath() %>/logout">ログアウト</a>
    </div>
  </div>

  <div class="card shadow-sm">
    <div class="card-body">

      <div class="table-responsive">
        <table class="table table-hover align-middle">
          <thead>
            <tr>
              <th style="width: 70px;">No</th>
              <th>タイトル</th>
              <th style="width: 260px;">ステータス</th>
              <th style="width: 120px;"></th>
            </tr>
          </thead>

          <tbody>
          <%
            if (tasks != null && !tasks.isEmpty()) {
            	int no = 1;
              for (Task task : tasks) {
          %>
            <tr>
              <td><%= no++ %></td>
              <td><%= task.getTitle() %></td>

              <td>
                <form action="<%= request.getContextPath() %>/tasks/update" method="post" class="d-flex gap-2">
                  <input type="hidden" name="id" value="<%= task.getId() %>">

                  <select name="status" class="form-select form-select-sm">
                    <option value="NEW" <%= "NEW".equals(task.getStatus()) ? "selected" : "" %>>未着手</option>
                    <option value="IN_PROGRESS" <%= "IN_PROGRESS".equals(task.getStatus()) ? "selected" : "" %>>対応中</option>
                    <option value="DONE" <%= "DONE".equals(task.getStatus()) ? "selected" : "" %>>完了</option>
                  </select>

                  <button type="submit" class="btn btn-sm btn-primary">更新</button>
                </form>
              </td>

              <td>
                <form action="<%= request.getContextPath() %>/tasks/delete" method="post"
                      onsubmit="return confirm('削除しますか？');">
                  <input type="hidden" name="id" value="<%= task.getId() %>">
                  <button type="submit" class="btn btn-sm btn-danger">削除</button>
                </form>
              </td>
            </tr>
          <%
              }
            } else {
          %>
            <tr>
              <td colspan="4" class="text-muted">タスクがありません</td>
            </tr>
          <%
            }
          %>
          </tbody>
        </table>
      </div>

    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

