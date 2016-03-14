package com.ayurma.ayuromaweb.client.view;



import com.ayurma.ayuromaweb.client.activity.RpcSuggestOracle;
import com.google.gwt.user.client.ui.Widget;

public interface SuggestBoxView {
	public interface Presenter{
		void sendQueryToServer(String query);
	}
	void setRpcOracle(RpcSuggestOracle rpcSuggestOracle);
	void setPresenter(Presenter presenter);
	void showAjaxAnim();
	void stopAjaxAnim();
	Widget asWidget();
}
