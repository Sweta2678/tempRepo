package com.tapestry.moic.controller;

import java.io.IOException;
import java.io.StringReader;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.URLCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.tapestry.moic.constant.MoicConstant;
import com.tapestry.moic.controller.interfaces.IAuthController;
import com.tapestry.moic.dto.TokenDto;
import com.tapestry.moic.utils.MoicUtil;

/**
 * The Class AuthController.
 *
 * @version 0.0.1
 */
@Component
public class AuthController implements IAuthController{

	/**
	 * Field LOGGER
	 */
	private Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
	
	@Value("${moic.sso.enabled}")
	private boolean isSSOEnabled;

	@Override
	public void login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, 
			String xml) {
		LOGGER.debug("Inside login method Received XML ::\n" + xml);
		xml = xml.replace("SAMLResponse=", "");
		xml = decodeXMLResponse(xml);
		
		String sessionIndex = null;
		TokenDto tokenDto = new TokenDto();
		try {
			if(null != xml && !xml.isEmpty()) {
				
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				 
				Document document;
				
				document = builder.parse(new InputSource(new StringReader(xml)));
				
				document.getDocumentElement().normalize();
						
				//Extract SessionIndex
				Element authnStatement = (Element) document.getElementsByTagName("AuthnStatement").item(0);
				sessionIndex = authnStatement.getAttribute("SessionIndex");
				
				//Extract required User attributes
				Element attributeStatement = (Element)  document.getElementsByTagName("AttributeStatement").item(0);
				NodeList  attributeNodeList = attributeStatement.getElementsByTagName("Attribute");
				for(int i = 0; i < attributeNodeList.getLength(); i++) {
					Element element = (Element) attributeNodeList.item(i);
					
					String attributeValue = element.getAttribute("Name");
					if(attributeValue.contains("emailaddress")) {
						tokenDto.setEmailAddress(element.getTextContent().trim());
						LOGGER.debug("emailaddress :: " + tokenDto.getEmailAddress());
					}
					else if(attributeValue.contains("displayname")) {
						tokenDto.setDisplayName(element.getTextContent().trim());
						LOGGER.debug("displayname :: " + tokenDto.getDisplayName());
					}
				}
			}
		
		} catch (SAXException | ParserConfigurationException | IOException e) {
			e.printStackTrace();
		}
		finally {
			String token = null;
			if(sessionIndex != null) {
				//user successfully authenticated
				token = MoicUtil.getEncryptedToken(MoicConstant.SESSION_TOKEN_ENCRYPTION_KEY, tokenDto);
			}
			LOGGER.debug("SessionIndex  found from xml :: " + sessionIndex);
	
			try {
				httpServletResponse.sendRedirect("/moic/#/landing?token=" + token);
			} catch (IOException e) {
				LOGGER.error("Something went wrong while auth redirection ::" + e.getMessage());
			}
		}
	}
	
	private String decodeXMLResponse(String encodedXML) {
		URLCodec encoder = new URLCodec();
	    String urlDecoded = null;
		try {
			urlDecoded = encoder.decode(encodedXML);
		} catch (DecoderException e) {
			e.printStackTrace();
		}
	    byte[] bytes = Base64.getDecoder().decode(urlDecoded);
	    String plainXML = new String(bytes);
	    LOGGER.debug("plainXML ::\n" + plainXML);
	    
	    return plainXML;
	}

	@Override
	public void test(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		try {
			if(!isSSOEnabled)
				httpServletResponse.sendRedirect("/moic/#/landing?token=L35pR0EHMwUeBRRqLAAKAwcHEGBLVkYBFg9dPgI0HBsENjAzM3xYKzAheidlTnhmdjB7EyE6JSsVIXcabHRyejZgQkFEADZlUEtZBygMcWUdFn8KTA==");
			else
				httpServletResponse.sendRedirect("/moic");
		} catch (IOException e) {
			LOGGER.error("Something went wrong while auth redirection ::" + e.getMessage());
		}
	}
}
