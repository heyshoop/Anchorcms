package com.anchorcms.cms.model.main;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by smt on 2016/11/16.
 */
@Entity
@Table(name = "c_model_item")
public class CmsModelItem implements Serializable{
    private static final long serialVersionUID = -6631454947604892847L;
    private Integer modelitemId;
    private String field;
    private String itemLabel;
    private Integer priority;
    private String defValue;
    private String optValue;
    private String textSize;
    private String areaRows;
    private String areaCols;
    private String help;
    private String helpPosition;
    private Integer dataType;
    private Byte isSingle;
    private Byte isChannel;
    private Byte isCustom;
    private Byte isDisplay;
    private Byte isRequired;
    private Integer imageWidth;
    private Integer imageHeight;

    @Id
    @Column(name = "modelitem_id", nullable = false)
    public Integer getModelitemId() {
        return modelitemId;
    }

    public void setModelitemId(Integer modelitemId) {
        this.modelitemId = modelitemId;
    }

    @Basic
    @Column(name = "field", nullable = false, length = 50)
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Basic
    @Column(name = "item_label", nullable = false, length = 100)
    public String getItemLabel() {
        return itemLabel;
    }

    public void setItemLabel(String itemLabel) {
        this.itemLabel = itemLabel;
    }

    @Basic
    @Column(name = "priority", nullable = false)
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Basic
    @Column(name = "def_value", nullable = true, length = 255)
    public String getDefValue() {
        return defValue;
    }

    public void setDefValue(String defValue) {
        this.defValue = defValue;
    }

    @Basic
    @Column(name = "opt_value", nullable = true, length = 255)
    public String getOptValue() {
        return optValue;
    }

    public void setOptValue(String optValue) {
        this.optValue = optValue;
    }

    @Basic
    @Column(name = "text_size", nullable = true, length = 20)
    public String getTextSize() {
        return textSize;
    }

    public void setTextSize(String textSize) {
        this.textSize = textSize;
    }

    @Basic
    @Column(name = "area_rows", nullable = true, length = 3)
    public String getAreaRows() {
        return areaRows;
    }

    public void setAreaRows(String areaRows) {
        this.areaRows = areaRows;
    }

    @Basic
    @Column(name = "area_cols", nullable = true, length = 3)
    public String getAreaCols() {
        return areaCols;
    }

    public void setAreaCols(String areaCols) {
        this.areaCols = areaCols;
    }

    @Basic
    @Column(name = "help", nullable = true, length = 255)
    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    @Basic
    @Column(name = "help_position", nullable = true, length = 1)
    public String getHelpPosition() {
        return helpPosition;
    }

    public void setHelpPosition(String helpPosition) {
        this.helpPosition = helpPosition;
    }

    @Basic
    @Column(name = "data_type", nullable = false)
    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    @Basic
    @Column(name = "is_single", nullable = false)
    public Byte getIsSingle() {
        return isSingle;
    }

    public void setIsSingle(Byte isSingle) {
        this.isSingle = isSingle;
    }

    @Basic
    @Column(name = "is_channel", nullable = false)
    public Byte getIsChannel() {
        return isChannel;
    }

    public void setIsChannel(Byte isChannel) {
        this.isChannel = isChannel;
    }

    @Basic
    @Column(name = "is_custom", nullable = false)
    public Byte getIsCustom() {
        return isCustom;
    }

    public void setIsCustom(Byte isCustom) {
        this.isCustom = isCustom;
    }

    @Basic
    @Column(name = "is_display", nullable = false)
    public Byte getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Byte isDisplay) {
        this.isDisplay = isDisplay;
    }

    @Basic
    @Column(name = "is_required", nullable = false)
    public Byte getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Byte isRequired) {
        this.isRequired = isRequired;
    }

    @Basic
    @Column(name = "image_width", nullable = true)
    public Integer getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(Integer imageWidth) {
        this.imageWidth = imageWidth;
    }

    @Basic
    @Column(name = "image_height", nullable = true)
    public Integer getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(Integer imageHeight) {
        this.imageHeight = imageHeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsModelItem that = (CmsModelItem) o;

        if (modelitemId != null ? !modelitemId.equals(that.modelitemId) : that.modelitemId != null) return false;
        if (field != null ? !field.equals(that.field) : that.field != null) return false;
        if (itemLabel != null ? !itemLabel.equals(that.itemLabel) : that.itemLabel != null) return false;
        if (priority != null ? !priority.equals(that.priority) : that.priority != null) return false;
        if (defValue != null ? !defValue.equals(that.defValue) : that.defValue != null) return false;
        if (optValue != null ? !optValue.equals(that.optValue) : that.optValue != null) return false;
        if (textSize != null ? !textSize.equals(that.textSize) : that.textSize != null) return false;
        if (areaRows != null ? !areaRows.equals(that.areaRows) : that.areaRows != null) return false;
        if (areaCols != null ? !areaCols.equals(that.areaCols) : that.areaCols != null) return false;
        if (help != null ? !help.equals(that.help) : that.help != null) return false;
        if (helpPosition != null ? !helpPosition.equals(that.helpPosition) : that.helpPosition != null) return false;
        if (dataType != null ? !dataType.equals(that.dataType) : that.dataType != null) return false;
        if (isSingle != null ? !isSingle.equals(that.isSingle) : that.isSingle != null) return false;
        if (isChannel != null ? !isChannel.equals(that.isChannel) : that.isChannel != null) return false;
        if (isCustom != null ? !isCustom.equals(that.isCustom) : that.isCustom != null) return false;
        if (isDisplay != null ? !isDisplay.equals(that.isDisplay) : that.isDisplay != null) return false;
        if (isRequired != null ? !isRequired.equals(that.isRequired) : that.isRequired != null) return false;
        if (imageWidth != null ? !imageWidth.equals(that.imageWidth) : that.imageWidth != null) return false;
        if (imageHeight != null ? !imageHeight.equals(that.imageHeight) : that.imageHeight != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = modelitemId != null ? modelitemId.hashCode() : 0;
        result = 31 * result + (field != null ? field.hashCode() : 0);
        result = 31 * result + (itemLabel != null ? itemLabel.hashCode() : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        result = 31 * result + (defValue != null ? defValue.hashCode() : 0);
        result = 31 * result + (optValue != null ? optValue.hashCode() : 0);
        result = 31 * result + (textSize != null ? textSize.hashCode() : 0);
        result = 31 * result + (areaRows != null ? areaRows.hashCode() : 0);
        result = 31 * result + (areaCols != null ? areaCols.hashCode() : 0);
        result = 31 * result + (help != null ? help.hashCode() : 0);
        result = 31 * result + (helpPosition != null ? helpPosition.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        result = 31 * result + (isSingle != null ? isSingle.hashCode() : 0);
        result = 31 * result + (isChannel != null ? isChannel.hashCode() : 0);
        result = 31 * result + (isCustom != null ? isCustom.hashCode() : 0);
        result = 31 * result + (isDisplay != null ? isDisplay.hashCode() : 0);
        result = 31 * result + (isRequired != null ? isRequired.hashCode() : 0);
        result = 31 * result + (imageWidth != null ? imageWidth.hashCode() : 0);
        result = 31 * result + (imageHeight != null ? imageHeight.hashCode() : 0);
        return result;
    }

    private CmsModel model;

    @ManyToOne
    @JoinColumn(name = "model_id",insertable = false,updatable = false)
    public CmsModel getModel() {
        return model;
    }

    public void setModel(CmsModel model) {
        this.model = model;
    }
}
