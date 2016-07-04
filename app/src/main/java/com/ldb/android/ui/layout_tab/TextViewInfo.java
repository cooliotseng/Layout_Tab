package com.ldb.android.ui.layout_tab;

/**
 * Created by lsp on 2016/7/3.
 */
public class TextViewInfo {
    private String id;
    private String layoutWidth;
    private String layoutHeight;
    private int layoutWeight;
    private int layoutGravity;
    private String text;
    private int textColor;
    private int gravity;

    public int getLayoutGravity() {
        return layoutGravity;
    }

    public void setLayoutGravity(int layoutGravity) {
        this.layoutGravity = layoutGravity;
    }

    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLayoutWidth() {
        return layoutWidth;
    }

    public void setLayoutWidth(String layoutWidth) {
        this.layoutWidth = layoutWidth;
    }

    public String getLayoutHeight() {
        return layoutHeight;
    }

    public void setLayoutHeight(String layoutHeight) {
        this.layoutHeight = layoutHeight;
    }

    public int getLayoutWeight() {
        return layoutWeight;
    }

    public void setLayoutWeight(int layoutWeight) {
        this.layoutWeight = layoutWeight;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }
}
