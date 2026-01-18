package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import model.Task;

 //tasksテーブルへのCRUD操作

public class TaskDao {
	private static final String URL;
	private static final String USER;
	private static final String PASS;

	//初期化ブロック:DB接続情報（URL / USER / PASS）をdb.propertiesから読み出し
	static {
	    try (InputStream in = TaskDao.class
	            .getClassLoader()
	            .getResourceAsStream("db.properties")) {

	        if (in == null) {
	            throw new RuntimeException("db.properties not found");
	        }

	        Properties p = new Properties();
	        p.load(in);

	        URL = p.getProperty("db.url");
	        USER = p.getProperty("db.user");
	        PASS = p.getProperty("db.pass");

	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

  //タスク一覧表示SQL
  public List<Task> findAll() {
	  List<Task> tasks = new ArrayList<>();
	  //tasksテーブルから全件取得するSQL
	  String sql = "SELECT id, title, status FROM tasks ORDER BY id";

	  try {
	    Class.forName("com.mysql.cj.jdbc.Driver");

	    try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	      while (rs.next()) {
	        int id = rs.getInt("id");
	        String title = rs.getString("title");
	        String status = rs.getString("status");
	        tasks.add(new Task(id, title, status));
	      }
	    }

	    return tasks;

	  } catch (Exception e) {
	    throw new RuntimeException("一覧取得に失敗しました", e);
	  }
	}
  
  //タスク追加SQL
  public void insert(String title, String status) {
	  String sql = "INSERT INTO tasks (title, status) VALUES (?, ?)";

	  try {
	    Class.forName("com.mysql.cj.jdbc.Driver");

	    try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	      ps.setString(1, title);
	      ps.setString(2, status);
	      ps.executeUpdate();
	    }

	  } catch (Exception e) {
	    throw new RuntimeException("タスク登録に失敗しました", e);
	  }
	}
  
 //タスク削除SQL
  public void deleteById(int id) {
	  String sql = "DELETE FROM tasks WHERE id = ?";

	  try {
	    Class.forName("com.mysql.cj.jdbc.Driver");

	    try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	      ps.setInt(1, id);
	      ps.executeUpdate();
	    }

	  } catch (Exception e) {
	    throw new RuntimeException("削除に失敗しました", e);
	  }

  }
  
  //タスク更新SQL
  public void updateStatus(int id, String status) {
	  String sql = "UPDATE tasks SET status = ? WHERE id = ?";

	  try {
	    Class.forName("com.mysql.cj.jdbc.Driver");

	    try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	      ps.setString(1, status);
	      ps.setInt(2, id);
	      ps.executeUpdate();
	    }

	  } catch (Exception e) {
	    throw new RuntimeException("ステータス更新に失敗しました", e);
	  }
	}
  
  //タスク検索SQL
  public List<Task> search(String searchKeyword, String status) {
	    List<Task> tasks = new ArrayList<>();

	    // null対策
	    if (searchKeyword == null) searchKeyword = "";
	    searchKeyword = searchKeyword.trim();
	    if (status == null || status.isBlank()) status = "ALL";

	    // 検索条件作成
	    StringBuilder sql = new StringBuilder("SELECT id, title, status FROM tasks");
	    List<Object> params = new ArrayList<>();
	    List<String> conditions = new ArrayList<>();

	    if (!searchKeyword.isEmpty()) {
	        conditions.add("title LIKE ?");
	        params.add("%" + searchKeyword + "%");
	    }
	    if (!"ALL".equals(status)) {
	        conditions.add("status = ?");
	        params.add(status);
	    }

	    if (!conditions.isEmpty()) {
	        sql.append(" WHERE ").append(String.join(" AND ", conditions));
	    }
	    sql.append(" ORDER BY id");

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
	             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

	            // パラメータセット
	            for (int i = 0; i < params.size(); i++) {
	                ps.setObject(i + 1, params.get(i));
	            }

	            try (ResultSet rs = ps.executeQuery()) {
	                while (rs.next()) {
	                    int id = rs.getInt("id");
	                    String title = rs.getString("title");
	                    String st = rs.getString("status");
	                    tasks.add(new Task(id, title, st));
	                }
	            }
	        }

	        return tasks;

	    } catch (Exception e) {
	        throw new RuntimeException("検索に失敗しました", e);
	    }
	}

}
