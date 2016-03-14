package com.ayurma.ayuromaweb.client.admin.view;

import java.util.List;

import com.ayurma.ayuromaweb.client.admin.util.UserLevelData;
import com.google.gwt.user.client.ui.Widget;

public interface IUsersView {
	public interface Presenter{

		

		void addUserToAdminGroup(int iUser, List<Boolean> access,
				String strUserLevel);

		void deleteUser(int id);
		
	}
	Widget asWidget();
	void info(String message,int id);
	void setPresenter(Presenter presenter);
	void init();
	void showData(List<String> names, List<String> emails, List<String> chkNames, List<String> userLevels);
	void userItemInfo(String message, int iUserItem);




	void addUserData(String userName, String userEmail, List<String> areaNames,
			List<Boolean> areaAccessInfo, List<Boolean> chkValues,
			UserLevelData levelData, int iLevelSelected);
	
}
