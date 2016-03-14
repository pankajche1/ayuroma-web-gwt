package com.ayurma.ayuromaweb.client.view;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class FooterViewImpl extends Composite implements IFooterView{

	private static FooterViewImplUiBinder uiBinder = GWT
			.create(FooterViewImplUiBinder.class);

	interface FooterViewImplUiBinder extends UiBinder<Widget, FooterViewImpl> {
	}
	private Presenter presenter;
	@UiField Label lblAddressHead;
	@UiField HTMLPanel mobilesPanel,addressPanel,phonesPanel,emailsPanel;
	public FooterViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter=presenter;
		
	}
	@UiHandler("btnHome")
	public void gotoHome(ClickEvent e){
		presenter.gotoPlace("home");
	}
	@UiHandler("btnProducts")
	public void gotoProducts(ClickEvent e){
		presenter.gotoPlace("products");
	}
	@UiHandler("btnContactUs")
	public void gotoContactUs(ClickEvent e){
		presenter.gotoPlace("contactUs");
	}
	@UiHandler("btnAboutUs")
	public void gotoAboutUs(ClickEvent e){
		presenter.gotoPlace("aboutUs");
	}
	@Override
	public void setAddress(String addressHead,List<String> addressLines,
			List<String> mobiles,List<String> phones,
			List<String> emails,
			String city,String pin,String country){
		lblAddressHead.setText(addressHead);
		//address lines:
		String htmlAddress="";
		for(String line:addressLines){
			htmlAddress+=line+"<br/>";
		}
		htmlAddress+=city+"<br/>"+pin+"<br/>"+country+"<br/>";
		addressPanel.getElement().setInnerHTML(htmlAddress);
		//mobile number:
		for(String mobile:mobiles){
			Label lblMobile = new Label(mobile);
			mobilesPanel.add(lblMobile);
		}
		//phones:
		if(phones!=null){
			for(String phone:phones){
				Label lblPhone = new Label(phone);
				phonesPanel.add(lblPhone);
			}			
		}
		//emails
		if(emails!=null){
			for(String email:emails){
				Label lblEmail = new Label(email);
				emailsPanel.add(lblEmail);
			}			
		}		
	}
}
