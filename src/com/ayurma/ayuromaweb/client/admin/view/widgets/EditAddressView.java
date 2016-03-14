package com.ayurma.ayuromaweb.client.admin.view.widgets;


import java.util.ArrayList;
import java.util.List;

import com.ayurma.ayuromaweb.shared.AddressDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;


public class EditAddressView extends Composite implements IEditAddressView{

	private static EditAddressViewUiBinder uiBinder = GWT
			.create(EditAddressViewUiBinder.class);

	interface EditAddressViewUiBinder extends UiBinder<Widget, EditAddressView> {
	}
	private Presenter presenter;
	private Long key;
	interface Style extends CssResource{
		String textBox();
		String textBoxExtra();
		
		
	}
	//btnUpdateLines, btnUpdateCity, btnUpdateState, btnUpdateCountry;
	@UiField InlineLabel lblUpdateLines,lblUpdateCity, lblUpdateState, lblUpdateCountry;
	@UiField HTMLPanel linesPanel;
	@UiField TextBox tbCity, tbState, tbCountry;
	private List<TextBoxPanel> lstTextBoxPanels;
	public EditAddressView() {
		initWidget(uiBinder.createAndBindUi(this));
		lstTextBoxPanels = new ArrayList<TextBoxPanel>(); 
	}
	
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
		
	}

	@Override
	public void info(String message, int id) {
		switch(id){
		case 0:
			//TODO a general message
			break;
		case 1:lblUpdateLines.setText(message);break;
		case 2:lblUpdateCity.setText(message);break;
		case 3:lblUpdateState.setText(message);break;
		case 4:lblUpdateCountry.setText(message);break;
		}
		
	}
	@UiHandler("btnUpdateLines")
	void onBtnUpdateLinesClick(ClickEvent e) {
		List<String> lines = new ArrayList<String>();
		for(TextBoxPanel t: lstTextBoxPanels){
			lines.add(t.getText());
		}
		presenter.updateAddressLines(lines, key);
	}
	@UiHandler("btnUpdateCity")
	void onBtnUpdateCityClick(ClickEvent e) {
		presenter.updateCity(tbCity.getText(), key);
	}
	@UiHandler("btnUpdateState")
	void onBtnUpdateStateClick(ClickEvent e) {
		presenter.updateState(tbState.getText(), key);
		
	}
	@UiHandler("btnUpdateCountry")
	void onBtnUpdateCountryClick(ClickEvent e) {
		presenter.updateCountry(tbCountry.getText(), key);
		
	}
	@UiHandler("btnAddLine")
	void onBtnAddLineClick(ClickEvent e) {
		
	}
	@UiHandler("btnRemove")
	void onBtnRemoveClick(ClickEvent e) {
		
	}

	@Override
	public void reset() {
		//clear all the messages:
		lblUpdateLines.setText("");
		lblUpdateCity.setText("");
		lblUpdateState.setText("");
		lblUpdateCountry.setText("");
		
	}
	public void setData(AddressDTO data){
		//first show the addresses lines:
		List<String> lines = data.getAddressLines();
		key = data.getKey();
		int i = 0;
		//clear already existing data:
		linesPanel.clear();
		lstTextBoxPanels.clear();
		//put the new data:
		for(String line:lines){
			TextBoxPanel panel = new TextBoxPanel(i);
			panel.setText(line);
			//adding to DOM
			linesPanel.add(panel);
			//adding to the list
			lstTextBoxPanels.add(panel);
			i++;
		}
		//now other data:
		tbCity.setText(data.getCity());
		tbState.setText(data.getState());
		tbCountry.setText(data.getCountry());
		
	}

}
