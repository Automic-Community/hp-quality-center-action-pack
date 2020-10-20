/**
 * 
 */
package com.automic.hpqc.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.core.NewCookie;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.automic.hpqc.constants.ExceptionConstants;
import com.automic.hpqc.exception.AutomicException;
import com.automic.hpqc.utils.AESEncryptDecrypt;
import com.automic.hpqc.utils.CommonUtil;
import com.automic.hpqc.utils.ConsoleWriter;

/**
 * @author sumitsamson
 * 
 */
@XmlRootElement(name = "ATToken")
public class AuthenticationToken {

    private String cookieDomain;
    private String url;
    private ArrayList<AuthenticationCookie> cookieList;

    public AuthenticationToken() {
        cookieList = new ArrayList<AuthenticationCookie>();
    }

    public AuthenticationToken(String baseUrl) {
        this.url = baseUrl;
        cookieList = new ArrayList<AuthenticationCookie>();
    }

    public String getCookieDomain() {
        return cookieDomain;
    }

    public String getUrl() {
        return url;
    }

    public ArrayList<AuthenticationCookie> getCookieList() {
        return cookieList;
    }

    @XmlElement(name = "Domain")
    public void setCookieDomain(String domain) {
        this.cookieDomain = domain;
    }

    @XmlElement(name = "url")
    public void setUrl(String baseUrl) {
        this.url = baseUrl;
    }

    @XmlElement(name = "ATCookieList")
    public void setCookieList(ArrayList<AuthenticationCookie> cookieList) {
        this.cookieList = cookieList;
    }

    public void putNewCookies(List<NewCookie> cookies) {
        Map<String, String> map = new HashMap<String, String>();
        for (AuthenticationCookie cook : cookieList) {
            map.put(cook.getName(), cook.getValue());
        }
        cookieList.clear();
        
        for (NewCookie cook : cookies) {            
            String name = cook.getName();
            if("LWSSO_COOKIE_KEY".equals(name) || "QCSession".equals(name) || "XSRF-TOKEN".equals(name)) {
            	String value = cook.getValue();            	
            	if ("LWSSO_COOKIE_KEY".equals(name) && (value == null || value.isEmpty())) {
            		cookieList.clear();
            		map.clear();
            		break;
            	} else {
            		map.remove(name);
            		AuthenticationCookie cookie = new AuthenticationCookie();
            		cookie.setName(name);
            		cookie.setValue(value);
            		setCookieDomain(cook.getDomain());
            		cookieList.add(cookie);
            	}
            }
        }
        Iterator<Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, String> entry = it.next();
            AuthenticationCookie cookie = new AuthenticationCookie();
            cookie.setName(entry.getKey());
            cookie.setValue(entry.getValue());
            cookieList.add(cookie);
        }
    }

    public List<NewCookie> getNewCookies() {
        List<NewCookie> newCookies = new ArrayList<NewCookie>();
        for (AuthenticationCookie cook : cookieList) {
            newCookies.add(new NewCookie(cook.getName(), cook.getValue()));
        }
        return newCookies;
    }

    public String encrypt() throws AutomicException {
        String encryptedTknId = "";
        if (cookieList.size() != 0) {
            String tokenId = CommonUtil.object2Xml(this, false);
            encryptedTknId = AESEncryptDecrypt.encrypt(tokenId);
        }
        return encryptedTknId;
    }

    public static AuthenticationToken createATToken(String baseUrl, String authTokenId) throws AutomicException {
        if (authTokenId == null || authTokenId.isEmpty()) {
            return new AuthenticationToken(baseUrl);
        }
        String decryptedAuthTokenId = AESEncryptDecrypt.decrypt(authTokenId);
        try {
            AuthenticationToken token = CommonUtil.xml2object(AuthenticationToken.class, decryptedAuthTokenId);
            token.validateToken(baseUrl);
            return token;
        } catch (JAXBException e) {
            throw new AutomicException(ExceptionConstants.INVALID_AUTH_TOKEN);
        }
    }

    public void validateToken(String baseUrl) throws AutomicException {
        if (getCookieDomain() == null) {
            if (!baseUrl.equals(getUrl())) {
                ConsoleWriter.writeln("Authentication Token is not valid for " + baseUrl);
                throw new AutomicException(ExceptionConstants.INVALID_AUTH_TOKEN_URL);
            }
        } else {
            if (!baseUrl.contains(getCookieDomain())) {
                ConsoleWriter.writeln("Authentication Token is not valid for " + baseUrl);
                throw new AutomicException(ExceptionConstants.INVALID_AUTH_TOKEN);
            }
        }
    }
}
