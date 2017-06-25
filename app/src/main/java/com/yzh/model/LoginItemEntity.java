package com.yzh.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by appadmin on 2017/6/22.
 */

public class LoginItemEntity implements Serializable{

    /**
     * Menus : {"MenuId":3300423,"MenuName":"生产管理","ParentId":0,"ProPath":"","TemplateId":-1,"OrderBy":0,"Target":"","SystemId":0,"EntityCount":0}
     * ChildMenu : [{"MenuId":3300429,"MenuName":"工单生成","ParentId":3300423,"ProPath":"","TemplateId":-1,"OrderBy":0,"Target":"rightFrame","SystemId":0,"EntityCount":0}]
     */

    private MenusBean Menus;
    private List<ChildMenuBean> ChildMenu;

    public MenusBean getMenus() {
        return Menus;
    }

    public void setMenus(MenusBean Menus) {
        this.Menus = Menus;
    }

    public List<ChildMenuBean> getChildMenu() {
        return ChildMenu;
    }

    public void setChildMenu(List<ChildMenuBean> ChildMenu) {
        this.ChildMenu = ChildMenu;
    }

    public static class MenusBean implements Serializable{
        /**
         * MenuId : 3300423
         * MenuName : 生产管理
         * ParentId : 0
         * ProPath :
         * TemplateId : -1
         * OrderBy : 0
         * Target :
         * SystemId : 0
         * EntityCount : 0
         */

        private int MenuId;
        private String MenuName;
        private int ParentId;
        private String ProPath;
        private int TemplateId;
        private int OrderBy;
        private String Target;
        private int SystemId;
        private int EntityCount;

        public int getMenuId() {
            return MenuId;
        }

        public void setMenuId(int MenuId) {
            this.MenuId = MenuId;
        }

        public String getMenuName() {
            return MenuName;
        }

        public void setMenuName(String MenuName) {
            this.MenuName = MenuName;
        }

        public int getParentId() {
            return ParentId;
        }

        public void setParentId(int ParentId) {
            this.ParentId = ParentId;
        }

        public String getProPath() {
            return ProPath;
        }

        public void setProPath(String ProPath) {
            this.ProPath = ProPath;
        }

        public int getTemplateId() {
            return TemplateId;
        }

        public void setTemplateId(int TemplateId) {
            this.TemplateId = TemplateId;
        }

        public int getOrderBy() {
            return OrderBy;
        }

        public void setOrderBy(int OrderBy) {
            this.OrderBy = OrderBy;
        }

        public String getTarget() {
            return Target;
        }

        public void setTarget(String Target) {
            this.Target = Target;
        }

        public int getSystemId() {
            return SystemId;
        }

        public void setSystemId(int SystemId) {
            this.SystemId = SystemId;
        }

        public int getEntityCount() {
            return EntityCount;
        }

        public void setEntityCount(int EntityCount) {
            this.EntityCount = EntityCount;
        }
    }

    public static class ChildMenuBean implements Serializable {
        /**
         * MenuId : 3300429
         * MenuName : 工单生成
         * ParentId : 3300423
         * ProPath :
         * TemplateId : -1
         * OrderBy : 0
         * Target : rightFrame
         * SystemId : 0
         * EntityCount : 0
         */

        private int MenuId;
        private String MenuName;
        private int ParentId;
        private String ProPath;
        private int TemplateId;
        private int OrderBy;
        private String Target;
        private int SystemId;
        private int EntityCount;

        public int getMenuId() {
            return MenuId;
        }

        public void setMenuId(int MenuId) {
            this.MenuId = MenuId;
        }

        public String getMenuName() {
            return MenuName;
        }

        public void setMenuName(String MenuName) {
            this.MenuName = MenuName;
        }

        public int getParentId() {
            return ParentId;
        }

        public void setParentId(int ParentId) {
            this.ParentId = ParentId;
        }

        public String getProPath() {
            return ProPath;
        }

        public void setProPath(String ProPath) {
            this.ProPath = ProPath;
        }

        public int getTemplateId() {
            return TemplateId;
        }

        public void setTemplateId(int TemplateId) {
            this.TemplateId = TemplateId;
        }

        public int getOrderBy() {
            return OrderBy;
        }

        public void setOrderBy(int OrderBy) {
            this.OrderBy = OrderBy;
        }

        public String getTarget() {
            return Target;
        }

        public void setTarget(String Target) {
            this.Target = Target;
        }

        public int getSystemId() {
            return SystemId;
        }

        public void setSystemId(int SystemId) {
            this.SystemId = SystemId;
        }

        public int getEntityCount() {
            return EntityCount;
        }

        public void setEntityCount(int EntityCount) {
            this.EntityCount = EntityCount;
        }
    }
}
