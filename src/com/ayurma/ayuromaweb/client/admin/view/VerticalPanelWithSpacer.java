package com.ayurma.ayuromaweb.client.admin.view;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class VerticalPanelWithSpacer extends VerticalPanel {
	private static final String CSS_DEMO_INSERT_PANEL_EXAMPLE_SPACER = "demo-InsertPanelExample-spacer";

	  public VerticalPanelWithSpacer() {
	    //Label spacerLabel = new Label("");
	   // spacerLabel.setStylePrimaryName(CSS_DEMO_INSERT_PANEL_EXAMPLE_SPACER);
	   // super.add(spacerLabel);
	  }

	  @Override
	  public void add(Widget w) {
	    super.insert(w, getWidgetCount() - 1);
	  }

	  @Override
	  public void insert(Widget w, int beforeIndex) {
	    if (beforeIndex == getWidgetCount()) {
	      beforeIndex--;
	    }
	    super.insert(w, beforeIndex);
	  }
	  /*
	   * added by Pankaj 131210 1021 hrs
	   * this is for clearing all the items except the last item
	   */
	  @Override
	  public void clear(){
		  super.clear();
		 // int numChildren=getWidgetCount();
		 // for(int i=0;i<numChildren-1;i++){
			  //remove(i);
		  //}
	  }
}
