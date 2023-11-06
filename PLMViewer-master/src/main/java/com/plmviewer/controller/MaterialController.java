/**
 *
 */
package com.plmviewer.controller;

import com.plmviewer.dao.UserActionDAO;
import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plmviewer.model.MaterialColorDetail;
import com.plmviewer.model.MaterialColorSearchResultVo;
import com.plmviewer.model.MaterialPricingSearchResult;
import com.plmviewer.model.MaterialReportVo;
import com.plmviewer.model.MaterialResultVo;
import com.plmviewer.model.MaterialSearchVo;
import com.plmviewer.model.MaterialSupplierVo;
import com.plmviewer.model.MaterialVo;
import com.plmviewer.model.User;
import com.plmviewer.model.UserAction;
import com.plmviewer.service.MaterialService;
import com.plmviewer.util.MaterialConstant;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import javax.print.DocFlavor.STRING;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author uthanasekarapandian
 *
 */
@Controller
public class MaterialController {

    private static final Logger logger = LoggerFactory
            .getLogger(MaterialController.class);

    @Autowired
    MaterialService materialService;
    @Autowired
    UserActionDAO useractionimpl;

    @RequestMapping(path = "/userlogin/LandingLayout/getMaterialSearchList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public @ResponseBody
    List<MaterialResultVo> getMaterialList(
            @RequestBody MaterialSearchVo materialSearchVo, HttpSession session) throws Exception {
        logger.info("getMaterialList Starts");
        List<MaterialResultVo> materialReultList = materialService
                .getMaterialList(materialSearchVo);
        addUserDetails(session.getAttribute("user"), "MATERIAL_SEARCH");
        logger.info("getMaterialList Ends");
        return materialReultList;

    }

    @RequestMapping(path = "/userlogin/LandingLayout/getMaterialData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public @ResponseBody
    MaterialVo getMaterialData(
            @RequestParam("partNumber") String searchkey,
            @RequestParam("materialTypeId") String materialTypeId,
            @RequestParam("materialsearchtype") String searchtype, HttpSession session)
            throws Exception {
        logger.info("getMaterialData Starts");
        MaterialVo materialVo = materialService.getMaterialData(searchkey,
                searchtype, getMaterialType(materialTypeId));
        addUserDetails(session.getAttribute("user"), "MATERIAL_DETAIL" );
        logger.info("getMaterialData Ends");
        return materialVo;
    }

    @RequestMapping(path = "/userlogin/searchLayout/materialinitLoad", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public @ResponseBody
    Map<String, Map<String, String>> mateerialSearchInitLoad(
            @RequestParam("attributelevel") String attributelevel,
            @RequestParam("attributename") String attributename, HttpSession session)
            throws Exception {
        logger.info("advSearchInitLoad Starts");
        System.out.println("materialsearchService attributelevel :: "
                + attributelevel);
        System.out.println("materialsearchService attributename :: "
                + attributename);

        return materialService.getmaterialattribute(attributelevel,
                attributename);
    }

    @RequestMapping(path = "/userlogin/LandingLayout/getMaterialSupplier", method = RequestMethod.GET)
    public @ResponseBody
    MaterialSupplierVo getMaterialSupplierDetails(
            @RequestParam("ida3MasterRef") BigDecimal ida3MasterRef,
            @RequestParam("materialTypeId") String materialTypeId, HttpSession session)
            throws Exception {
        logger.info("MaterialController getMaterialSupplierDetails");
        logger.debug("ida3MasterRef::  " + ida3MasterRef
                + " ::  Material type ::  " + materialTypeId);

        MaterialSupplierVo materialSupplierVo = materialService
                .getMaterialSupplierVo(ida3MasterRef,
                        getMaterialType(materialTypeId));
        addUserDetails(session.getAttribute("user"), "MATERIAL_SUPPLIER" );
        logger.info("MaterialController getMaterialSupplierDetails");
        return materialSupplierVo;
    }

    @RequestMapping(path = "/userlogin/LandingLayout/getMaterialColorDetail", method = RequestMethod.GET)
    public @ResponseBody
    MaterialColorDetail getMaterialColorDetails(
            @RequestParam("colorId") BigDecimal colorId, HttpSession session) throws Exception {
        logger.info("MaterialController getMaterialColorDetails");
        logger.debug("Color Id ::  " + colorId);

        MaterialColorDetail materialColorDetail = materialService
                .getMaterialColorDetails(colorId);
        addUserDetails(session.getAttribute("user"), "MATERIAL_COLOUR_DETAIL");
        logger.info("MaterialController getMaterialColorDetails");
        return materialColorDetail;
    }

    @RequestMapping(path = "/userlogin/LandingLayout/getMaterialColorSearchResult", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public @ResponseBody
    List<MaterialColorSearchResultVo> getMaterialColorSearchResult(
            @RequestParam("partNumber") String partNumber, HttpSession session) throws Exception {
        logger.info("getMaterialColorSearchResult Starts");
        List<MaterialColorSearchResultVo> materialColorSearchResultVoList = materialService
                .getMaterialColorSearch(partNumber);
        addUserDetails(session.getAttribute("user"), "MATERIAL_COLOUR");
        logger.info("getMaterialColorSearchResult Ends");
        return materialColorSearchResultVoList;
    }

    @RequestMapping(path = "/userlogin/LandingLayout/getMaterialPriceSearchResult", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public @ResponseBody
    List<MaterialPricingSearchResult> getMaterialPriceSearchResult(
            @RequestParam("ida3MasterRef") BigDecimal supplierId, HttpSession session)
            throws Exception {
        logger.info("getMaterialColorSearchResult Starts");
        List<MaterialPricingSearchResult> materialPriceSearchResultVoList = materialService
                .getMaterialPricingList(supplierId);
        addUserDetails(session.getAttribute("user"), "MATERIAL_PRICING");
        logger.info("getMaterialColorSearchResult Ends");
        return materialPriceSearchResultVoList;
    }

    private String getMaterialType(String materialTypeId) {
        String materialType = "";
        if (materialTypeId.equalsIgnoreCase("6648")) {
            materialType = MaterialConstant.EdgeStainType;
        } else if (materialTypeId.equalsIgnoreCase("6675")) {
            materialType = MaterialConstant.EnamelType;
        } else if (materialTypeId.equalsIgnoreCase("6797")) {
            materialType = MaterialConstant.FillerType;
        } else if (materialTypeId.equalsIgnoreCase("6868")) {
            materialType = MaterialConstant.HardwareType;
        } else if (materialTypeId.equalsIgnoreCase("7084")) {
            materialType = MaterialConstant.LeatherType;
        } else if (materialTypeId.equalsIgnoreCase("7323")) {
            materialType = MaterialConstant.MiscType;
        } else if (materialTypeId.equalsIgnoreCase("7396")) {
            materialType = MaterialConstant.PackagingType;
        } else if (materialTypeId.equalsIgnoreCase("7578")) {
            materialType = MaterialConstant.TextileType;
        } else if (materialTypeId.equalsIgnoreCase("7743")) {
            materialType = MaterialConstant.ThreadType;
        } else if (materialTypeId.equalsIgnoreCase("7802")) {
            materialType = MaterialConstant.WovenTrimType;
        } else if (materialTypeId.equalsIgnoreCase("8038")) {
            materialType = MaterialConstant.ZipperType;
        }
        return materialType;
    }

    private void addUserDetails(Object username, String useraction) {
        System.out.println("For action ********* " + useraction);
        UserAction action = new UserAction();
        action.setUsername(((User) username).getUserName());
        action.setAction(useraction);
        action.setActiontime(new Timestamp(new Date().getTime()));
        useractionimpl.addaction(action);
    }
    
    @RequestMapping(path = "/userlogin/LandingLayout/gererateMaterialReport", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public @ResponseBody
    List<MaterialReportVo> gererateMaterialReport(
    		  @RequestParam("cmnumber") String cmnumber,
    		  @RequestParam("username") String username,
    		  @RequestParam("email") String email,
              @RequestParam("Department") String department, HttpSession session)
            throws Exception {
       System.out.println("gererateMaterialReport Starts with cmnumber = "+cmnumber+" username ="+username+" email="+email+" Department="+department);
       User user=new User();  
       user.setEmail(email);
       user.setUserName(username);        
        List<MaterialReportVo> list=   materialService.gererateMaterialReport(cmnumber, department,user);
        addUserDetails(session.getAttribute("user"), "MATERIAL_REPORT");
        System.out.println(list.size());
        return list;
    }
    
}
