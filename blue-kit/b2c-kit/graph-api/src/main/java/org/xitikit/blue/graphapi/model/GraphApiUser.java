package org.xitikit.blue.graphapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author J. Keith Hoopes
 *   Copyright Xitikit.org 2017
 *
 *   Models the request to update an Azure B2C user.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GraphApiUser{

    @JsonProperty("objectId")
    private String id;

    private Boolean accountEnabled;

    private PasswordProfile PasswordProfile;

    private List<SignInName> signInNames;

    private String surname;

    private String displayName;

    private String givenName;

    @JsonProperty("userPrincipalName")
    private String userPrincipalName;

    //    @JsonIgnore
    //    public Object getFormattedCustomAttributeOne() {
    //
    //        if (customAttributeOne != null && !"".equals(customAttributeOne.trim())) {
    //
    //            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
    //            try {
    //                Date date = fmt.parse(customAttributeOne);
    //                Calendar calendar = Calendar.getInstance();
    //                calendar.setTime(date);
    //                return LocalDateTime.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0, 0);
    //            } catch (ParseException e) {
    //                return null;
    //            }
    //        }
    //        return null;
    //    }

    @JsonIgnore
    public String getSignInEmail(){

        String email = "";
        if(signInNames != null){
            for(SignInName signInName : signInNames){
                if(signInName
                  .getType()
                  .equals("emailAddress")){
                    email = signInName.getValue();
                    break;
                }
            }
        }
        return email;
    }

    @JsonIgnore
    public void setSignInEmail(String signInEmail){

        if(signInNames == null){
            signInNames = new ArrayList<>();
            signInNames.add(new SignInName("emailAddress", signInEmail));
            return;
        }

        for(SignInName signInName : signInNames){
            if(signInName
              .getType()
              .equals("emailAddress")){
                signInName.setValue(signInEmail);
                break;
            }
        }
    }
}