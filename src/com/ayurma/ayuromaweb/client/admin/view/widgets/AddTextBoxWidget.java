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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class AddTextBoxWidget extends Composite {

	private static AddTextBoxWidgetUiBinder uiBinder = GWT
			.create(AddTextBoxWidgetUiBinder.class);

	interface AddTextBoxWidgetUiBinder extends
			UiBinder<Widget, AddTextBoxWidget> {
	}
	interface Style extends CssResource{
		String textBox();
		
	}
	@UiField
	Button btnAddTextBox;
	@UiField
	HTMLPanel tbPanel;
	@UiField
	TextBox tb1;
	@UiField
	Label lblLabel;
	@UiField
	Style style;
	private int idPanel = 0;
	private List<TextBoxPanel> lstTextBoxPanels = new ArrayList<TextBoxPanel>();
	public AddTextBoxWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		//put the first text box in the list:
		//lstTextBoxes.add(tb1);
	}
	@UiHandler("btnAddTextBox")
	void onAddTextBoxClick(ClickEvent e) {
		
		TextBoxPanel panel = new TextBoxPanel(idPanel);
		idPanel++;
		//add to the panel:
		tbPanel.add(panel);
		lstTextBoxPanels.add(panel);
		
	}
	public List<String> getValues(){
		List<String> listValues = new ArrayList<String>();
		//text text from the first text box:
		String text = tb1.getText().trim();
		if(!text.isEmpty()){
			listValues.add(text);
		}
		//text from other text boxes:
		for(TextBoxPanel panel : lstTextBoxPanels){
			text = panel.getText().trim();
			if(!text.isEmpty()){
				listValues.add(text);
			}
			
		}
		return listValues;
	}
	public void setButtonText(String text){
		btnAddTextBox.setText(text);
	}
	public void setLabelText(String text){
		lblLabel.setText(text);
	}
	private class TextBoxPanel extends HTMLPanel{
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
			tb.setStyleName(style.textBox());
			btnDelete=new Button("Remove");
			btnDelete.addClickHandler(eventHandler);
			add(btnDelete);
		}
		public String getText(){
			return tb.getText();
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
					 removeTextBoxPanel(id);
				 }
				
			}
			
		}
		public int getId() {
			return id;
		}
		
		
	}//private class ends here
	private void removeTextBoxPanel(int idPanel){
		int index = -1;
		int i=0;
		for(TextBoxPanel panel : lstTextBoxPanels){
			if(idPanel==panel.getId()){
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
	public void reset(){
		//empty the first text box:
		tb1.setText("");
		//now remove all other text boxes:
		//remove from dom:
		for(TextBoxPanel panel : lstTextBoxPanels){
			panel.removeFromParent();
		}
		//now make the list empty:
		lstTextBoxPanels.clear();
	}


}
