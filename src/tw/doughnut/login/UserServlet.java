package tw.doughnut.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import tw.doughnut.login.User;
import tw.doughnut.login.UserDaoMySqlImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@SuppressWarnings("serial")
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=utf-8";
	UserDaoMySqlImpl userDao = null;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		BufferedReader br = request.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		// 將輸入資料列印出來除錯用
		System.out.println("input: " + jsonIn);

		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		if (userDao == null) {
			userDao = new UserDaoMySqlImpl();
		}

		
		int count = 0;
		User user = null;
		String memberJson = null;
		String action = jsonObject.get("action").getAsString();
		
	
		//設定要從Client端抓取的資料
		switch (action) {
		case "insert":
			memberJson = jsonObject.get("member").getAsString();
			user = gson.fromJson(memberJson, User.class);
			count = userDao.insert(user);
			writeText(response, String.valueOf(count));
			System.out.println("insert = " + memberJson);
			break;
	
		case "login":
			memberJson = jsonObject.get("user").getAsString();
			User userLogin = gson.fromJson(memberJson, User.class);
			boolean result = userDao.login(userLogin);
			JsonObject jObject = new JsonObject();
			jObject.addProperty("result", result);
			writeText(response, jObject.toString());
			System.out.println("login : " + result);
			break;
		default:
			writeText(response, "not fun");
			break;
		}
	}

	private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.print(outText);
		// 將輸出資料列印出來除錯用
		System.out.println("output:" + outText);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (userDao == null) {
			userDao = new UserDaoMySqlImpl();
		}
		List<User> users = userDao.getAll();
		writeText(response, new Gson().toJson(users));
	}

}