package com.lulu.weichatfriends.model;

/**
 * 单个用户分享内容类
 * Created by Lulu on 2016/10/28.
 */

public class FriendsZone {
    private String name;// 用户名
    private String icon;// 用户头像
    private String content;// 用户发表的内容


    private String pic; // 分享的图片

    /**
     * 带图片分享
     *
     * @param name
     * @param icon
     * @param content
     * @param pic
     */
    public FriendsZone(String name, String icon, String content, String pic) {
        this.name = name;
        this.icon = icon;
        this.content = content;
        this.pic = pic;
    }

    /**
     * 不带有图片的分享
     *
     * @param name
     * @param icon
     * @param pic  public FriendsZone(String name, String icon,  String pic) {
     *             this.name = name;
     *             this.icon = icon;
     *             this.pic = pic;
     *             }
     */
    public FriendsZone(String name, String icon, String content) {
        this.name = name;
        this.icon = icon;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

}
