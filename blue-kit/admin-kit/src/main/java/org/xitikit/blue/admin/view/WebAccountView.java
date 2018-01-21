package org.xitikit.blue.admin.view;

import java.io.Serializable;

public class WebAccountView implements Serializable{

    private String userName;

    private String webAccountId;

    public String getUserName(){

        return userName;
    }

    public void setUserName(final String userName){

        this.userName = userName;
    }

    public String getWebAccountId(){

        return webAccountId;
    }

    public void setWebAccountId(final String webAccountId){

        this.webAccountId = webAccountId;
    }
}
