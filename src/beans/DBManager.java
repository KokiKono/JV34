/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2015/11/25
 * 内容　　:mysqlデータベース接続に関するクラス。
 * *************************
 * 更新日　:2015/12/08
 * 更新者　:k.koki
 * 内容　　:mysqlのautoCommit関連を制御するメソッドの追加とロールバック機能の追加。
 * *************************
 * 更新日　:2016/01/21
 * 更新者　:k.koki
 * 内容　　:<,>,",',&を含むSQLを置換し文字コードかする。
 * *************************
 * 更新日　:2016/02/04
 * 更新者　:k.koki
 * 内容　　:PreparedStatementに関するメソッドを追加
 * *************************
 * 更新日　:2016/11/03
 * 更新者　:k.koki
 * 内容　　:プリペアドステイトメント関係のクラス、メソッドを追加。
 * *************************
 * 更新日　:2016/11/05
 * 更新者　:k.koki
 * 内容　　:プリペアドステイトメントクラスにメソッド追加。
 * *************************/

package beans;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dtd.PermissionType;

public class DBManager
{
	private static String _forName = "com.mysql.jdbc.Driver";
	private static String _DriverURL = "jdbc:mysql://localhost:3306/";
	private static String _User = "root";
	private static String _DBName = "root";
	private static String _Password = "";
	private Connection _con;
	private Statement _st;
	private ResultSet _rs;
	private ResultSetMetaData _rsMeta;
	private int _ColumnCount;

	public void SetDB() throws ClassNotFoundException, SQLException
	{
		// closeDB();
		// System.out.println("forName:" + _forName);
		// System.out.println("DriverURL:" + _DriverURL);
		Class.forName(_forName);
		this._con = DriverManager.getConnection(_DriverURL + _DBName, DBManager._User, _Password);
		this._st = this._con.createStatement();
	}

	/**
	 * デフォルトコンストラクタ Class.forName("com.mysql.jdbs.Driver")
	 * DriverManager("jdbc:mysql://localhost:3306/任意のDB名,"root","")
	 *
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public DBManager(String strDBName) throws ClassNotFoundException, SQLException
	{
		_DBName = strDBName;
		SetDB();
	}

	/**
	 * コンストラクタ Class.forName("任意のドライバーURL")
	 * DriverManager("jdbc:mysql://localhost:3306/任意のDB名,"root","")
	 *
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public DBManager(String strDBName, String strForName) throws ClassNotFoundException, SQLException
	{
		_DBName = strDBName;
		_forName = strForName;
		_Password = "";
		SetDB();
	}

	/**
	 * コンストラクタ Class.forName("任意のドライバー名");
	 * DriverManager("jdbc:mysql://localhost:3306/任意のDB名","任意のユーザー","")
	 *
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public DBManager(String strDBName, String strForName, String strUser) throws ClassNotFoundException, SQLException
	{
		_DBName = strDBName;
		_forName = strForName;
		_User = strUser;
		_Password = "";
		SetDB();
	}

	/**
	 * コンストラクタ Class.forName("任意のドライバー名");
	 * DriverManager("任意のDriverManager/任意のDB名","任意のユーザー","")
	 *
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public DBManager(String strDBName, String strForName, String strUser, String strDriverManager)
			throws ClassNotFoundException, SQLException
	{
		_DBName = strDBName;
		_forName = strForName;
		_User = strUser;
		_DriverURL = strDriverManager;
		_Password = "";
		SetDB();
	}

	/**
	 * コンストラクタ Class.forName("任意のドライバー");
	 * DriverManager("任意のDriverManager/任意のDB名","任意のユーザー","任意のパスワード")
	 *
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public DBManager(String strDBName, String strForName, String strUser, String strDriverManager, String strPassword)
			throws ClassNotFoundException, SQLException
	{
		_DBName = strDBName;
		_forName = strForName;
		_User = strUser;
		_DriverURL = strDriverManager;
		_Password = strPassword;
		SetDB();
	}

	/**
	 * クローズ用メソッド
	 *
	 * @throws SQLException
	 */
	public void closeDB() throws SQLException
	{
		if (this._rs != null)
		{
			this._rs.close();
		}
		if (this._st != null)
		{
			this._st.close();
		}
		if (this._con != null)
		{
			this._con.close();
		}
	}

	/**
	 * SELECT文実行用メソッド
	 *
	 * @return 二次元配列String型
	 * @throws SQLException
	 */
	public ArrayList<ArrayList<String>> runSelect(String strSQL) throws SQLException
	{
		// SQLインジェクション処理
		// strSQL=replaceSQL(strSQL);
		// SetDB();
		// 戻り値用二次元可変長配列の生成。
		ArrayList<ArrayList<String>> returnList = new ArrayList<ArrayList<String>>();
		this._rs = this._st.executeQuery(strSQL);
		_rsMeta = this._rs.getMetaData();
		// 列数の数
		this._ColumnCount = _rsMeta.getColumnCount();
		// データの取得
		while (this._rs.next())
		{
			// 一時格納用可変長配列の生成。
			ArrayList<String> stockList = new ArrayList<String>();
			for (int i = 1; i <= this._ColumnCount; i++)
			{
				stockList.add(this._rs.getString(i));
			}
			returnList.add(stockList);
		}
		// returnList=getResultSet(this._rs);
		return returnList;
	}

	/**
	 * 更新系SQL文を行うメソッド。
	 *
	 * @param String
	 *            SQL文
	 * @param int
	 *            更新件数
	 * @throws SQLException
	 */
	public int update(String strSQL) throws SQLException
	{
		// SQLインジェクション処理
		// strSQL=replaceSQL(strSQL);
		// SetDB();
		int n = 0;
		n = this._st.executeUpdate(strSQL);
		// closeDB();
		return n;
	}

	/**
	 * 二次元配列String型をMap<int,ArrayList<String>>に変換する。 intは0から始まる。
	 */
	public Map<Integer, ArrayList<String>> aryOFmap(ArrayList<ArrayList<String>> ary)
	{
		// 戻り値用Map変数
		Map<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();
		int i = 0;
		for (ArrayList<String> list : ary)
		{
			map.put(i++, list);
		}
		return map;
	}

	// update k.koki 2015/12/08 begin
	/**
	 * mysqlのautoCommitのON,OFFを指定するメソッドです。
	 *
	 * @param ON
	 *            =true OFF=false
	 * @throws SQLException
	 */
	public void setautoCommit(boolean autoCommit) throws SQLException
	{
		this._con.setAutoCommit(autoCommit);
	}

	/**
	 * mysqlのautoCommitの状態を取得します。
	 *
	 * @throws SQLException
	 */
	public boolean getautoCommit() throws SQLException
	{
		boolean bl = false;
		bl = this._con.getAutoCommit();
		return bl;
	}

	/**
	 * mysqlのCommitを行います。 autoCommitがONの時はOFFにし再度実行しONにします。
	 *
	 * @throws SQLException
	 */
	public void runCommit() throws SQLException
	{
		if (!getautoCommit())
		{
			this._con.commit();
		} else
		{
			setautoCommit(false);
			runCommit();
		}

	}

	/**
	 * mysqlのロールバックを行う機能です。
	 * Connectionをcolseしていた場合又はautoCommitを自動にしていた場合何も処理されません。
	 *
	 * @throws SQLException
	 */
	public void rollback() throws SQLException
	{
		// System.out.println("ロールバック");
		if (!getautoCommit())
		{
			if (this._con != null)
			{
				this._con.rollback();
			}
		}
	}

	// update k.koki 2015/12/08 end
	// update k.koki 2016/01/21 begin
	public String replaceSQL(String strSQL)
	{
		strSQL = strSQL.replaceAll("<", "&lt;");
		strSQL = strSQL.replaceAll(">", "&gt;");
		strSQL = strSQL.replaceAll("\"", "&quot;");
		strSQL = strSQL.replaceAll("&", "&amp;");
		strSQL = strSQL.replaceAll("'", "&rsquo;");
		return strSQL;
	}

	// update k.koki 2016/01/21 end
	// updata k.koki 2016/02/04 begin
	/**
	 * 作成日:2016/02/04 11:43:30 作成者:k.koki
	 *
	 * @param String
	 *            SQLをPrepardStatement形式に変更します。
	 * @param strSQL
	 * @return SQLExceptionが起きた場合、nullをreturnします。
	 * @throws SQLException
	 */
	public PreparedStatement getPreparedStatement(String strSQL) throws SQLException
	{
		// SetDB();

		return this._con.prepareStatement(strSQL);

	}

	/**
	 * 作成日:2016/02/04 11:51:00 作成者:k.koki
	 *
	 * @param PrepardStatement更新処理を実行します
	 *            。
	 * @param sql
	 * @return 更新件数
	 * @throws SQLException
	 */
	public int update(PreparedStatement sql) throws SQLException
	{
		int count = 0;
		count = sql.executeUpdate();
		return count;
	}

	private ArrayList<ArrayList<String>> getResultSet(ResultSet rs) throws SQLException
	{
		// 戻り値用二次元可変長配列の生成。
		ArrayList<ArrayList<String>> returnList = new ArrayList<ArrayList<String>>();
		ResultSetMetaData rsMeta = rs.getMetaData();
		// 列数の数
		int ColumnCount = rsMeta.getColumnCount();
		// データの取得
		while (rs.next())
		{
			// 一時格納用可変長配列の生成。
			ArrayList<String> stockList = new ArrayList<String>();
			for (int i = 1; i <= this._ColumnCount; i++)
			{
				stockList.add(rs.getString(i));
			}
			returnList.add(stockList);
		}
		return returnList;
	}

	public ArrayList<ArrayList<String>> runSelect(PreparedStatement sql) throws SQLException
	{
		// SetDB();
		// 戻り値用二次元配列の生成
		ArrayList<ArrayList<String>> returnList = new ArrayList<>();
		this._rs = sql.executeQuery();
		// returnList=getResultSet(this._rs);
		_rsMeta = this._rs.getMetaData();
		// 列数の数
		this._ColumnCount = _rsMeta.getColumnCount();
		// データの取得
		while (this._rs.next())
		{
			// 一時格納用可変長配列の生成。
			ArrayList<String> stockList = new ArrayList<String>();
			for (int i = 1; i <= this._ColumnCount; i++)
			{
				stockList.add(this._rs.getString(i));
			}
			returnList.add(stockList);
		}
		// closeDB();
		return returnList;
	}

	// update k.koki 2016/02/04 end
	// update k.koki 2016/11/03 start
	/**
	 * 特定の文字列を変換します。基本的なSQLインジェクションは回避されます。
	 *
	 * @author 浩生
	 *
	 */
	public class PreparedStatementByKoki
	{
		private static final String WHERE = "WHERE";
		private static final String AND = "AND";
		private static final String OR = "OR";
		private static final String HAVING = "HAVING";
		private String sql;
		/**
		 * toNull()、toNullAll()を実行したときのkeyログ
		 * 
		 * @auther 浩生 2016/11/08
		 * @param toNullKeys
		 *            ArrayList<String>
		 */
		private ArrayList<String> toNullKeys;

		{
			this.toNullKeys = new ArrayList<String>();
		}

		public PreparedStatementByKoki(String sql)
		{
			// TODO 自動生成されたコンストラクター・スタブ
			this.sql = sql;
		}

		private void replace(String key, String val)
		{
			this.sql = sql.replaceAll(key, val);
		}

		public void setString(String key, String val)
		{
			val = replaceSQL(val);
			this.replace(key, "'" + val + "'");
		}

		public void setInt(String key, int val)
		{
			this.replace(key, String.valueOf(val));
		}

		public ArrayList<ArrayList<String>> select() throws SQLException
		{
			return runSelect(this.sql);
		}

		public int update() throws SQLException
		{
			return DBManager.this.update(sql);
		}

		public String out()
		{
			return this.sql;
		}

		// update k.koki 2016/11/05 start
		private String START = "start*/";
		private String END = "end*/";
		private String IF = "/*if";
		private int nowIndex = 0;

		/**
		 * SQL文でif(key)startからif(key)endまでくくった個所を削除します。
		 * 他のメソッドの干渉もあるのでif(key)はコメント化してください。
		 *
		 * @param key
		 */
		public int toNull(String key)
		{
			// toNullログ
			if (!this.toNullKeys.contains(key))
			{
				this.toNullKeys.add(key);
				this.nowIndex = 0;
			}
			String startKey = IF + "(" + key + ")" + START;
			String endKey = IF + "(" + key + ")" + END;
			int startIndex = endLine(startKey);
			int endIndex = startLine(endKey);
			if (startIndex == -1 || endIndex == -1)
			{
				return -1;
			}
			// 削除処理
			String start = this.sql.substring(0, startIndex);
			String end = this.sql.substring(endIndex, this.sql.length());
			this.sql = start + end;

			this.nowIndex = endIndex;
			return endIndex + endKey.length();
		}

		/**
		 * コメント文の末尾を求める時の最大半角数 コメント末尾で２文字必要なので、if(key)の)後の許容数
		 */
		private int maxLine = 10;

		/**
		 * if(key)の末尾のインデックスを取得します。
		 *
		 * @param ifcode
		 * @return
		 */
		private int endLine(String ifcode)
		{
			int endIndex = this.sql.indexOf(ifcode, nowIndex);
			if (endIndex == -1)
			{
				return -1;
			}
			endIndex = endIndex + ifcode.length();
			// 末尾のコメントを求める
			loop: for (int i = 0; i < maxLine; i++)
			{
				if (this.sql.substring(endIndex, endIndex + i).equals("*/"))
				{
					// コメント末尾発見
					endIndex = endIndex + i;
					break loop;
				}
			}
			return endIndex;
		}

		/**
		 * if(key)の先頭のインデックスを取得します。
		 *
		 * @param ifcode
		 * @return
		 */
		private int startLine(String ifcode)
		{
			int startIndex = this.sql.indexOf(ifcode, nowIndex);
			if (startIndex == -1)
			{
				return -1;
			}
			// 先頭のコメントを検索する。
			loop: for (int i = 0; i < maxLine; i++)
			{
				if (this.sql.substring(startIndex - i, startIndex).equals("/*"))
				{
					// 先頭コメント発見
					startIndex = startIndex - i;
					break loop;
				}
			}
			return startIndex;
		}

		/**
		 * 該当するif(key)文を全て削除します。
		 *
		 * @param key
		 */
		public void toNullAll(String key)
		{
			int index = 0;
			while (toNull(key) > 0)
				;

		}

		/**
		 * 検索しそのインデックスを返します。
		 *
		 * @auther 浩生 2016/11/07
		 * @param key
		 * @return
		 */
		public int indexOf(String key)
		{
			return this.sql.indexOf(key);
		}

		/**
		 * 複数の文字列を検索し存在するかをbooleanで返します。
		 *
		 * @auther 浩生 2016/11/07
		 * @param keys
		 * @return
		 */
		public boolean indexOfs(String... keys)
		{
			int index = 0;
			for (String key : keys)
			{
				index = index + indexOf(key);
			}
			if (index > 0)
			{
				return true;
			}
			return false;
		}

		/**
		 * SQLをクリーンする（未実装）
		 * 
		 * @auther 浩生 2016/11/07
		 *
		 */
		public void cleanSql()
		{
			this.sql = commentDelete();
			while (clean() > 0)
				;
			if (this.sql.indexOf(";") == -1)
			{
				this.sql = this.sql + ";";
			}
			clean();
			this.sql = this.sql.replaceAll("WHERE\\s*HAVING", HAVING);
			this.sql = this.sql.replaceAll("WHERE\\s*;", ";");
			this.sql = this.sql.replaceAll("HAVING\\s*;", ";");
		}

		private final String[] flushs = { "AND\\s*;", "OR\\s*;" };
		private final String[] havings = { "HAVING\\s*AND", "HAVING\\s*OR", "AND\\s*HAVING", "OR\\s*HAVING" };
		private final String[] wheres = { "OR\\s*WHERE", "AND\\s*WHERE", "WHERE\\s*AND", "WHERE\\s*OR" };
		private final String ands = "AND\\s*AND";
		private final String ors = "OR\\s*OR";

		private int clean()
		{
			int count = 0;

			for (String where : wheres)
			{
				if (this.sql.indexOf(where) > 0)
				{
					count++;
				}
				this.sql = this.sql.replaceAll(where, WHERE);
			}
			for (String having : havings)
			{
				if (this.sql.indexOf(having) > 0)
				{
					count++;
				}
				this.sql = this.sql.replaceAll(having, HAVING);
			}
			for (String flush : flushs)
			{
				if (this.sql.indexOf(flush) > 0)
					count++;
				this.sql = this.sql.replaceAll(flush, "");
			}
			if (this.sql.indexOf(ands) > 0)
				count++;
			this.sql = this.sql.replaceAll(ands, AND);
			if (this.sql.indexOf(ors) > 0)
				count++;
			this.sql = this.sql.replaceAll(ors, OR);
			return count;
		}

		/**
		 * コメント文を全て削除します。
		 * 
		 * @auther 浩生 2016/11/08
		 * @return
		 */
		private String commentDelete()
		{
			String regex = "/\\*/?([^/]|[^*]/)*\\*/";
			return this.sql.replaceAll(regex, "");
		}

		/**
		 * 文字列が半角スペースで構成されているかをチェックします。
		 *
		 * @auther 浩生 2016/11/07
		 * @param str
		 * @return
		 */
		private boolean ifBlank(String str)
		{
			for (int count = 0; count < str.length(); count++)
			{
				if (str.charAt(count) != " ".charAt(0))
				{
					return false;
				}
			}
			return true;
		}

		/**
		 * 引数配列keyがtoNull()、toNullAll()で使用されたかを検索しbooleanを返します。
		 * 
		 * @auther 浩生 2016/11/08
		 * @param keys
		 * @return
		 */
		public boolean ifToNull(String... keys)
		{
			for (String key : keys)
			{
				if (!this.toNullKeys.contains(key))
					return false;
			}
			return true;
		}
		// update k.koki 2016/11/05 end
	}

	/**
	 * SQL文をPreparedStatementByKokiクラスに変換して返します。 SQL文の定型句は大文字にしてください。
	 *
	 * @param sql
	 * @return
	 */
	public PreparedStatementByKoki getStatementByKoki(String sql)
	{
		return new PreparedStatementByKoki(sql);
	}

	// update k.koki 2016/11/03 end

	/**
	 * updateまたはinsertするメソッド
	 * 
	 * @auther dyf 2016/11/20
	 * @param params
	 *            第一引数は必ずSQL文,objectを渡す
	 * @return データベースに登録できたら1,できなかったら0
	 */
	public int updateORInsert(Object... params)
	{
		int state = 0;
		try
		{
			params.getClass();
			PreparedStatement ps = getPreparedStatement((String) params[0]);
			for (int i = 1; i < params.length; i++)
			{
				if (params[i] instanceof String)
				{
					ps.setString(i, (String) params[i]);
				} else if (params[i] instanceof Integer)
				{
					ps.setInt(i, (int) params[i]);
				} else if (params[i] instanceof Date)
				{
					ps.setDate(i, (Date) params[i]);
				}

				// System.out.println(params[i]);
			}
			ps.executeUpdate();
			state = 1;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return state;
	}

	/**
	 * すでにデータベースに登録されているかどうか
	 * 
	 * @auther dyf 2016/11/20
	 * @param params
	 *            objectを渡す,最初のパラメータ必ずsql文
	 * @return すでにあるならtrue,ないならfalse
	 * @throws SQLException
	 */
	public boolean selectIsExist(Object... params) throws SQLException
	{

		PreparedStatement ps = getPreparedStatement((String) params[0]);
		for (int i = 1; i < params.length; i++)
		{
			if (params[i] instanceof String)
			{
				ps.setString(i, (String) params[i]);
			} else if (params[i] instanceof Integer)
			{
				ps.setInt(i, (int) params[i]);
			} else if (params[i] instanceof Date)
			{
				ps.setDate(i, (Date) params[i]);
			}

		}

		_rs = ps.executeQuery();

		return _rs.next();

	}

	/**
	 * データベースにアクセス情報取得
	 * 
	 * @auther dyf
	 * @param params
	 *            第一引数は必ずSQL文
	 * @return 取得してきた情報を返す(ArrayList)
	 */
	public ArrayList<ArrayList> select(Object... params)
	{
		ArrayList<ArrayList> aryTbl = new ArrayList<ArrayList>();
		try
		{

			PreparedStatement ps = getPreparedStatement((String) params[0]);

			for (int i = 1; i < params.length; i++)
			{
				if (params[i] instanceof String)
				{
					ps.setString(i, (String) params[i]);
				} else if (params[i] instanceof Integer)
				{
					ps.setInt(i, (int) params[i]);
				} else if (params[i] instanceof Date)
				{
					ps.setDate(i, (Date) params[i]);
				}

			}
			_rs = ps.executeQuery();
			java.sql.ResultSetMetaData rsmd = _rs.getMetaData();
			while (_rs.next())
			{
				ArrayList<String> rec = new ArrayList<String>();
				for (int i = 1; i <= rsmd.getColumnCount(); i++)
				{
					rec.add(_rs.getString(i));
				}
				aryTbl.add(rec);
			}
			_rs.close();
			return aryTbl;

		} catch (SQLException e)
		{
			e.printStackTrace();
			return aryTbl;
		} catch (NullPointerException e)
		{
			System.out.println("サーバ側で問題発生しました");
			return aryTbl;
		}
	}

	/**
	 * 特定情報を取得する場合は
	 * 
	 * @auther dyf 2016/11/29
	 * @param params
	 *            第一引数はSQL,第二引数はデータベースに取得したいカラム名
	 * @return 特定情報
	 */
	public String selectReturnStr(Object... params)
	{
		String result = "";
		try
		{

			PreparedStatement ps = getPreparedStatement((String) params[0]);
			System.out.println(params.length);
			int j = 0;
			for (int i = 2; i < params.length; i++)
			{
				j = j + 1;
				if (params[i] instanceof String)
				{
					ps.setString(j, (String) params[i]);
				} else if (params[i] instanceof Integer)
				{
					ps.setInt(j, (int) params[i]);
				} else if (params[i] instanceof Date)
				{
					ps.setDate(j, (Date) params[i]);
				}

			}
			_rs = ps.executeQuery();
			java.sql.ResultSetMetaData rsmd = _rs.getMetaData();
			while (_rs.next())
			{
				result = _rs.getString((String) params[1]);
			}
			_rs.close();
			return result;

		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		} catch (NullPointerException e)
		{
			System.out.println("サーバ側で問題発生しました");
			return null;
		}
	}

	/**
	 * 権限チェック 列挙型からチェックしたい権限取得
	 * 
	 * @auther dyf 2016/12/04
	 * @param checkPer
	 *            チェックしたい権限種類
	 * @param permissionName
	 *            チェックする権限名
	 * @param loginEmployeeID
	 *            ログインしている社員ID
	 * @return 権限があるとtrue,ないとfalse
	 */
	public boolean permissionCheck(PermissionType checkPer, String permissionName, String loginEmployeeID)
	{

		String dbPermission = "";
		String permission = "";
		String employeePermission = "";

		switch (checkPer) {

		case Manager:

			dbPermission = "SELECT official_position_id,official_position_name FROM official_position_master WHERE official_position_name = ?";
			permission = "official_position_id";
			employeePermission = "SELECT official_position_id FROM employee_master WHERE employee_master = ?";

			break;

		case Department:

			dbPermission = "SELECT department_id,department_name FROM department_master WHERE department_name = ?";
			permission = "department_id";
			employeePermission = "SELECT department_id FROM employee_master WHERE employee_master = ?";

			break;
		}

		String dbResult = selectReturnStr(dbPermission, permission, permissionName);

		String employeeResult = selectReturnStr(employeePermission, permission, loginEmployeeID);

		// ログインした人権限があるかどうか
		if (dbResult.equals(employeeResult))
		{
			return true;
		} else
		{
			return false;
		}
	}


}
