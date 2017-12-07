package com.dbutils.model.entity;

import com.dbutils.model.anno.ColumnAnno;

public class UserInfo {
    @ColumnAnno("DB_ID")
   private String uId;
    @ColumnAnno("DB_NAME")
   private String uName;
   @ColumnAnno("DB_AGE")
   private int uAge;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public int getuAge() {
        return uAge;
    }

    public void setuAge(int uAge) {
        this.uAge = uAge;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "uId='" + uId + '\'' +
                ", uName='" + uName + '\'' +
                ", uAge=" + uAge +
                '}';
    }
}
