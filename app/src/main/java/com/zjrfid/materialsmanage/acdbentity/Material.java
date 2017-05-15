package com.zjrfid.materialsmanage.acdbentity;

/**
 * Created by Administrator on 2017/2/10 0010.
 * 入库单表头
 */
public class Material {
    public String MaterialLode;//物资编码
    public String OldLode;//旧编码
    public String ClassificationLode;//分类编码
    public String MaterialName;//物资名称
    public String DefaultRate;//默认税率
    public String SpecType;//规格型号
    public String IsAsset;//是否资产
    public String DefaultGoodsAllocation;//默认货位
    public String MainUnit;//主计量单位
    public String DefaultWarehouse;//默认仓库
    public String AuxiliaryUnit;//辅助计量单位
    public String NoTaxUnitPrice;//无税单价
    public String NoTaxAmount;//无税金额
    public String IncludeTaxUnitPrice;//含税单价
    public String IncludeTaxAmount;//含税金额
    public String TaxRate;//税率
    public String TaxAmount;//税额
    public String BatchNo;//批号
    public String Quantity;//数量
    public String GoodsAllocation;//货位
    public String Remark;//备注
    public String MaterialClassification;//材料分类

    public Material(){}

    public Material(String materialLode, String oldLode, String classificationLode, String materialName, String defaultRate, String specType, String isAsset, String defaultGoodsAllocation, String mainUnit, String defaultWarehouse, String auxiliaryUnit, String noTaxUnitPrice, String noTaxAmount, String includeTaxUnitPrice, String includeTaxAmount, String taxRate, String taxAmount, String batchNo, String quantity, String goodsAllocation, String remark, String materialClassification) {
        MaterialLode = materialLode;
        OldLode = oldLode;
        ClassificationLode = classificationLode;
        MaterialName = materialName;
        DefaultRate = defaultRate;
        SpecType = specType;
        IsAsset = isAsset;
        DefaultGoodsAllocation = defaultGoodsAllocation;
        MainUnit = mainUnit;
        DefaultWarehouse = defaultWarehouse;
        AuxiliaryUnit = auxiliaryUnit;
        NoTaxUnitPrice = noTaxUnitPrice;
        NoTaxAmount = noTaxAmount;
        IncludeTaxUnitPrice = includeTaxUnitPrice;
        IncludeTaxAmount = includeTaxAmount;
        TaxRate = taxRate;
        TaxAmount = taxAmount;
        BatchNo = batchNo;
        Quantity = quantity;
        GoodsAllocation = goodsAllocation;
        Remark = remark;
        MaterialClassification = materialClassification;
    }

    public String getMaterialLode() {
        return MaterialLode;
    }

    public void setMaterialLode(String materialLode) {
        MaterialLode = materialLode;
    }

    public String getOldLode() {
        return OldLode;
    }

    public void setOldLode(String oldLode) {
        OldLode = oldLode;
    }

    public String getClassificationLode() {
        return ClassificationLode;
    }

    public void setClassificationLode(String classificationLode) {
        ClassificationLode = classificationLode;
    }

    public String getMaterialName() {
        return MaterialName;
    }

    public void setMaterialName(String materialName) {
        MaterialName = materialName;
    }

    public String getDefaultRate() {
        return DefaultRate;
    }

    public void setDefaultRate(String defaultRate) {
        DefaultRate = defaultRate;
    }

    public String getSpecType() {
        return SpecType;
    }

    public void setSpecType(String specType) {
        SpecType = specType;
    }

    public String getIsAsset() {
        return IsAsset;
    }

    public void setIsAsset(String isAsset) {
        IsAsset = isAsset;
    }

    public String getDefaultGoodsAllocation() {
        return DefaultGoodsAllocation;
    }

    public void setDefaultGoodsAllocation(String defaultGoodsAllocation) {
        DefaultGoodsAllocation = defaultGoodsAllocation;
    }

    public String getMainUnit() {
        return MainUnit;
    }

    public void setMainUnit(String mainUnit) {
        MainUnit = mainUnit;
    }

    public String getDefaultWarehouse() {
        return DefaultWarehouse;
    }

    public void setDefaultWarehouse(String defaultWarehouse) {
        DefaultWarehouse = defaultWarehouse;
    }

    public String getAuxiliaryUnit() {
        return AuxiliaryUnit;
    }

    public void setAuxiliaryUnit(String auxiliaryUnit) {
        AuxiliaryUnit = auxiliaryUnit;
    }

    public String getNoTaxUnitPrice() {
        return NoTaxUnitPrice;
    }

    public void setNoTaxUnitPrice(String noTaxUnitPrice) {
        NoTaxUnitPrice = noTaxUnitPrice;
    }

    public String getNoTaxAmount() {
        return NoTaxAmount;
    }

    public void setNoTaxAmount(String noTaxAmount) {
        NoTaxAmount = noTaxAmount;
    }

    public String getIncludeTaxUnitPrice() {
        return IncludeTaxUnitPrice;
    }

    public void setIncludeTaxUnitPrice(String includeTaxUnitPrice) {
        IncludeTaxUnitPrice = includeTaxUnitPrice;
    }

    public String getIncludeTaxAmount() {
        return IncludeTaxAmount;
    }

    public void setIncludeTaxAmount(String includeTaxAmount) {
        IncludeTaxAmount = includeTaxAmount;
    }

    public String getTaxRate() {
        return TaxRate;
    }

    public void setTaxRate(String taxRate) {
        TaxRate = taxRate;
    }

    public String getTaxAmount() {
        return TaxAmount;
    }

    public void setTaxAmount(String taxAmount) {
        TaxAmount = taxAmount;
    }

    public String getBatchNo() {
        return BatchNo;
    }

    public void setBatchNo(String batchNo) {
        BatchNo = batchNo;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getGoodsAllocation() {
        return GoodsAllocation;
    }

    public void setGoodsAllocation(String goodsAllocation) {
        GoodsAllocation = goodsAllocation;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getMaterialClassification() {
        return MaterialClassification;
    }

    public void setMaterialClassification(String materialClassification) {
        MaterialClassification = materialClassification;
    }
}
