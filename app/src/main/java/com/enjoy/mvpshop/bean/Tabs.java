/**
  * Copyright 2021 bejson.com 
  */
package com.enjoy.mvpshop.bean;
import java.util.List;

/**
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Tabs {

    private String name;
    private List<String> pictures;
    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setPictures(List<String> pictures) {
         this.pictures = pictures;
     }
     public List<String> getPictures() {
         return pictures;
     }

}