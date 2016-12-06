/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/17
 * 内容　　:このクラスはログインに関するセッション管理、ページ管理を行います。
 * 			このクラスを継承したモノを各ログインクラスを作成します。
 * 			例）個人顧客のログインと法人顧客のログインのスパークラス。
 * 			このクラスの継承先では下記の事を定義してください。
 * 			・ユーザーのログインを行うメソッドの処理定義
 * 			・ユーザーに割り振るアクセスID
 * ＜継承先での実装内容＞
 * charengeメソッドと
 * getUserAccessIDメソッドのオーバーライド
 * ＜機能の流れ（セッション無し）＞
 * コンスタント（セッションの確認）
 * 		↓
 * userParamクラスの継承とセッター（必要であれば）
 * 		↓
 * loginメソッドを実行（継承先で）
 * 		↓
 * ｛
 * charengeメソッドの実行
 * 		↓
 * getUserAccessIDの実行
 * 		↓
 * ユーザー情報をセッションに登録
 * 		↓
 * デフォルトページに遷移
 * ｝
 * ＜機能の流れ（アクセス不正）＞
 * コンスタント（セッションの確認）
 * 		↓
 * 例外発生（LoginParamException）
 * ＜機能の流れ（アクセスOK）＞
 * コンスタント（セッションの確認）
 * 		↓
 * 終了
 * *************************/
package beans;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class BaseLogin implements LoginInterface {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private static final String rMessage="Login.Message";
	private UserParam userParam;

	/**
	 * ログイン制御を行うコンスタントを生成します。 ユーザー情報がセッションに存在しない場合はウェルカムページに遷移
	 * 後にLoginSessionException例外発生。
	 * 不正なアクセスならば、LoginParamException例外発生。
	 *
	 * @auther 浩生 2016/11/21
	 * @param servlet
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 * @throws LoginSessionException
	 * @throws LoginParamException
	 */
	public BaseLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			LoginSessionException, LoginParamException {
		this.request = request;
		this.response = response;
		if (!Page.Welcome.page.equals(getPage(request.getRequestURI()))) {
			if (request.getSession().getAttribute(sessionLogin) == null) {
				//メッセージを生成
				String msg="ログイン情報がありません。もう一度ログインしてください。";
				setMessage(msg);
				// ウェルカムページに遷移
				go(Page.Welcome);
				//ゲスト設定。
				defaultUser();
				throw new LoginSessionException();
			}else{
				//アクセス権限の確認
				if(findAccessPage()){
					//ログイン成功
				}else{
					throw new LoginParamException();
				}
			}
		}
	}

	/**
	 * ログインチャレンジを行うメソッドを記述してください。 結果は以下に統一してください。 ログイン成功：true ログイン失敗：false
	 *
	 * @auther 浩生 2016/11/17
	 * @return
	 */
	abstract boolean charenge();

	/**
	 * ログインに成功したユーザーに対して、アクセスIDを振り分けて下さい。
	 *
	 * @auther 浩生 2016/11/17
	 * @return
	 */
	abstract AccessId getUserAccessID();

	/**
	 * ページに遷移します。
	 *
	 * @auther 浩生 2016/11/21
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void go(Page page) throws ServletException, IOException {
		RequestDispatcher rd = this.request
				.getRequestDispatcher(page.page);
		if(this.message!=null)this.request.setAttribute(rMessage,this.message);
		rd.forward(this.request, this.response);
	}

	/**
	 * ログインを実行します。 実行構成は ログイン 　↓
	 * 成功：アクセスIDに付与されたTopページに遷移します。セッションに新しいユーザー情報を登録します。
	 *  失敗：メッセージオブジェクトを設定しウェルカムページに遷移します。
	 * @throws IOException
	 * @throws ServletException
	 *
	 * @auther 浩生 2016/11/21
	 *
	 */
	public void login() throws ServletException, IOException {
		if(charenge()){
			this.userParam=new UserParam();
			userParam.accessId=getUserAccessID();
			this.request.getSession().setAttribute(sessionLogin, userParam);
			//デフォルトページに遷移
			go(this.userParam.accessId.page);
		}else{
			this.request.getSession().setAttribute(rMessage, this.message);
		}
	}

	/**
	 * ログインが成功したユーザー情報をセッションに格納できます。
	 *
	 * @auther 浩生 2016/11/21
	 * @param UserParam
	 */
	public void setUserParam(UserParam userParam) {
		this.userParam = userParam;
	}

	/**
	 * ユーザー情報をセッションに登録します。
	 *
	 * @auther 浩生 2016/11/21
	 *
	 */
	protected void setSession() {
		HttpSession session = this.request.getSession();
		session.setAttribute(sessionLogin, this.userParam);
	}

	/**
	 * セッションのユーザー情報を削除します。
	 *
	 * @auther 浩生 2016/11/21
	 *
	 */
	protected void logout() {
		HttpSession session = this.request.getSession();
		session.removeAttribute(sessionLogin);
	}

	public class LoginException extends Exception {
		@Override
		public String getMessage() {
			// TODO 自動生成されたメソッド・スタブ
			return "ログインできません。";
		}
	}

	public class LoginSessionException extends LoginException {
		@Override
		public String getMessage() {
			// TODO 自動生成されたメソッド・スタブ
			return "ユーザー情報がセッションに格納されていません。";
		}
	}

	public class LoginParamException extends LoginException{
		@Override
		public String getMessage() {
			// TODO 自動生成されたメソッド・スタブ
			return "不正なアクセスです。";
		}
	}

	/**
	 * xxxx/xxx/xxで示されたURIの末尾を返します。
	 *
	 * @param uri
	 * @return
	 */
	private String getPage(String uri) {
		String uris[] = uri.split("/");
		return uris[uris.length - 1];
	}

	/**
	 * ユーザー情報を格納する箱クラス。
	 * 追加の情報を格納したい場合はこのクラスを継承してください。
	 * @author 浩生
	 *
	 */
	public class UserParam{
		public AccessId accessId;
	}
	/**
	 * 現在、セッションに格納されているユーザー情報が現在のページにアクセス出来るかを判断します。
	 * @auther 浩生
	 * 2016/11/21
	 * @return
	 */
	private boolean findAccessPage(){
		UserParam param=(UserParam)this.request.getSession().getAttribute(sessionLogin);
		Access access=Access.indexOf(param.accessId);
		if(access==null)return false;
		Page[] pages=access.pages;
		for(Page page:pages){
			if(page.page.equals(getPage(this.request.getRequestURI()))){
				return true;
			}
		}
		return false;
	}
	/**
	 * 遷移先のメッセージオブジェクト。
	 * @auther 浩生
	 * 2016/11/21
	 * @param message Object
	 */
	private Object message;
	/**
	 * ログイン成功、失敗時に遷移先に送るメッセージオブジェクトを設定出来ます。
	 * @auther 浩生
	 * 2016/11/21
	 * @param message
	 */
	public void setMessage(Object message){
		this.message=message;
	}
	/**
	 * 遷移先などでメッセージを取得できます。
	 * @auther 浩生
	 * 2016/11/21
	 * @return
	 */
	public Object getRequestMessage(){
		return this.request.getAttribute(rMessage);
	}

	/**
	 * デフォルトでゲストユーザーをセッションに設定する。
	 * @auther 浩生
	 * 2016/11/22
	 *
	 */
	private void defaultUser(){
		UserParam param=new UserParam();
		param.accessId=AccessId.Gest;
		this.request.getSession().setAttribute(sessionLogin, param);
	}
	public class defaultMessageBox{
		public String user;
		public String password;
		public String loginDate;
		{
			this.user=new String();
			this.password=new String();
			this.loginDate=new CalendarByKoki().outOfJP();
		}
	}
	/**
	 * セッションからユーザー情報を取得する。
	 * @auther 浩生
	 * 2016/12/05
	 * @return
	 */
	public UserParam getUserParamFromSession(){
		return (UserParam) this.request.getSession().getAttribute(sessionLogin);
	}
}
