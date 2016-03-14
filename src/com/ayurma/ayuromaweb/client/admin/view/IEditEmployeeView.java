package com.ayurma.ayuromaweb.client.admin.view;





import com.ayurma.ayuromaweb.client.admin.view.widgets.IEditAddressView;
import com.ayurma.ayuromaweb.shared.EmployeeDTO;
import com.google.gwt.user.client.ui.Widget;

public interface IEditEmployeeView {
	public interface Presenter{

		
		
	}
	void setPresenter(Presenter presenter);
	void setAddressPresenter(IEditAddressView.Presenter presenter);
	Widget asWidget();
	void reset();
	
	void info(String message, int id);
	void setData(EmployeeDTO dto);
	
	
}
