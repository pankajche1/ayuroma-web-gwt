package com.ayurma.ayuromaweb.client.admin.view.widgets;




import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class AddressWidget extends Composite implements IAddressWidget{

	private static AddressWidgetUiBinder uiBinder = GWT
			.create(AddressWidgetUiBinder.class);

	interface AddressWidgetUiBinder extends UiBinder<Widget, AddressWidget> {
	}
	interface Style extends CssResource{
		String textBox();
		String textBoxExtra();
		
		
	}
	@UiField
	Button btnAddLine, btnRemove;
	@UiField
	HTMLPanel  extraLinePanel, panelRemoveButton;
	@UiField TextBox tbLine1, tbLine2, tbLine3, tbCity, tbState, tbCountry;
	@UiField
	Style style;
	private int id = 0;
	private int idTextBoxPanelAddress1 = 0;
	private AddressesPanel parentPanel;
	/*
	 * extra line in the default address1:
	 */
	private List<TextBoxPanel> lstTextBoxPanels = new ArrayList<TextBoxPanel>();
	//private List<HasText> lstAddressLines = new ArrayList<TextBoxPanel>();
	public AddressWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		
	}
	public AddressWidget(int id, AddressesPanel parentPanel) {
		initWidget(uiBinder.createAndBindUi(this));
		this.id = id;
		//make the remove button visible:
		panelRemoveButton.setVisible(true);
		
		this.parentPanel = parentPanel;
	}
	/**
	 * for adding a new line in the default address
	 * @param e
	 */
	@UiHandler("btnAddLine")
	void onAddLineAddress1Click(ClickEvent e) {
		TextBoxPanel panel = new TextBoxPanel(idTextBoxPanelAddress1);
		idTextBoxPanelAddress1++;
		//add to the panel:
		extraLinePanel.add(panel);
		lstTextBoxPanels.add(panel);
	}
	@UiHandler("btnRemove")
	void onRemoveClick(ClickEvent e) {
		parentPanel.removePanel(id);
	}	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
	/**
	 * this is a new line in the address
	 * @author pankaj
	 *
	 */
	private class TextBoxPanel extends HTMLPanel implements HasText{
		private int id;
		private Button btnDelete;
		private TextBox tb;
		//private int displayMode=0;
		
		public TextBoxPanel(int id) {
			super("");
			this.id=id;
			EventHandler eventHandler = new EventHandler();
			tb = new TextBox();
			add(tb);
			tb.setStyleName(style.textBoxExtra());
			btnDelete=new Button("Remove");
			btnDelete.addClickHandler(eventHandler);
			add(btnDelete);
		}
		
		private class EventHandler implements ClickHandler, KeyUpHandler{

			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onClick(final ClickEvent event) {
				 Widget sender = (Widget) event.getSource();
				 if(sender==btnDelete){
					 //confirmDelete(id);	
					 //removeTextBoxPanel(id);
					 removeExtraLinePanel(id);
				 }
				
			}
			
		}
		public int getId() {
			return id;
		}
		@Override
		public void setText(String text) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public String getText() {
			
			return tb.getText();
		}
		
		
	}//private class ends here
	private void removeExtraLinePanel(int idExtraLinePanel){
		int index = -1;
		int i=0;
		for(TextBoxPanel panel : lstTextBoxPanels){
			if(idExtraLinePanel==panel.getId()){
				panel.removeFromParent();
				index = i;
				break;
			}
			i++;
			
		}
		if(index>=0){
			lstTextBoxPanels.remove(index);
			
		}
	}
	public String getCity(){
		return tbCity.getText();
	}
	public HasText getTbCity(){
		return tbCity;
	}
	public String getState(){
		return tbState.getText();
	}
	public String getCountry(){
		return tbCountry.getText();
		
	}
	
	@Override
	public HasText getTbState() {
		
		return tbState;
	}
	@Override
	public HasText getTbCountry() {
		
		return tbCountry;
	}
	@Override
	public List<String> getLines() {

		List<String> lines = new ArrayList<String>();
		//first default lines:
		lines.add(tbLine1.getText());
		lines.add(tbLine2.getText());
		lines.add(tbLine3.getText());
		//now extra lines:
		for(TextBoxPanel linePanel: lstTextBoxPanels){
			lines.add(linePanel.getText());
		}
		return lines;
	}
	public void reset(){
		//clear the first three lines:
		tbLine1.setText(""); tbLine2.setText(""); tbLine3.setText(""); tbCity.setText(""); tbState.setText(""); tbCountry.setText("");
		//remove the extra lines if any:
		
		for(TextBoxPanel p:lstTextBoxPanels){
			//remove from DOM:
			p.removeFromParent();
		}
		//clear the list:
		lstTextBoxPanels.clear();
		
		
		
	}
	
	
}
