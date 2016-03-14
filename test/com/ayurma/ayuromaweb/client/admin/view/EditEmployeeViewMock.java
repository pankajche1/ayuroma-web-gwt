package com.ayurma.ayuromaweb.client.admin.view;

import static org.junit.Assert.assertEquals;

import java.util.List;

import com.ayurma.ayuromaweb.shared.AddressDTO;
import com.ayurma.ayuromaweb.shared.EmployeeDTO;
import com.google.gwt.user.client.ui.Widget;

public class EditEmployeeViewMock implements IEditEmployeeView {
	private EmployeeDTO targetEmployee;
	@Override
	public void setPresenter(Presenter presenter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info(String message, int id) {
		System.out.println("EditEmployeeViewMock::info() message: "+message);
		
	}

	@Override
	public void setData(EmployeeDTO dto) {
		//System.out.println("EditEmployeeViewMock::setData() name:"+dto.getName());
		this.targetEmployee = dto;
		displayEmployeeDTO(dto);
		
	}
	private void displayEmployeeDTO(EmployeeDTO dto){
		
		System.out.println("EditEmployeeViewMock::name:"+dto.getName());
		System.out.println("EditEmployeeViewMock::designation:"+dto.getDesignation());
		//System.out.println("EditEmployeeViewMock::designation:"+dto.getDesignation());
		//addresses:
		List<AddressDTO> addresses = dto.getAddresses();
		int iAddress = 0 ;
		for(AddressDTO a:addresses){
			System.out.println("    Address ("+(iAddress+1)+")");
			System.out.println("        city:"+a.getCity());
			System.out.println("        state:"+a.getState());
			//get the lines:
			System.out.println("        lines:");
			List<String> lines = a.getAddressLines();
			for(String l:lines){
				System.out.println("            "+l);
			}
			iAddress++;
		}
		
	}

	@Override
	public void setAddressPresenter(
			com.ayurma.ayuromaweb.client.admin.view.widgets.IEditAddressView.Presenter presenter) {
		// TODO Auto-generated method stub
		
	}

	public EmployeeDTO getTargetEmployee() {
		return targetEmployee;
	}

}
