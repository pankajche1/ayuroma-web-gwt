package com.ayurma.ayuromaweb.server;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

@SuppressWarnings("serial")
public class ServeImageServlet extends HttpServlet  {
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

	public void doGet(HttpServletRequest req, HttpServletResponse res)
	    throws IOException {
	        BlobKey blobKey = new BlobKey(req.getParameter("blob-key"));
	       // System.out.println("Pankaj: server Image");
	        //this line was added on 1308062158
	        res.setHeader("Cache-Control", "max-age=1209600"); //Cache for two weeks
	        blobstoreService.serve(blobKey, res);
	        //blobstoreService.
	       
	    }

}