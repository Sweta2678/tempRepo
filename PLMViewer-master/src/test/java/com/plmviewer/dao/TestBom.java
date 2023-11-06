/**
 * 
 */
package com.plmviewer.dao;
import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.plmviewer.controller.BOMController;
import com.plmviewer.controller.LandingController;
import com.plmviewer.controller.MaterialController;
import com.plmviewer.exceptions.BusinessException;
import com.plmviewer.model.BOMLink;
import com.plmviewer.model.BOMModel;
import com.plmviewer.model.BOMPart;
import com.plmviewer.model.MaterialReportVo;
import com.plmviewer.model.SkuInfoVo;
import com.plmviewer.service.BOMService;
import com.plmviewer.service.BOMServiceImpl;
import com.plmviewer.util.FlexObject;
import com.plmviewer.util.PLMUtil;
import com.plmviewer.util.SortHelper;

/**
 * @author uthanasekarapandian
 *
 */
@ContextConfiguration(locations = "classpath:PLMViewer-servlet-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestBom {
	 @Autowired
	    private BOMDao bomDao;
	 
	 @Autowired
	 	BOMServiceImpl bomServiceImpl;
	 
	 @Autowired
	 	MaterialController materialController;
	 
	 @Autowired
	 	MaterialDao materialDao;
	 
	 @Autowired
	 	ProductDao productDao;
	 
	 
	 
	 @Test
	 @Transactional
	 @Rollback(true)
	 public void testSkuInfoForaProduct() throws Exception{
//	System.out.println(materialController.gererateMaterialReport("L-12775-07", "D02", null).size());	
//			MaterialReportVo materialReportVo=new MaterialReportVo();
//			materialReportVo.setFlexbomlinkida2a2("177419510");
//			 materialReportVo.setFlexbomlinkdimensionid("-PARENT:wt.part.WTPartMaster:173652315-REV:A-BRANCH:2-SKU:wt.part.WTPartMaster:171520132");
//			materialReportVo.setFlexbomlinkdimensionname("");	
//			materialReportVo.setFlexbomlinkida3e5("171520132");	
//			materialReportVo.setLcsskuida3masterreference("171520132");	
//			
//			materialDao.mergeLinks(Arrays.asList(materialReportVo));
	 }
	 
	 
	
   
	 @Ignore
	public void test(){
		
	}
}
