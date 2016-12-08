/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/10
 * 内容　　:ログインに関する制御を定義する抽象クラス。
 * *************************/
package beans;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.DBManager.PreparedStatementByKoki;

public abstract class LoginSuper implements LoginInterface {
	private HttpServlet servlet;
	private HttpServletRequest request;
	/**
	 * ログインチャレンジ結果を格納するクラスです。
	 *
	 * @auther 浩生 2016/11/10
	 * @param result
	 *            String
	 */
	protected String result;
	/**
	 * ログインアクセスしたユーザー情報を格納する箱クラス。
	 * @author 浩生
	 *
	 */
	public class User{
		/**
		 * ログイン条件
		 * @auther 浩生
		 * 2016/11/10
		 * @param params String[]
		 */
		public String[] params;
		/**
		 * ログインチャレンジ結果
		 * @auther 浩生
		 * 2016/11/10
		 * @param result String
		 */
		public String result;
		/**
		 * ログイン時に実行したSQL
		 * @auther 浩生
		 * 2016/11/10
		 * @param sql String
		 */
		public String sql;
		public ArrayList<ArrayList<String>> resultList;
		{
			this.params=null;
			this.result=new String();
			this.sql=new String();
			this.resultList=new ArrayList<ArrayList<String>>();
		}
	}

	private String welcomePage = new String();

	{
		this.servlet=null;
		this.request=null;
		this.result=new String();

	}
	public LoginSuper(HttpServlet servlet, HttpServletRequest request) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.servlet = servlet;
		this.request = request;
	}

	/**
	 * ログインをチャレンジします。
	 *
	 * @auther 浩生 2016/11/10
	 * @param param
	 *            リクエストで受け取るパラメータ名を指定してください。
	 *            このパラメータをString型で取得してSQLファイルのステートメントにString型で 設定します。
	 *            ステートメントはパラメータ名と同じにしてください。
	 *            ステートメントはDBManager.getPrepardStatementByKokiを使用します。
	 * @return {@link User}
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see DBManager
	 * @see DBManager#getPreparedStatement(String)
	 * @see PreparedStatementByKoki
	 * @see PreparedStatementByKoki#setString(String, String)
	 */
	public User challenge(String... params) throws ClassNotFoundException,
			SQLException {
		//ログイン情報格納
		User user=new User();
		user.params=params;
		DBManager dbManager = new DBManager(Dao.DB.val);
		PreparedStatementByKoki statementByKoki = dbManager
				.getStatementByKoki(readSql(this.servlet, Dao.SQL.val));
		user.sql=statementByKoki.out();
		for (String param : params) {
			statementByKoki.setString(param,
					(String) this.request.getParameter(param));
		}
		if ((user.resultList=statementByKoki.select()).size() == 0) {
			this.result = NG;
			user.result=NG;
			return user;
		}
		this.result = OK;
		user.result=OK;
		return user;
	}

	/**
	 * SQLファイルを読み込みます。
	 *
	 * @auther 浩生 2016/11/10
	 * @param servlet
	 * @param path
	 * @return
	 */
	protected static String readSql(HttpServlet servlet, String path) {
		return readSql(servlet, path, "");
	}

	/**
	 * SQLファイルを読み込む際に行末にlineをつけて取得します。
	 *
	 * @auther 浩生 2016/11/10
	 * @param servlet
	 * @param path
	 * @param line
	 * @return
	 */
	protected static String readSql(HttpServlet servlet, String path,
			String line) {
		path = servlet.getServletContext().getRealPath("/WEB-INF/sql/" + path);
		StringBuilder builder = new StringBuilder();

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(path), "UTF-8"));
			String string = reader.readLine();
			while (string != null) {
				builder.append(string);
				builder.append(line);
				string = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return builder.toString();
	}

	/**
	 * ログインチャレンジ結果により遷移先を切り替える処理を変えてください。 例えば、ログインユーザーごとに遷移先が変わる場合の
	 * コントローラ処理をここに記述します。
	 *
	 * @auther 浩生 2016/11/10
	 * @return 遷移先定数定義したファイル。 ここに記述するコントローラ処理は下記の列挙型とメソッドを参照して記述してください。
	 * @see Forward
	 * @see Forward#indexOf(String)
	 *
	 */
	public abstract Forward getForward();

	/**
	 * ウェルカムページに遷移するかを判断する変数。
	 *
	 * @auther 浩生 2016/11/10
	 * @param welcomeFlg
	 *            int
	 */
	private int welcomeFlg = 1;

	/**
	 * ログインチャレンジがNGの時にウェルカムページに遷移する機能をONにします。 この機能はデフォルトでONになっています。
	 *
	 * @auther 浩生 2016/11/10
	 * @see LoginSuper#go(HttpServletRequest, HttpServletResponse)
	 */
	public void onWelcome() {
		this.welcomeFlg = 1;
	}

	/**
	 * ログインチャレンジがNGの時にウェルカムページへ遷移する機能をOFFにします。
	 *
	 * @auther 浩生 2016/11/10
	 * @see LoginSuper#go(HttpServletRequest, HttpServletResponse)
	 */
	public void offWelcome() {
		this.welcomeFlg = -1;
	}

	/**
	 * ログインチャレンジ結果後に遷移を行うメソッドです。
	 * ログインチャレンジ結果に左右する処置は下記のgetForward()メソッドに処理を記述してください。
	 *
	 * @throws IOException
	 * @throws ServletException
	 * @throws NotYetChallenge
	 * @auther 浩生 2016/11/10
	 * @see LoginSuper#getForward()遷移先振り分けメソッド
	 */
	public void go(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NotYetChallenge {
		if (this.result.isEmpty()) {
			throw new NotYetChallenge();
		}
		Forward forward = null;
		if (this.result == NG) {
			// ウェルカムページに遷移する
			if (this.welcomeFlg > 0) {
				forward = Forward.Welcome;
			} else {
				if (this.welcomePage.isEmpty() == false) {
					RequestDispatcher rd = request.getRequestDispatcher("/"
							+ this.welcomePage);
					rd.forward(request, response);
					return;
				} else {
					System.out.println("WelComeページが設定されていません。");
					return;
				}
			}
		} else {
			forward=getForward();
		}
		String forwardUrl = "/" + forward.to;
		RequestDispatcher rd = request.getRequestDispatcher(forwardUrl);
		rd.forward(request, response);
	}

	/**
	 * ログインチャレンジが行っていない場合に起きる例外クラス。
	 *
	 * @author 浩生
	 *
	 */
	public class NotYetChallenge extends Exception {
		@Override
		public String getMessage() {
			// TODO 自動生成されたメソッド・スタブ
			return "ログインチャレンジが行われていません。";
		}
	}


	/**
	 * デフォルトウェルカムページ以外ウェルカムページを設定します。 ウェルカム遷移機能がOFFになっている場合はフォワード処理は強制的に終了します。
	 *
	 * @auther 浩生 2016/11/10
	 * @param page
	 * @see LoginSuper#offWelcome()
	 * @see LoginSuper#onWelcome()
	 */
	public void setWelCome(String page) {
		this.welcomePage = page;
	}
}