package com.ayurma.ayuromaweb.client.view;



import com.ayurma.ayuromaweb.client.activity.RpcSuggestOracle;



import com.ayurma.ayuromaweb.client.view.resource.MainClientBundle;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class SuggestBoxViewImpl extends Composite implements SuggestBoxView{

	private static SuggestBoxViewImplUiBinder uiBinder = GWT
			.create(SuggestBoxViewImplUiBinder.class);

	interface SuggestBoxViewImplUiBinder extends
			UiBinder<Widget, SuggestBoxViewImpl> {
	}
	interface Style extends CssResource{
		String rootPanel();
		String suggestboxPanel();
		String ajaxAnimPanel();
		String suggestBox();
		String ajaxImage();
		String twitterStyleTextbox();
	}
	@UiField
	Style cssRes;
	
	private  RpcSuggestOracle rpcSuggestOracle;
	private Presenter presenter;
	private Image rotatingArrow= new Image(MainClientBundle.INSTANCE.getRotatingArrow());
	private Image imgLense;
	@UiField
	HTMLPanel searchBoxPanel;
	@UiField
	HTMLPanel ajaxAnimPanel;
    @UiField
    HTMLPanel suggestboxPanel;
    @UiField 
    Button btnSearch;
    private SuggestBox suggestbox;
	public SuggestBoxViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		btnSearch.addClickHandler(new SearchGoHandler());
		imgLense=new Image(MainClientBundle.INSTANCE.getSearchLense());
		rotatingArrow.addStyleName(cssRes.ajaxImage());
		ajaxAnimPanel.add(imgLense);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void setRpcOracle(RpcSuggestOracle rpcSuggestOracle) {
		this.rpcSuggestOracle=rpcSuggestOracle;
		suggestbox = new SuggestBox(this.rpcSuggestOracle);
		suggestboxPanel.add(suggestbox);
		suggestbox.setText("Search");
		suggestbox.getValueBox().getElement().getStyle().setColor("#cdcdcd");
		suggestbox.getValueBox().getElement().getStyle().setFontWeight(FontWeight.BOLD);
		//suggestbox.setStyleName(cssRes.suggestBox());
		suggestbox.setStyleName(cssRes.twitterStyleTextbox());
		suggestbox.getValueBox().addFocusHandler(new FocusHandler(){

			@Override
			public void onFocus(FocusEvent event) {
				TextBox tb = (TextBox)event.getSource();
				if(tb.getText().equals("Search")){
					tb.setText("");
					suggestbox.getValueBox().getElement().getStyle().setColor("#111");
					suggestbox.getValueBox().getElement().getStyle().clearFontWeight();
				}
				
			}});
		suggestbox.getValueBox().addBlurHandler(new BlurHandler(){

			@Override
			public void onBlur(BlurEvent event) {
				TextBox tb = (TextBox)event.getSource();
				if(tb.getText().equals("")){
					tb.setText("Search");
					suggestbox.getValueBox().getElement().getStyle().setColor("#cdcdcd");
					suggestbox.getValueBox().getElement().getStyle().setFontWeight(FontWeight.BOLD);
				}
				
			}});
		// suggestbox.addKeyUpHandler(new SearchGoHandler());
		 suggestbox.addSelectionHandler(new SelectionHandler(){

			@Override
			public void onSelection(SelectionEvent event) {
				
				if(presenter!=null) presenter.sendQueryToServer(suggestbox.getText());
				
			}});
		
	}
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
		
	}

	@Override
	public void showAjaxAnim() {
		imgLense.removeFromParent();
		ajaxAnimPanel.add(rotatingArrow);
		
	}

	@Override
	public void stopAjaxAnim() {
		rotatingArrow.removeFromParent();
		ajaxAnimPanel.add(imgLense);
		
	}
	// Create a handler for the sendButton and nameField
	public	class SearchGoHandler implements ClickHandler, KeyUpHandler {

				@Override
				public void onKeyUp(KeyUpEvent event) {
					
					if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
						if(presenter!=null) presenter.sendQueryToServer(suggestbox.getText());
					}
					
				}

				@Override
				public void onClick(ClickEvent event) {

					if(presenter!=null) presenter.sendQueryToServer(suggestbox.getText());
				}
						
			}

}
