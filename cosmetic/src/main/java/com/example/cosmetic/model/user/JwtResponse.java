//package com.example.cosmetic.model.user;//package com.example.mh_cosmetic.model.user;
//
//import org.springframework.security.core.GrantedAuthority;
//
//import java.io.Serializable;
//import java.util.Collection;
//
//public class JwtResponse implements Serializable {
//    private String jwtToken;
//    private String userName;
//    private Collection<? extends GrantedAuthority> grantList;
//    private Boolean isOnline;
//
//    public JwtResponse() {
//    }
//    public JwtResponse(String jwtToken) {
//        this.jwtToken = jwtToken;
//    }
//    public JwtResponse(String jwtToken, String userName, Collection<? extends GrantedAuthority> grantList, Boolean isOnline) {
//        this.jwtToken = jwtToken;
//        this.userName = userName;
//        this.grantList = grantList;
//        this.isOnline = isOnline;
//    }
//
//    public String getJwtToken() {
//        return jwtToken;
//    }
//
//    public void setJwtToken(String jwtToken) {
//        this.jwtToken = jwtToken;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public Collection<? extends GrantedAuthority> getGrantList() {
//        return grantList;
//    }
//
//    public void setGrantList(Collection<? extends GrantedAuthority> grantList) {
//        this.grantList = grantList;
//    }
//
//    public Boolean getIsOnline() {
//        return isOnline;
//    }
//
//    public void setIsOnline(Boolean isOnline) {
//        this.isOnline = isOnline;
//    }
//}
