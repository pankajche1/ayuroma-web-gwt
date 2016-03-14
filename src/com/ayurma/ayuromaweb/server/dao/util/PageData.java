package com.ayurma.ayuromaweb.server.dao.util;

public class PageData {
	public int iStart=0;
	public int iEnd=-1;
	public int nItemsCurPage=0;
	public int nPages=0;
	/**
	 * 
	 * @param iPage This is the index of the page which you want to get
	 * @param nItemDatastore total items in the data store
	 * @param nItemsPerPage Number of pages per page
	 */
	public PageData(int iPage,int nItemDatastore,int nItemsPerPage){
		nPages=nItemDatastore/nItemsPerPage;
		int nItemsLeft=nItemDatastore%nItemsPerPage;
		if(nItemsLeft>0) nPages++;
		// int iPage=0;//this page index we want
		if(iPage>=0&&iPage<nPages){
			//--the fist item of the current Page:
			iStart=iPage*nItemsPerPage;
			if(iPage>nPages-2){//-- if the page is the last page
				iEnd=iStart+nItemDatastore-1-iPage*nItemsPerPage;
			}else{
				iEnd=iStart+nItemsPerPage-1;
			}	
			nItemsCurPage=iEnd-iStart+1;		 
		}
	}
	public int getiStart() {
		return iStart;
	}
	public int getiEnd() {
		return iEnd;
	}
	public int getnItemsCurPage() {
		return nItemsCurPage;
	}
	public int getnPages() {
		return nPages;
	}
}
