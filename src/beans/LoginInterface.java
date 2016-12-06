/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/17
 * 内容　　:
 * *************************/
package beans;



public interface LoginInterface {
	/**
	 * 各ユーザーごとに振り分けるアクセスIDを列挙します。
	 * また各ユーザーのTopページを定義します。
	 * 例）
	 *
	 * @author 浩生
	 *
	 */
	public enum AccessId{
		KEIRI(Page.KEIRI_TOP),
		JINJI(Page.JINJI_TOP),
		Gest(Page.Welcome),
		/*これより上に列挙してください。*/
		/**
		 * 全てのアクセス権限があるアクセスID
		 * @auther 浩生
		 * 2016/11/17
		 * @param Master AccessId
		 */
		Master(null);
		public Page page;
		private AccessId(Page page) {
			// TODO 自動生成されたコンストラクター・スタブ
			this.page=page;
		}
	}
	/**
	 * 遷移先のページを全て列挙します。
	 * @author 浩生
	 *
	 */
	public enum Page{
		KEIRI_TOP(""),
		JINJI_TOP(""),
		/**
		 * アクセスIDに関係なくゲスト、ユーザーが遷移
		 * できるページです。
		 * アクセスIDは定義しないのでnullです。
		 * @auther 浩生
		 * 2016/11/17
		 * @param Welcome_jsp Page
		 */
		Welcome("Welcome.jsp");
		public String page;
		private Page(String page) {
			// TODO 自動生成されたコンストラクター・スタブ
			this.page=page;
		}
		public Page indexOf(String pageName){
			if(pageName==null)return null;
			for(Page page:Page.values()){
				if(page.page.equals(pageName)){
					return page;
				}
			}
			return null;
		}
	}
	/**
	 * アクセスIDごとにアクセスできるページを列挙します。
	 * アクセス制限のないページではアクセスIDをnullで
	 * 定義します。例）Welcom
	 * 登録するページは初めにデフォルトページを
	 * 列挙します。
	 * @author 浩生
	 * @see Access#Welcome
	 */
	public enum Access{
		KEIRI(AccessId.KEIRI,Page.KEIRI_TOP),
		JINIJ(AccessId.JINJI,Page.JINJI_TOP),
		/**
		 * これは全てのユーザー、ゲストがアクセスできるページです。
		 * なのでアクセスIDはnullにします。
		 * @auther 浩生
		 * 2016/11/17
		 * @param Welcome Access
		 */
		Welcome(null,Page.Welcome);
		public AccessId accessId;
		public Page[] pages;
		private Access(AccessId accessId,Page... pages) {
			// TODO 自動生成されたコンストラクター・スタブ
			this.accessId=accessId;
			this.pages=pages;
		}
		/**
		 * このAccessに登録されているリストをアクセスID
		 * で単一検索します。
		 * @auther 浩生
		 * 2016/11/17
		 * @param accessId
		 * @return
		 */
		public static Access indexOf(AccessId accessId){
			for(Access access:Access.values()){
				if(access.accessId==accessId){
					return access;
				}
			}
			return null;
		}
		/**
		 * このアクセスIDでページにアクセスできるかを判断します。
		 * @auther 浩生
		 * 2016/11/17
		 * @param accessId
		 * @param page
		 * @return
		 */
		public static boolean indexAccess(AccessId accessId,Page page){
			if(accessId==AccessId.Master)return true;
			for(Page tar:indexOf(accessId).pages){
				if(tar==null)return false;
				if(tar==page){
					return true;
				}
			}
			return false;
		}
		/**
		 * 指定アクセスIDのデフォルトページを取得します。
		 * @auther 浩生
		 * 2016/11/24
		 * @param accessId
		 * @return
		 */
		public static Page defaultPage(AccessId accessId){
			Access access=indexOf(accessId);
			if(access==null)return null;
			return access.pages[0];
		}
	}




	/**
	 * ログイン情報クラス。
	 * @auther 浩生
	 * 2016/11/17
	 * @param sessionUserParam String
	 */
	public static final String sessionLogin="LoginInterface.UserParam";
	/**
	 * ユーザーIDリクエスト用
	 * @auther 浩生
	 * 2016/11/17
	 * @param requestLoginId String
	 */
	public static final String requestLoginId="Login.request.loginId";
	/**
	 * パスワードリクエスト用
	 * @auther 浩生
	 * 2016/11/17
	 * @param requestPassword String
	 */
	public static final String requestPassword="Login.request.password";

}
