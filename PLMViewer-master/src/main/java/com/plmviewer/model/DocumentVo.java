/**
 *
 */
package com.plmviewer.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author uthanasekarapandian
 *
 */
public class DocumentVo {

    private String documentName;
    private BigDecimal ida3masterreference;
    private String department;
    private String documentType;
    private String createDate;
    private String updateDate;
    private String accessoriesdepartment;
    private String engConstdtl;
    private String silhouette;
    private String collection;
    private String introDate;
    private String subCollection;
    private String docClass;
    private String internalOffice;
    private String subClass;
    private String season;
    private String serviceProvider;
    private String intendedUsers;
    private String theClass;
    private String wearDept;
    private String wearSilhouette;
    private String finalSPpattern;
    private String singleintro;
    private String singleSeason;
    private String stylenumberdescription;
    private String prnType;
    private String testResultsMaintainedBy;
    private String supplier;
    private String year;
    private String cmNumber;
    private String cocExpiration;
    private String externalAnalyticalStatus;
    private String externalAnalyticalStatusDate;
    private String externalTestingFacility;
    private String externaStatuslTestingType;
    private String internalXFStatus;
    private String internalXRFTestDate;
    private String osilnspectionDate;
    private BigDecimal osiScore;
    private String reasonForFailure;
    private String otherExternalTestingFacility;
    private String otherExterTestingType;
    private String supplierType;
    private String testPerformedBy;
    private String status;
    private String constructiondetails;
    private List<DocumentToFileLinkVo> DocumentToPrimaryFileLinkVoList;
    private List<DocumentToFileLinkVo> DocumentToSecondaryFileLinkVoList;
    private List<String> primaryFileList;
    private List<String> secondaryFileList;

    public List<String> getPrimaryFileList() {
        return primaryFileList;
    }

    public void setPrimaryFileList(List<String> primaryFileList) {
        this.primaryFileList = primaryFileList;
    }

    public List<String> getSecondaryFileList() {
        return secondaryFileList;
    }

    public void setSecondaryFileList(List<String> secondaryFileList) {
        this.secondaryFileList = secondaryFileList;
    }

    /**
     * @return the documentName
     */
    public String getDocumentName() {
        return documentName;
    }

    /**
     * @param documentName the documentName to set
     */
    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * @return the documentType
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     * @param documentType the documentType to set
     */
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    /**
     * @return the createDate
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the updateDate
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate the updateDate to set
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return the accessoriesdepartment
     */
    public String getAccessoriesdepartment() {
        return accessoriesdepartment;
    }

    /**
     * @param accessoriesdepartment the accessoriesdepartment to set
     */
    public void setAccessoriesdepartment(String accessoriesdepartment) {
        this.accessoriesdepartment = accessoriesdepartment;
    }

    /**
     * @return the engConstdtl
     */
    public String getEngConstdtl() {
        return engConstdtl;
    }

    /**
     * @param engConstdtl the engConstdtl to set
     */
    public void setEngConstdtl(String engConstdtl) {
        this.engConstdtl = engConstdtl;
    }

    /**
     * @return the silhouette
     */
    public String getSilhouette() {
        return silhouette;
    }

    /**
     * @param silhouette the silhouette to set
     */
    public void setSilhouette(String silhouette) {
        this.silhouette = silhouette;
    }

    /**
     * @return the collection
     */
    public String getCollection() {
        return collection;
    }

    /**
     * @param collection the collection to set
     */
    public void setCollection(String collection) {
        this.collection = collection;
    }

    /**
     * @return the introDate
     */
    public String getIntroDate() {
        return introDate;
    }

    /**
     * @param introDate the introDate to set
     */
    public void setIntroDate(String introDate) {
        this.introDate = introDate;
    }

    public String getSubCollection() {
        return subCollection;
    }

    public void setSubCollection(String subCollection) {
        this.subCollection = subCollection;
    }

    /**
     * @return the subCollection
     */
    

    /**
     * @return the docClass
     */
    public String getDocClass() {
        return docClass;
    }

    /**
     * @param docClass the docClass to set
     */
    public void setDocClass(String docClass) {
        this.docClass = docClass;
    }

    /**
     * @return the internalOffice
     */
    public String getInternalOffice() {
        return internalOffice;
    }

    /**
     * @param internalOffice the internalOffice to set
     */
    public void setInternalOffice(String internalOffice) {
        this.internalOffice = internalOffice;
    }

    /**
     * @return the subClass
     */
    public String getSubClass() {
        return subClass;
    }

    /**
     * @param subClass the subClass to set
     */
    public void setSubClass(String subClass) {
        this.subClass = subClass;
    }

    /**
     * @return the season
     */
    public String getSeason() {
        return season;
    }

    /**
     * @param season the season to set
     */
    public void setSeason(String season) {
        this.season = season;
    }

    /**
     * @return the serviceProvider
     */
    public String getServiceProvider() {
        return serviceProvider;
    }

    /**
     * @param serviceProvider the serviceProvider to set
     */
    public void setServiceProvider(String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    /**
     * @return the intendedUsers
     */
    public String getIntendedUsers() {
        return intendedUsers;
    }

    /**
     * @param intendedUsers the intendedUsers to set
     */
    public void setIntendedUsers(String intendedUsers) {
        this.intendedUsers = intendedUsers;
    }

    /**
     * @return the theClass
     */
    public String getTheClass() {
        return theClass;
    }

    /**
     * @param theClass the theClass to set
     */
    public void setTheClass(String theClass) {
        this.theClass = theClass;
    }

    /**
     * @return the wearDept
     */
    public String getWearDept() {
        return wearDept;
    }

    /**
     * @param wearDept the wearDept to set
     */
    public void setWearDept(String wearDept) {
        this.wearDept = wearDept;
    }

    /**
     * @return the wearSilhouette
     */
    public String getWearSilhouette() {
        return wearSilhouette;
    }

    /**
     * @param wearSilhouette the wearSilhouette to set
     */
    public void setWearSilhouette(String wearSilhouette) {
        this.wearSilhouette = wearSilhouette;
    }

    /**
     * @return the finalSPpattern
     */
    public String getFinalSPpattern() {
        return finalSPpattern;
    }

    /**
     * @param finalSPpattern the finalSPpattern to set
     */
    public void setFinalSPpattern(String finalSPpattern) {
        this.finalSPpattern = finalSPpattern;
    }

    /**
     * @return the singleintro
     */
    public String getSingleintro() {
        return singleintro;
    }

    /**
     * @param singleintro the singleintro to set
     */
    public void setSingleintro(String singleintro) {
        this.singleintro = singleintro;
    }

    /**
     * @return the singleSeason
     */
    public String getSingleSeason() {
        return singleSeason;
    }

    /**
     * @param singleSeason the singleSeason to set
     */
    public void setSingleSeason(String singleSeason) {
        this.singleSeason = singleSeason;
    }

    /**
     * @return the stylenumberdescription
     */
    public String getStylenumberdescription() {
        return stylenumberdescription;
    }

    /**
     * @param stylenumberdescription the stylenumberdescription to set
     */
    public void setStylenumberdescription(String stylenumberdescription) {
        this.stylenumberdescription = stylenumberdescription;
    }

    /**
     * @return the prnType
     */
    public String getPrnType() {
        return prnType;
    }

    /**
     * @param prnType the prnType to set
     */
    public void setPrnType(String prnType) {
        this.prnType = prnType;
    }

    /**
     * @return the testResultsMaintainedBy
     */
    public String getTestResultsMaintainedBy() {
        return testResultsMaintainedBy;
    }

    /**
     * @param testResultsMaintainedBy the testResultsMaintainedBy to set
     */
    public void setTestResultsMaintainedBy(String testResultsMaintainedBy) {
        this.testResultsMaintainedBy = testResultsMaintainedBy;
    }

    /**
     * @return the supplier
     */
    public String getSupplier() {
        return supplier;
    }

    /**
     * @param supplier the supplier to set
     */
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return the cmNumber
     */
    public String getCmNumber() {
        return cmNumber;
    }

    /**
     * @param cmNumber the cmNumber to set
     */
    public void setCmNumber(String cmNumber) {
        this.cmNumber = cmNumber;
    }

    /**
     * @return the cocExpiration
     */
    public String getCocExpiration() {
        return cocExpiration;
    }

    /**
     * @param cocExpiration the cocExpiration to set
     */
    public void setCocExpiration(String cocExpiration) {
        this.cocExpiration = cocExpiration;
    }

    /**
     * @return the externalAnalyticalStatus
     */
    public String getExternalAnalyticalStatus() {
        return externalAnalyticalStatus;
    }

    /**
     * @param externalAnalyticalStatus the externalAnalyticalStatus to set
     */
    public void setExternalAnalyticalStatus(String externalAnalyticalStatus) {
        this.externalAnalyticalStatus = externalAnalyticalStatus;
    }

    /**
     * @return the externalAnalyticalStatusDate
     */
    public String getExternalAnalyticalStatusDate() {
        return externalAnalyticalStatusDate;
    }

    /**
     * @param externalAnalyticalStatusDate the externalAnalyticalStatusDate to
     * set
     */
    public void setExternalAnalyticalStatusDate(String externalAnalyticalStatusDate) {
        this.externalAnalyticalStatusDate = externalAnalyticalStatusDate;
    }

    /**
     * @return the externalTestingFacility
     */
    public String getExternalTestingFacility() {
        return externalTestingFacility;
    }

    /**
     * @param externalTestingFacility the externalTestingFacility to set
     */
    public void setExternalTestingFacility(String externalTestingFacility) {
        this.externalTestingFacility = externalTestingFacility;
    }

    /**
     * @return the externaStatuslTestingType
     */
    public String getExternaStatuslTestingType() {
        return externaStatuslTestingType;
    }

    /**
     * @param externaStatuslTestingType the externaStatuslTestingType to set
     */
    public void setExternaStatuslTestingType(String externaStatuslTestingType) {
        this.externaStatuslTestingType = externaStatuslTestingType;
    }

    /**
     * @return the internalXFStatus
     */
    public String getInternalXFStatus() {
        return internalXFStatus;
    }

    /**
     * @param internalXFStatus the internalXFStatus to set
     */
    public void setInternalXFStatus(String internalXFStatus) {
        this.internalXFStatus = internalXFStatus;
    }

    /**
     * @return the internalXRFTestDate
     */
    public String getInternalXRFTestDate() {
        return internalXRFTestDate;
    }

    /**
     * @param internalXRFTestDate the internalXRFTestDate to set
     */
    public void setInternalXRFTestDate(String internalXRFTestDate) {
        this.internalXRFTestDate = internalXRFTestDate;
    }

    /**
     * @return the osilnspectionDate
     */
    public String getOsilnspectionDate() {
        return osilnspectionDate;
    }

    /**
     * @param osilnspectionDate the osilnspectionDate to set
     */
    public void setOsilnspectionDate(String osilnspectionDate) {
        this.osilnspectionDate = osilnspectionDate;
    }

    /**
     * @return the osiScore
     */
    public BigDecimal getOsiScore() {
        return osiScore;
    }

    /**
     * @param osiScore the osiScore to set
     */
    public void setOsiScore(BigDecimal osiScore) {
        this.osiScore = osiScore;
    }

    /**
     * @return the reasonForFailure
     */
    public String getReasonForFailure() {
        return reasonForFailure;
    }

    /**
     * @param reasonForFailure the reasonForFailure to set
     */
    public void setReasonForFailure(String reasonForFailure) {
        this.reasonForFailure = reasonForFailure;
    }

    /**
     * @return the otherExternalTestingFacility
     */
    public String getOtherExternalTestingFacility() {
        return otherExternalTestingFacility;
    }

    /**
     * @param otherExternalTestingFacility the otherExternalTestingFacility to
     * set
     */
    public void setOtherExternalTestingFacility(String otherExternalTestingFacility) {
        this.otherExternalTestingFacility = otherExternalTestingFacility;
    }

    /**
     * @return the otherExterTestingType
     */
    public String getOtherExterTestingType() {
        return otherExterTestingType;
    }

    /**
     * @param otherExterTestingType the otherExterTestingType to set
     */
    public void setOtherExterTestingType(String otherExterTestingType) {
        this.otherExterTestingType = otherExterTestingType;
    }

    /**
     * @return the supplierType
     */
    public String getSupplierType() {
        return supplierType;
    }

    /**
     * @param supplierType the supplierType to set
     */
    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    /**
     * @return the testPerformedBy
     */
    public String getTestPerformedBy() {
        return testPerformedBy;
    }

    /**
     * @param testPerformedBy the testPerformedBy to set
     */
    public void setTestPerformedBy(String testPerformedBy) {
        this.testPerformedBy = testPerformedBy;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the constructiondetails
     */
    public String getConstructiondetails() {
        return constructiondetails;
    }

    /**
     * @param constructiondetails the constructiondetails to set
     */
    public void setConstructiondetails(String constructiondetails) {
        this.constructiondetails = constructiondetails;
    }

    /**
     * @return the ida3masterreference
     */
    public BigDecimal getIda3masterreference() {
        return ida3masterreference;
    }

    /**
     * @param ida3masterreference the ida3masterreference to set
     */
    public void setIda3masterreference(BigDecimal ida3masterreference) {
        this.ida3masterreference = ida3masterreference;
    }

    /**
     * @return the documentToPrimaryFileLinkVoList
     */
    public List<DocumentToFileLinkVo> getDocumentToPrimaryFileLinkVoList() {
        return DocumentToPrimaryFileLinkVoList;
    }

    /**
     * @param documentToPrimaryFileLinkVoList the
     * documentToPrimaryFileLinkVoList to set
     */
    public void setDocumentToPrimaryFileLinkVoList(
            List<DocumentToFileLinkVo> documentToPrimaryFileLinkVoList) {
        DocumentToPrimaryFileLinkVoList = documentToPrimaryFileLinkVoList;
    }

    /**
     * @return the documentToSecondaryFileLinkVoList
     */
    public List<DocumentToFileLinkVo> getDocumentToSecondaryFileLinkVoList() {
        return DocumentToSecondaryFileLinkVoList;
    }

    /**
     * @param documentToSecondaryFileLinkVoList the
     * documentToSecondaryFileLinkVoList to set
     */
    public void setDocumentToSecondaryFileLinkVoList(
            List<DocumentToFileLinkVo> documentToSecondaryFileLinkVoList) {
        DocumentToSecondaryFileLinkVoList = documentToSecondaryFileLinkVoList;
    }

}
