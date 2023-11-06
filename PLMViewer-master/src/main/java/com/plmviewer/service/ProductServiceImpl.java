/**
 *
 */
package com.plmviewer.service;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plmviewer.dao.ProductDao;
import com.plmviewer.exceptions.BusinessException;
import com.plmviewer.model.DocumentObjectRelationship;
import com.plmviewer.model.ProductVo;
import com.plmviewer.model.ProductadditionDetailsVo;
import com.plmviewer.model.SKUaRevVo;
import com.plmviewer.model.SkuAdditionalDetailsVo;
import com.plmviewer.util.ProductDeptConstant;

/**
 * @author uthanasekarapandian
 *
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    ProductDao productDao;

    @Override
    public List<ProductVo> getProduct(String searchkey, String searchtype) throws Exception {
        List<ProductVo> productVoList = null;
        try {
            logger.info("getProductbyStyleNumber Starts");

            try {
                if (searchtype.equalsIgnoreCase("stylenumber")) {
                    productVoList = productDao.getProductbyStyleNumber(searchkey);
                } else {
                    productVoList = productDao.getProductbySlot(searchkey);
                }
            } catch (ParseException e1) {
                throw new BusinessException("Exception thorwing while parsing");
            }
            Map<String, String> productSource = productDao.getProductSource(searchkey, searchtype);
            Map<String, String> productSkuStyle = productDao.getSKUforStyle(searchkey, searchtype);
            List<String> productSpecification = productDao.getSpecification(searchkey, searchtype);
            List<String> productSeason = productDao.getProductSeason(searchkey, searchtype);
            logger.info("productVoList Size " + productVoList.size()
                    + "  :::  productSource Size " + productSource.size() + " ::: productSkuStyle Size " + productSkuStyle.size());

            if (productVoList.get(0).getCollection() != null && !productVoList.get(0).getCollection().trim().equals("")) {
                productVoList.get(0).setCollection(productDao.getAttributeValue("PRODUCT", "COLLECTION",
                        productVoList.get(0).getCollection()));
            }
            if (productVoList.get(0).getSubCollection() != null && !productVoList.get(0).getSubCollection().trim().equals("")) {
                productVoList.get(0).setSubCollection(productDao.getAttributeValue("PRODUCT", "SUBCOLLECTION",
                        productVoList.get(0).getSubCollection()));
            }
            if (productVoList.get(0).getProductClass() != null && !productVoList.get(0).getProductClass().trim().equals("")) {
                productVoList.get(0).setProductClass(productDao.getAttributeValue("PRODUCT", "CLASS",
                        productVoList.get(0).getProductClass()));
            }
            if (productVoList.get(0).getSubClass() != null && !productVoList.get(0).getSubClass().trim().equals("")) {
                productVoList.get(0).setSubClass(productDao.getAttributeValue("PRODUCT", "SUBCLASS",
                        productVoList.get(0).getSubClass()));
            }
            if (productVoList.get(0).getGender() != null && !productVoList.get(0).getGender().trim().equals("")) {
                productVoList.get(0).setGender(productDao.getAttributeValue("PRODUCT", "GENDER",
                        productVoList.get(0).getGender()));
            }

            if (productVoList.get(0).getIntroDate() != null) {
                productVoList.get(0).setIntroDate(productDao.getAttributeValue("PRODUCT", "INTRODATE", productVoList.get(0).getIntroDate()));
            }
            if (productVoList.get(0).getDeletedDate() != null) {
                productVoList.get(0).setDeletedDate(productDao.getAttributeValue("PRODUCT", "DELETEDATE", productVoList.get(0).getDeletedDate()));
            }
           
            productVoList.get(0).setProductSource(productSource);

            productVoList.get(0).setProductSkuStyle(productSkuStyle);
            productVoList.get(0).setProductSpecification(productSpecification);
            productVoList.get(0).setProductSeason(productSeason);
            String primaryUrl = productVoList.get(0).getPartPrimaryImageUrl();

            if (productVoList.get(0).getAdopted() != null && productVoList.get(0).getAdopted().equalsIgnoreCase("true")) {
                productVoList.get(0).setAdopted("Yes");
            } else {
                productVoList.get(0).setAdopted("No");
            }

            if (productVoList.get(0).getDropped() != null && productVoList.get(0).getDropped().equalsIgnoreCase("true")) {
                productVoList.get(0).setDropped("Yes");
            } else {
                productVoList.get(0).setDropped("No");
            }

            if (primaryUrl != null && !(primaryUrl.trim().equalsIgnoreCase("null")
                    || primaryUrl.trim().equals(""))) {
                String decodedUrl;
                try {
                    decodedUrl = java.net.URLDecoder.decode(primaryUrl, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new BusinessException(e);
                }
                productVoList.get(0).setPartPrimaryImageUrl(decodedUrl);
            }
            String dept = productVoList.get(0).getDepartment();
            if (dept != null && !dept.trim().equalsIgnoreCase("")) {
                ProductadditionDetailsVo productadditionDetailsVo
                        = productDao.getProductDataList(dept, searchkey, searchtype, getDepartmentType(dept));
                if (productadditionDetailsVo != null) {
                    if (productadditionDetailsVo.getGender() != null && !productadditionDetailsVo.getGender().trim().equals("")) {
                        productadditionDetailsVo.setGender(productDao.getAttributeValue("PRODUCT", "GENDER", productadditionDetailsVo.getGender()));
                    }
                    if (productadditionDetailsVo.getSilhouette() != null && !productadditionDetailsVo.getSilhouette().trim().equals("")) {
                        productadditionDetailsVo.setSilhouette(productDao.getAttributeValue("PRODUCT", "SILHOUETTE", productadditionDetailsVo.getSilhouette()));
                    }
                    if (productadditionDetailsVo.getSignatureType() != null && !productadditionDetailsVo.getSignatureType().trim().equals("")) {
                        productadditionDetailsVo.setSignatureType(productDao.getAttributeValue("PRODUCT", "LOGOTYPE", productadditionDetailsVo.getSignatureType()));
                    }
                    if (productadditionDetailsVo.getRounding() != null && !productadditionDetailsVo.getRounding().trim().equals("")) {
                        productadditionDetailsVo.setRounding(productDao.getAttributeValue("PRODUCT", "ROUNDING", productadditionDetailsVo.getRounding()));
                    }
                    if (productadditionDetailsVo.getFactorystoreTypeProduct() != null && !productadditionDetailsVo.getFactorystoreTypeProduct().trim().equals("")) {
                        productadditionDetailsVo.setFactorystoreTypeProduct(productDao.getAttributeValue("PRODUCT", "FACTORYSTORETYPEPRODUCT", productadditionDetailsVo.getFactorystoreTypeProduct()));
                    }
                    if (productadditionDetailsVo.getMaterial() != null && !productadditionDetailsVo.getMaterial().trim().equals("")) {
                        productadditionDetailsVo.setMaterial(productDao.getAttributeValue("PRODUCT", "MATERIAL", productadditionDetailsVo.getMaterial()));
                    }
                    if (productadditionDetailsVo.getDepartment() != null && !productadditionDetailsVo.getDepartment().trim().equals("")) {
                        productadditionDetailsVo.setDepartment(productDao.getAttributeValue("PRODUCT", "DEPARTMENT", productadditionDetailsVo.getDepartment()));
                    }
                    if (productadditionDetailsVo.getIDWindow() != null && !productadditionDetailsVo.getIDWindow().trim().equals("")) {
                        productadditionDetailsVo.setIDWindow(productDao.getAttributeValue("PRODUCT", "IDWINDOW", productadditionDetailsVo.getIDWindow()));

                    }
                    if (productadditionDetailsVo.getAttitude() != null && !productadditionDetailsVo.getAttitude().trim().equals("")) {
                        productadditionDetailsVo.setAttitude(productDao.getAttributeValue("PRODUCT", "ATTITUDE", productadditionDetailsVo.getAttitude()));
                    }
                    if (productadditionDetailsVo.getCalcWholeSale1() != null && !productadditionDetailsVo.getCalcWholeSale1().trim().equals("")) {
                        productadditionDetailsVo.setCalcWholeSale1(productDao.getAttributeValue("PRODUCT", "CalcWHPriceorWHMU", productadditionDetailsVo.getCalcWholeSale1()));
                    }
                    if (productadditionDetailsVo.getLook() != null && !productadditionDetailsVo.getLook().trim().equals("")) {
                        String look = productadditionDetailsVo.getLook();
                        StringBuffer buffer = new StringBuffer();
                        StringTokenizer tok = new StringTokenizer(look, "|~*~|");
                        String value = "";
                        while (tok.hasMoreTokens()) {
                            value = productDao.getAttributeValue("PRODUCT", "LOOK", tok.nextToken());
                            buffer.append(value.trim() + ",");
                        }
                        buffer.deleteCharAt(buffer.toString().lastIndexOf(","));
                        productadditionDetailsVo.setLook(buffer.toString());
                    }
                    if (productadditionDetailsVo.getUpdated() != null && !productadditionDetailsVo.getUpdated().trim().equals("")) {
                        if (productadditionDetailsVo.getUpdated().equalsIgnoreCase("yes")) {
                            productadditionDetailsVo.setUpdated("Yes");
                        } else {
                            productadditionDetailsVo.setUpdated("No");
                        }
                    }
                    if (productadditionDetailsVo.getGcc() != null && !productadditionDetailsVo.getGcc().trim().equals("")) {
                        if (productadditionDetailsVo.getGcc().equalsIgnoreCase("yes")) {
                            productadditionDetailsVo.setGcc("Yes");
                        } else {
                            productadditionDetailsVo.setGcc("No");
                        }
                    }
                    /**
                     * ***************************
                     */
                    if (productadditionDetailsVo.getProductidentity() != null && !productadditionDetailsVo.getProductidentity().trim().equals("")) {
                        productadditionDetailsVo.setProductidentity(productDao.getAttributeValue("PRODUCT", "PRODUCTIDENTITY", productadditionDetailsVo.getProductidentity()));
                    }
                    if (productadditionDetailsVo.getCrossBody() != null && !productadditionDetailsVo.getCrossBody().trim().equals("")) {
                        productadditionDetailsVo.setCrossBody(productDao.getAttributeValue("PRODUCT", "CROSSBODY", productadditionDetailsVo.getCrossBody()));
                    }
                    if (productadditionDetailsVo.getChannelExclusive() != null && !productadditionDetailsVo.getChannelExclusive().trim().equals("")) {
                        productadditionDetailsVo.setChannelExclusive(productDao.getAttributeValue("PRODUCT", "CHANNELEXCLUSIVE", productadditionDetailsVo.getChannelExclusive()));
                    }
                    if (productadditionDetailsVo.getHandbagSize() != null && !productadditionDetailsVo.getHandbagSize().trim().equals("")) {
                        productadditionDetailsVo.setHandbagSize(productDao.getAttributeValue("PRODUCT", "HANDBAGSIZE", productadditionDetailsVo.getHandbagSize()));
                    }
                    if (productadditionDetailsVo.getCostAgreement() != null && !productadditionDetailsVo.getCostAgreement().trim().equals("")) {
                        productadditionDetailsVo.setCostAgreement(productDao.getAttributeValue("PRODUCT", "COSTAGREEMENT", productadditionDetailsVo.getCostAgreement()));
                    }
                    if (productadditionDetailsVo.getPlanExclusion() != null && !productadditionDetailsVo.getPlanExclusion().trim().equals("")) {
                        productadditionDetailsVo.setPlanExclusion(productDao.getAttributeValue("PRODUCT", "PLANEXCLUSION", productadditionDetailsVo.getPlanExclusion()));
                    }
                    if (productadditionDetailsVo.getStyleGroup() != null && !productadditionDetailsVo.getStyleGroup().trim().equals("")) {
                        productadditionDetailsVo.setStyleGroup(productDao.getAttributeValue("PRODUCT", "STYLEGROUP", productadditionDetailsVo.getStyleGroup()));
                    }
                    if (productadditionDetailsVo.getAttitudinalSegments() != null && !productadditionDetailsVo.getAttitudinalSegments().trim().equals("")) {
                        productadditionDetailsVo.setAttitudinalSegments(productDao.getAttributeValue("PRODUCT", "ATTITUDINALSEGMENTS", productadditionDetailsVo.getAttitudinalSegments()));
                    }
                    if (productadditionDetailsVo.getCollaboration() != null && !productadditionDetailsVo.getCollaboration().trim().equals("")) {
                        productadditionDetailsVo.setCollaboration(productDao.getAttributeValue("PRODUCT", "COLLABORATION", productadditionDetailsVo.getCollaboration()));
                    }
                    if (productadditionDetailsVo.getHardwareColor() != null && !productadditionDetailsVo.getHardwareColor().trim().equals("")) {
                        productadditionDetailsVo.setHardwareColor(productDao.getAttributeValue("PRODUCT", "HARDWARECOLOR", productadditionDetailsVo.getHardwareColor()));
                    }

                    if (productadditionDetailsVo.getEmblishment() != null && !productadditionDetailsVo.getEmblishment().trim().equals("")) {
                        productadditionDetailsVo.setEmblishment(productDao.getAttributeValue("PRODUCT", "EMBELLISHMENT", productadditionDetailsVo.getEmblishment()));
                    }
                    if (productadditionDetailsVo.getOneTimeBuy() != null && !productadditionDetailsVo.getOneTimeBuy().trim().equals("")) {
                        productadditionDetailsVo.setOneTimeBuy(productDao.getAttributeValue("PRODUCT", "ONETIMEBUY", productadditionDetailsVo.getOneTimeBuy()));
                    }
                    if (productadditionDetailsVo.getProductSegmentation() != null && !productadditionDetailsVo.getProductSegmentation().trim().equals("")) {
                        productadditionDetailsVo.setProductSegmentation(productDao.getAttributeValue("PRODUCT", "PRODUCTSEGMENTATION", productadditionDetailsVo.getProductSegmentation()));
                    }
                    if (productadditionDetailsVo.getPint() != null && !productadditionDetailsVo.getPint().trim().equals("")) {
                        productadditionDetailsVo.setPint(productDao.getAttributeValue("PRODUCT", "PRINT", productadditionDetailsVo.getPint()));
                    }
                    if (productadditionDetailsVo.getGifting() != null && !productadditionDetailsVo.getGifting().trim().equals("")) {
                        productadditionDetailsVo.setGifting(productDao.getAttributeValue("PRODUCT", "GIFTING", productadditionDetailsVo.getGifting()));
                    }
                    if (productadditionDetailsVo.getLongLeadTimeMaterial() != null && !productadditionDetailsVo.getLongLeadTimeMaterial().trim().equals("")) {
                        productadditionDetailsVo.setLongLeadTimeMaterial(productDao.getAttributeValue("PRODUCT", "LONGLEADTIME", productadditionDetailsVo.getLongLeadTimeMaterial()));
                    }
                    if (productadditionDetailsVo.getSpecialProduct() != null && !productadditionDetailsVo.getSpecialProduct().trim().equals("")) {
                        productadditionDetailsVo.setSpecialProduct(productDao.getAttributeValue("PRODUCT", "SPECIALPRODUCT", productadditionDetailsVo.getSpecialProduct()));
                    }
                    if (productadditionDetailsVo.getOrganizationalPanel() != null && !productadditionDetailsVo.getOrganizationalPanel().trim().equals("")) {
                        productadditionDetailsVo.setOrganizationalPanel(productDao.getAttributeValue("PRODUCT", "ORGANIZATIONALPANEL", productadditionDetailsVo.getOrganizationalPanel()));
                    }

                    if (productadditionDetailsVo.getTotoBillfoldPockets() != null && !productadditionDetailsVo.getTotoBillfoldPockets().trim().equals("")) {
                        productadditionDetailsVo.setTotoBillfoldPockets(productDao.getAttributeValue("PRODUCT", "TOTOBILLFOLDPOCKETS", productadditionDetailsVo.getTotoBillfoldPockets()));
                    }
                    if (productadditionDetailsVo.getCreditCardPocket() != null && !productadditionDetailsVo.getCreditCardPocket().trim().equals("")) {
                        productadditionDetailsVo.setCreditCardPocket(productDao.getAttributeValue("PRODUCT", "CREDITCARDPOCKET", productadditionDetailsVo.getCreditCardPocket()));
                    }
                    if (productadditionDetailsVo.getCoin() != null && !productadditionDetailsVo.getCoin().trim().equals("")) {
                        productadditionDetailsVo.setCoin(productDao.getAttributeValue("PRODUCT", "COIN", productadditionDetailsVo.getCoin()));
                    }
                    if (productadditionDetailsVo.getKeyringDimensions() != null && !productadditionDetailsVo.getKeyringDimensions().trim().equals("")) {
                        productadditionDetailsVo.setKeyringDimensions(productDao.getAttributeValue("PRODUCT", "KEYRINGDIMENSIONS", productadditionDetailsVo.getKeyringDimensions()));
                    }
                    if (productadditionDetailsVo.getClosures() != null && !productadditionDetailsVo.getClosures().trim().equals("")) {
                        productadditionDetailsVo.setClosures(productDao.getAttributeValue("PRODUCT", "CLOSURE", productadditionDetailsVo.getClosures()));
                    }
                    if (productadditionDetailsVo.getIDWindow() != null && !productadditionDetailsVo.getIDWindow().trim().equals("")) {
                        productadditionDetailsVo.setIDWindow(productDao.getAttributeValue("PRODUCT", "IDWINDOW", productadditionDetailsVo.getIDWindow()));
                    }
                    if (productadditionDetailsVo.getCoin() != null && !productadditionDetailsVo.getCoin().trim().equals("")) {
                        productadditionDetailsVo.setCoin(productDao.getAttributeValue("PRODUCT", "COIN", productadditionDetailsVo.getCoin()));
                    }
                    if (productadditionDetailsVo.getSignatureType() != null && !productadditionDetailsVo.getSignatureType().trim().equals("")) {
                        productadditionDetailsVo.setSignatureType(productDao.getAttributeValue("PRODUCT", "LOGOTYPE", productadditionDetailsVo.getSignatureType()));
                    }
                    if (productadditionDetailsVo.getLens() != null && !productadditionDetailsVo.getLens().trim().equals("")) {
                        productadditionDetailsVo.setLens(productDao.getAttributeValue("PRODUCT", "LENSTYPE", productadditionDetailsVo.getLens()));
                    }
                    if (productadditionDetailsVo.getFunction() != null && !productadditionDetailsVo.getFunction().trim().equals("")) {
                        productadditionDetailsVo.setFunction(productDao.getAttributeValue("PRODUCT", "FUNCTIONALITY", productadditionDetailsVo.getFunction()));
                    }
                    if (productadditionDetailsVo.getFacecolor() != null && !productadditionDetailsVo.getFacecolor().trim().equals("")) {
                        productadditionDetailsVo.setFacecolor(productDao.getAttributeValue("SKU", "FACECOLOR", productadditionDetailsVo.getFacecolor()));
                    }
                    if (productadditionDetailsVo.getWatchCaseShape() != null && !productadditionDetailsVo.getWatchCaseShape().trim().equals("")) {
                        productadditionDetailsVo.setWatchCaseShape(productDao.getAttributeValue("PRODUCT", "CASESHAPE", productadditionDetailsVo.getWatchCaseShape()));
                    }
                    if (productadditionDetailsVo.getWatchCaseSize() != null && !productadditionDetailsVo.getWatchCaseSize().trim().equals("")) {
                        productadditionDetailsVo.setWatchCaseSize(productDao.getAttributeValue("PRODUCT", "CASESIZE", productadditionDetailsVo.getWatchCaseSize()));
                    }
                    if (productadditionDetailsVo.getToeshape() != null && !productadditionDetailsVo.getToeshape().trim().equals("")) {
                        productadditionDetailsVo.setToeshape(productDao.getAttributeValue("PRODUCT", "TOESHAPE", productadditionDetailsVo.getToeshape()));
                    }
                    if (productadditionDetailsVo.getPlanExclusion() != null && !productadditionDetailsVo.getPlanExclusion().trim().equals("")) {
                        productadditionDetailsVo.setPlanExclusion(productDao.getAttributeValue("PRODUCT", "PLANEXCLUSION", productadditionDetailsVo.getPlanExclusion()));
                    }

                    if (productadditionDetailsVo.getSoletype() != null && !productadditionDetailsVo.getSoletype().trim().equals("")) {
                        productadditionDetailsVo.setSoletype(productDao.getAttributeValue("PRODUCT", "SOLETYPE", productadditionDetailsVo.getSoletype()));
                    }

                    if (productadditionDetailsVo.getHeelHeight1() != null && !productadditionDetailsVo.getHeelHeight1().trim().equals("")) {
                        productadditionDetailsVo.setHeelHeight1(productDao.getAttributeValue("PRODUCT", "HEELHEIGHT", productadditionDetailsVo.getHeelHeight1()));
                    }

                    if (productadditionDetailsVo.getToetype() != null && !productadditionDetailsVo.getToetype().trim().equals("")) {
                        productadditionDetailsVo.setToetype(productDao.getAttributeValue("PRODUCT", "TOETYPE", productadditionDetailsVo.getToetype()));
                    }

                    /******************************/
                    if (productadditionDetailsVo.getTeamTechnicalDesigner()!= null && productadditionDetailsVo.getTeamTechnicalDesigner().trim().equals("0")) {
                        productadditionDetailsVo.setTeamTechnicalDesigner("");
                    }
                    
//                    if (productadditionDetailsVo.getTeamTechnicalDesigner()!= null && productadditionDetailsVo.getTeamTechnicalDesigner().trim().equals("0")) {
//                        productadditionDetailsVo.setTeamTechnicalDesigner("");
//                    }
                    
                    /******************************/
                    /**
                     * ***************************
                     */
                }
                productVoList.get(0).setProductadditionDetailsVo(productadditionDetailsVo);
            }

            productVoList.get(0).setMerchandisingFullPrice(productDao.getMerchandisingPricingList(searchkey, searchtype, ProductDeptConstant.merchFullPrice));
            productVoList.get(0).setMerchandisingCJIPrice(productDao.getMerchandisingPricingList(searchkey, searchtype, ProductDeptConstant.merchCJIPrice));
            // productVoList.get(0).setMerchandisingFactoryPrice(productDao.getMerchandisingPricingList(searchkey, searchtype, ProductDeptConstant.merchandisingFactoryPrice));
            try {

                if (productVoList.get(0).getProductadditionDetailsVo().getMerchCJIPrices() != null && !productVoList.get(0).getProductadditionDetailsVo().getMerchCJIPrices().trim().equals("")) {
                    String value = productVoList.get(0).getProductadditionDetailsVo().getMerchCJIPrices();

                    String val = productDao.getAttributeValue("PRODUCT", "CJIPRICES", value);
                    productVoList.get(0).getProductadditionDetailsVo().setMerchCJIPrices(val);

                }

                if (productVoList.get(0).getProductadditionDetailsVo().getMerchFactoryPrices() != null && !productVoList.get(0).getProductadditionDetailsVo().getMerchFactoryPrices().trim().equals("")) {
                    String value = productVoList.get(0).getProductadditionDetailsVo().getMerchFactoryPrices();
                    String val = productDao.getAttributeValue("PRODUCT", "FACTORYPRICES", value);
                    productVoList.get(0).getProductadditionDetailsVo().setMerchFactoryPrices(val);

                }
                if (productVoList.get(0).getProductadditionDetailsVo().getCrossBody() != null && !productVoList.get(0).getProductadditionDetailsVo().getCrossBody().trim().equals("")) {
                    String value = productVoList.get(0).getProductadditionDetailsVo().getCrossBody();
                    String val = productDao.getAttributeValue("PRODUCT", "CROSSBODY", value);
                    System.out.println("Crossbody ::::: " + val);
                    productVoList.get(0).getProductadditionDetailsVo().setCrossBody(val);

                }
                if (productVoList.get(0).getProductadditionDetailsVo().getGifting() != null && !productVoList.get(0).getProductadditionDetailsVo().getGifting().trim().equals("")) {
                    String value = productVoList.get(0).getProductadditionDetailsVo().getGifting();
                    String val = productDao.getAttributeValue("PRODUCT", "GIFTING", value);
                    productVoList.get(0).getProductadditionDetailsVo().setGifting(val);

                }

            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }

            productVoList.get(0).setMerchandisingFactoryPrice(productDao.getMerchandisingPricingList(searchkey, searchtype, ProductDeptConstant.merchFactoryPrice));

            if (productVoList.get(0).getMerchandisingFactoryPrice().get(0) != null && productVoList.get(0).getMerchandisingFactoryPrice().get(0).getRounding() != null) {
                String value = productVoList.get(0).getMerchandisingFactoryPrice().get(0).getRounding();
                String val = productDao.getAttributeValue("PRODUCT", "ROUNDING", value);
                if (val != null) {
                    productVoList.get(0).getMerchandisingFactoryPrice().get(0).setRounding(val);
                }

            }

            productVoList.get(0).setSkuGroupTable(productDao.getSkuGroup(searchkey, searchtype));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        logger.info("getProductbyStyleNumber Ends");
        return productVoList;
    }

    private String getDepartmentType(String deptCode) {
        String deptType = null;
        if (deptCode.equalsIgnoreCase("D01") || deptCode.equalsIgnoreCase("D02") || deptCode.equalsIgnoreCase("D03")
                || deptCode.equalsIgnoreCase("D04") || deptCode.equalsIgnoreCase("D05") || deptCode.equalsIgnoreCase("D06")
                || deptCode.equalsIgnoreCase("D08") || deptCode.equalsIgnoreCase("D10") || deptCode.equalsIgnoreCase("D14")
                || deptCode.equalsIgnoreCase("D23")) {
            deptType = ProductDeptConstant.accessoriesDepartType;
        } else if (deptCode.equalsIgnoreCase("D11") || deptCode.equalsIgnoreCase("D19")) {
            deptType = ProductDeptConstant.footWearDepartType;
        } else if (deptCode.equalsIgnoreCase("D17") || deptCode.equalsIgnoreCase("D21")) {
            deptType = ProductDeptConstant.fragranceDepartType;
        } else if (deptCode.equalsIgnoreCase("D13")) {
            deptType = ProductDeptConstant.jewelryDepartType;
        } else if (deptCode.equalsIgnoreCase("D12") || deptCode.equalsIgnoreCase("D20")) {
            deptType = ProductDeptConstant.sunWearDepartType;
        } else if (deptCode.equalsIgnoreCase("D09") || deptCode.equalsIgnoreCase("D18")) {
            deptType = ProductDeptConstant.watchesDepartType;
        } else {
            deptType = ProductDeptConstant.wearablesDepartType;
        }

        return deptType;

    }

    public List<ProductVo> getProductList(String searchkey, String searchtype) throws Exception {
        logger.info("getProductList Starts");
        List<ProductVo> productVoList = null;
        if (searchtype.equals("stylenumber")) {
            productVoList = productDao.getProductbyStyleNumber(searchkey);
        } else {
            productVoList = productDao.getProductbySlot(searchkey);
        }
        logger.info("productVoList Size " + productVoList.size());
        logger.info("getProductList Ends");
        return productVoList;
    }

    @Override
    public Map<String, String> getProductSkuDetails(String searchkey, String searchtype, String skuStyleName) throws Exception {
        logger.info("getProductSkuDetails Starts");
        Map<String, String> SkuaRevVoList = productDao.getskuDetails(searchkey, searchtype, skuStyleName);

        logger.info("SkuaRevVoList Size " + SkuaRevVoList.size());
        logger.info("getProductSkuDetails Ends");
        return SkuaRevVoList;
    }

    public SkuAdditionalDetailsVo getProductSkuAdditionalDetails(String searchkey, String searchtype, String skuStyleName, String deptCode) throws Exception {
        logger.info("getProductSkuAdditionalDetails Starts");
        SkuAdditionalDetailsVo skuAdditionalDetailsVo = null;

        if (deptCode.equalsIgnoreCase("D01") || deptCode.equalsIgnoreCase("D02") || deptCode.equalsIgnoreCase("D03")
                || deptCode.equalsIgnoreCase("D04") || deptCode.equalsIgnoreCase("D05") || deptCode.equalsIgnoreCase("D06")
                || deptCode.equalsIgnoreCase("D08") || deptCode.equalsIgnoreCase("D10") || deptCode.equalsIgnoreCase("D14")
                || deptCode.equalsIgnoreCase("D23") || deptCode.equalsIgnoreCase("D07") || deptCode.equalsIgnoreCase("D15")
                || deptCode.equalsIgnoreCase("D16") || deptCode.equalsIgnoreCase("D22")) {
            skuAdditionalDetailsVo = productDao.getSkuAccessoriesDeptsql(searchkey, searchtype, deptCode, skuStyleName, ProductDeptConstant.accessoriesDepartType);

        } else if (deptCode.equalsIgnoreCase("D09") || deptCode.equalsIgnoreCase("D18")) {

            skuAdditionalDetailsVo = productDao.getSkuAccessoriesDeptsql(searchkey, searchtype, deptCode, skuStyleName, ProductDeptConstant.footWearDepartType);
        } else if (deptCode.equalsIgnoreCase("D17") || deptCode.equalsIgnoreCase("D21")
                || deptCode.equalsIgnoreCase("D12") || deptCode.equalsIgnoreCase("D20")) {
            skuAdditionalDetailsVo = productDao.getSkuAccessoriesDeptsql(searchkey, searchtype, deptCode, skuStyleName, ProductDeptConstant.fragranceDepartType);
        } else {
            skuAdditionalDetailsVo = productDao.getSkuAccessoriesDeptsql(searchkey, searchtype, deptCode, skuStyleName, deptCode);
        }

        //productDao.getAttributelist(attributelevel, attributename)
        skuAdditionalDetailsVo.setMaterial(productDao.getAttributeValue("PRODUCT", "MATERIAL", skuAdditionalDetailsVo.getMaterial()));
        skuAdditionalDetailsVo.setChannelExclusive(productDao.getAttributeValue("PRODUCT", "CHANNELEXCLUSIVE", skuAdditionalDetailsVo.getChannelExclusive()));
        skuAdditionalDetailsVo.setFacecolor(productDao.getAttributeValue("SKU", "FACECOLOR", skuAdditionalDetailsVo.getFacecolor()));

        skuAdditionalDetailsVo.setLongLeadTimeMaterial(productDao.getAttributeValue("PRODUCT", "LONGLEADTIME", skuAdditionalDetailsVo.getLongLeadTimeMaterial()));

        skuAdditionalDetailsVo.setOneTimeBuy(productDao.getAttributeValue("PRODUCT", "ONETIMEBUY", skuAdditionalDetailsVo.getOneTimeBuy()));

        skuAdditionalDetailsVo.setIntroDate(productDao.getAttributeValue("PRODUCT", "INTRODATE", skuAdditionalDetailsVo.getIntroDate()));
        skuAdditionalDetailsVo.setFsIntroDate(productDao.getAttributeValue("PRODUCT", "FSINTRODATE", skuAdditionalDetailsVo.getFsIntroDate()));
        skuAdditionalDetailsVo.setDeleteDate(productDao.getAttributeValue("PRODUCT", "DELETEDATE", skuAdditionalDetailsVo.getDeleteDate()));
        skuAdditionalDetailsVo.setFsDeleteDate(productDao.getAttributeValue("PRODUCT", "FSDELETEDATE", skuAdditionalDetailsVo.getFsDeleteDate()));
        skuAdditionalDetailsVo.setStatus(productDao.getAttributeValue("SKU", "STATUS", skuAdditionalDetailsVo.getStatus()));
        skuAdditionalDetailsVo.setExotic(productDao.getAttributeValue("PRODUCT", "EXOTIC", skuAdditionalDetailsVo.getExotic()));
        skuAdditionalDetailsVo.setOneTimeBuy(productDao.getAttributeValue("PRODUCT", "ONETIMEBUY", skuAdditionalDetailsVo.getOneTimeBuy()));
        skuAdditionalDetailsVo.setFinishedGoodColorCodes(skuAdditionalDetailsVo.getSkuStyleName());
        skuAdditionalDetailsVo.setCalcWholeSale1(productDao.getAttributeValue("PRODUCT", "CalcWHPriceorWHMU", skuAdditionalDetailsVo.getCalcWholeSale1()));
        skuAdditionalDetailsVo.setSignatureType(productDao.getAttributeValue("PRODUCT", "LOGOTYPE", skuAdditionalDetailsVo.getSignatureType()));
        skuAdditionalDetailsVo.setAbcCode(productDao.getAttributeValue("PRODUCT", "ABCCODE", skuAdditionalDetailsVo.getAbcCode()));
        skuAdditionalDetailsVo.setFactorystoreTypeSku(productDao.getAttributeValue("PRODUCT", "FACTORYSTORETYPEPRODUCT", skuAdditionalDetailsVo.getFactorystoreTypeSku()));

        skuAdditionalDetailsVo.setHardwareColor(productDao.getAttributeValue("PRODUCT", "HARDWARECOLOR", skuAdditionalDetailsVo.getHardwareColor()));

        if (skuAdditionalDetailsVo.getLook() != null && !skuAdditionalDetailsVo.getLook().trim().equals("")) {
            String look = skuAdditionalDetailsVo.getLook();
            StringBuffer buffer = new StringBuffer();
            StringTokenizer tok = new StringTokenizer(look, "|~*~|");
            String value = "";
            while (tok.hasMoreTokens()) {
                value = productDao.getAttributeValue("PRODUCT", "LOOK", tok.nextToken());
                buffer.append(value.trim() + ",");
            }
            buffer.deleteCharAt(buffer.toString().lastIndexOf(","));
            skuAdditionalDetailsVo.setLook(buffer.toString());
        }
        skuAdditionalDetailsVo.setMerchandisingFullPrice(productDao.getSkuMerchandisingPrice(searchkey, searchtype, skuStyleName));
        skuAdditionalDetailsVo.setSizeScaleMoa(productDao.getSkuSizeScaleMoa(searchkey, searchtype, skuStyleName));
   /******************************/
        logger.info("getProductSkuAdditionalDetails Ends");
        return skuAdditionalDetailsVo;
    }

    public Map<String, String> getAttribute(String attrtype, String attrname) throws Exception {

        logger.info("getAttribute Starts");
        Map<String, String> attributelist = productDao.getAttributelist(attrtype, attrname);
        logger.info("getAttribute Ends");
        return attributelist;
    }

    public List<DocumentObjectRelationship> getProductDocumentDetails(String prodNum, String type) throws Exception {
        logger.info("getProductDocumentDetails Starts");
        List<DocumentObjectRelationship> prodDocumentList
                = productDao.getProductDocumentDetails(prodNum, type);

        logger.info("getProductDocumentDetails Ends");
        return prodDocumentList;
    }

	@Override
	public Map<String, String> initReportLoad() {
		
		return productDao.initReportLoad();
	}

}
