package com.ayurma.ayuromaweb.client.admin.view.widgets;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class TextBoxPanel extends HTMLPanel{

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
		//tb.setStyleName(style.textBoxExtra());
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
				
				 //removeExtraLinePanel(id);
			 }
			
		}
		
	}
	public int getId() {
		return id;
	}
	
	public void setText(String text) {
		tb.setText(text);
		
	}
	
	public String getText() {
		
		return tb.getText();
	}

}
