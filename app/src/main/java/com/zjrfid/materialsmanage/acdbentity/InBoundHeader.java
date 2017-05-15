package com.zjrfid.materialsmanage.acdbentity;

/**
 * Created by Administrator on 2017/2/10 0010.
 */
public class InBoundHeader {
    private String EnterWhDate;
    private String EnterWhDocumentNo;
    private String Warehouse;
    private String EnterWhType;
    private String ReceivingClerk;
    private String Department;
    private String Supplier;
    private String Note;
    private String DocumentNo1;
    private String OriginalDocumentType1;
    private String DocumentNo2;
    private String OriginalDocumentType2;
    private String DocumentMaker;
    private String Auditor;
    private String DocumentDate;
    private String AuditDate;

    public InBoundHeader() {
    }

    public InBoundHeader(String enterWhDate, String enterWhDocumentNo, String warehouse, String enterWhType, String receivingClerk, String department, String supplier, String note, String documentNo1, String originalDocumentType1, String documentNo2, String originalDocumentType2, String documentMaker, String auditor, String documentDate, String auditDate) {
        EnterWhDate = enterWhDate;
        EnterWhDocumentNo = enterWhDocumentNo;
        Warehouse = warehouse;
        EnterWhType = enterWhType;
        ReceivingClerk = receivingClerk;
        Department = department;
        Supplier = supplier;
        Note = note;
        DocumentNo1 = documentNo1;
        OriginalDocumentType1 = originalDocumentType1;
        DocumentNo2 = documentNo2;
        OriginalDocumentType2 = originalDocumentType2;
        DocumentMaker = documentMaker;
        Auditor = auditor;
        DocumentDate = documentDate;
        AuditDate = auditDate;
    }

    public String getEnterWhDate() {
        return EnterWhDate;
    }

    public void setEnterWhDate(String enterWhDate) {
        EnterWhDate = enterWhDate;
    }

    public String getEnterWhDocumentNo() {
        return EnterWhDocumentNo;
    }

    public void setEnterWhDocumentNo(String enterWhDocumentNo) {
        EnterWhDocumentNo = enterWhDocumentNo;
    }

    public String getWarehouse() {
        return Warehouse;
    }

    public void setWarehouse(String warehouse) {
        Warehouse = warehouse;
    }

    public String getEnterWhType() {
        return EnterWhType;
    }

    public void setEnterWhType(String enterWhType) {
        EnterWhType = enterWhType;
    }

    public String getReceivingClerk() {
        return ReceivingClerk;
    }

    public void setReceivingClerk(String receivingClerk) {
        ReceivingClerk = receivingClerk;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getSupplier() {
        return Supplier;
    }

    public void setSupplier(String supplier) {
        Supplier = supplier;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getDocumentNo1() {
        return DocumentNo1;
    }

    public void setDocumentNo1(String documentNo1) {
        DocumentNo1 = documentNo1;
    }

    public String getOriginalDocumentType1() {
        return OriginalDocumentType1;
    }

    public void setOriginalDocumentType1(String originalDocumentType1) {
        OriginalDocumentType1 = originalDocumentType1;
    }

    public String getDocumentNo2() {
        return DocumentNo2;
    }

    public void setDocumentNo2(String documentNo2) {
        DocumentNo2 = documentNo2;
    }

    public String getOriginalDocumentType2() {
        return OriginalDocumentType2;
    }

    public void setOriginalDocumentType2(String originalDocumentType2) {
        OriginalDocumentType2 = originalDocumentType2;
    }

    public String getDocumentMaker() {
        return DocumentMaker;
    }

    public void setDocumentMaker(String documentMaker) {
        DocumentMaker = documentMaker;
    }

    public String getAuditor() {
        return Auditor;
    }

    public void setAuditor(String auditor) {
        Auditor = auditor;
    }

    public String getDocumentDate() {
        return DocumentDate;
    }

    public void setDocumentDate(String documentDate) {
        DocumentDate = documentDate;
    }

    public String getAuditDate() {
        return AuditDate;
    }

    public void setAuditDate(String auditDate) {
        AuditDate = auditDate;
    }
}
