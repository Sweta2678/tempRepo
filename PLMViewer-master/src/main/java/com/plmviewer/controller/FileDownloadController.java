/**
 * 
 */
package com.plmviewer.controller;

/**
 * @author uthanasekarapandian
 *
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FileDownloadController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileDownloadController.class); 
		/**
		 * Size of a byte buffer to read/write file
		 */
		private static final int BUFFER_SIZE = 4096;
				
		/**
		 * Path of the file to be downloaded, relative to application's directory
		 */
		private String filePath = "/downloads/SpringProject.zip";
		
		/**
		 * Method for handling file download request from client
		 */
		@RequestMapping(path = "/userlogin/LandingLayout/getFileDownload", method = RequestMethod.GET ,
		produces=MediaType.APPLICATION_JSON_VALUE,	headers="Accept=application/json"	)
		public void downloadFile(HttpServletRequest request,
				HttpServletResponse response,@RequestParam("fileName") String fileName ) throws IOException {
logger.info("downloadFile Starts");
			// get absolute path of the application
			ServletContext context = request.getServletContext();
			String appPath = context.getRealPath("");
			logger.info("appPath = " + appPath);
          
			// construct the complete absolute path of the file
			String fullPath = appPath + filePath;	
			System.out.println("fullPath   :::  "+fullPath);
			String fileName1 =  request.getContextPath()+"/LCSWImagesDocuments/"+fileName;
			logger.info("filename "+fileName1);
			System.out.println("fileName   :::  "+fileName1);
			File downloadFile = new File(fileName1);
			FileInputStream inputStream = new FileInputStream(downloadFile);
			
			// get MIME type of the file
			String mimeType = context.getMimeType(fileName);
			if (mimeType == null) {
				// set to binary type if MIME mapping not found
				mimeType = "application/octet-stream";
			}
			System.out.println("MIME type: " + mimeType);

			// set content attributes for the response
			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());

			// set headers for the response
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",
					downloadFile.getName());
			response.setHeader(headerKey, headerValue);

			// get output stream of the response
			OutputStream outStream = response.getOutputStream();

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;

			// write bytes read from the input stream into the output stream
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}

			inputStream.close();
			outStream.close();

		}
	
	}

