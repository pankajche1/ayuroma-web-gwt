package com.ayurma.ayuromaweb.client.admin.view;





import java.util.List;

import com.google.gwt.user.client.ui.Widget;

public interface IBrowseEmployeesView {
	public interface Presenter{
		void onLoadEmployeesButtonClicked(String text, String value, int sourceId);

		void onEditEmployeeClicked(int employeeId);

		

		void onDeleteEmployeeClicked(int id);

		void linkButtonClicked(int id);

		int getDisplayMode();

		void clearEmployeesCache();
		
		
		
	}

	void setPresenter(Presenter presenter);
	Widget asWidget();
	void showEmployeesList(List<String> names, List<String> sn, int displayMode);
	void reset();
	
	void info(String message, int code);
	void clear();
	void init();

}
