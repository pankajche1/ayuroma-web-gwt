package com.ayurma.ayuromaweb.server.admin;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ayurma.ayuromaweb.server.dao.AdminDAO;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

@SuppressWarnings("serial")
public class UploadImageServlet extends HttpServlet  {
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    @SuppressWarnings("deprecation")
	public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {
    	//
    	String imageLabel=req.getParameter("imageInfo");
    	String folderName=req.getParameter("folderName");
    	//getting the data from the request:
        Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
        
        BlobKey blobKey = blobs.get("imageFile");//name of the file element of the html form
        //System.out.println("Got imageInfo:"+imageLabel+", and folder name:"+folderName);
        if (blobKey == null) {
        	//System.out.println("from Upload Image: The blob key is null.");
           // res.sendRedirect("/");
        } else {
        	//here an image data container object will be created on the data store to
        	//store the key of the image:
        	
        	AdminDAO.get().saveImageInfo(folderName,imageLabel, blobKey.getKeyString());
        	//System.out.println("from Upload Image: key of the image="+blobKey.getKeyString());
            //res.sendRedirect(blobKey.getKeyString());
            res.setStatus(HttpServletResponse.SC_CREATED);
            //Redirect recursively to this servlet (calls doGet)
            //the name of the servlet is 'uploadSliderImage'
            res.sendRedirect("/admin/uploadImageServlet?id=" + blobKey.getKeyString());          
           // res.setContentType("text/html");
            //res.set
           // res.getWriter().print(blobKey.getKeyString());
           // res.flushBuffer();
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
    	
      //Send the meta-data id back to the client in the HttpServletResponse response
      String id = req.getParameter("id");
     // System.out.println("Upload Image doGet() method, id="+id);
      resp.setHeader("Content-Type", "text/html");
      resp.setContentType("text/html");
     
     // System.out.println("Upload Image doGet() content type:"+resp.getContentType());
      resp.getWriter().println(id);

    }
}
