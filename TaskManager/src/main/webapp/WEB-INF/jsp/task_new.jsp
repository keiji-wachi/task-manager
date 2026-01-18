<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>タスク登録</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container py-4" style="max-width: 720px;">
  <div class="d-flex justify-content-between align-items-center mb-3">
    <h1 class="h4 mb-0">タスク登録</h1>
    <a class="btn btn-outline-secondary" href="<%= request.getContextPath() %>/tasks">一覧へ戻る</a>
  </div>

  <div class="card shadow-sm">
    <div class="card-body">
      <form action="<%= request.getContextPath() %>/tasks/create" method="post">
        <div class="mb-3">
          <label class="form-label">タイトル</label>
          <input type="text" class="form-control" name="title" maxlength="100" required>
        </div>

        <div class="mb-3">
          <label class="form-label">ステータス</label>
          <select name="status" class="form-select">
            <option value="NEW">未着手</option>
            <option value="IN_PROGRESS">対応中</option>
            <option value="DONE">完了</option>
          </select>
        </div>

        <button type="submit" class="btn btn-success">登録</button>
      </form>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
