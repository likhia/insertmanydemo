package com.ocbc.data;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("POCCOLL")
public class POCColl {
    private Integer fld0;
    private Date fld1;
    private String fld2;
    private String fld3;
    
    public Integer getFld0() {
        return fld0;
    }
    public void setFld0(Integer fld0) {
        this.fld0 = fld0;
    }
    public Date getFld1() {
        return fld1;
    }
    public void setFld1(Date fld1) {
        this.fld1 = fld1;
    }
    public String getFld2() {
        return fld2;
    }
    public void setFld2(String fld2) {
        this.fld2 = fld2;
    }
    public String getFld3() {
        return fld3;
    }
    public void setFld3(String fld3) {
        this.fld3 = fld3;
    }
    
    
    

}
