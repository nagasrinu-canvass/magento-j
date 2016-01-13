/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cnv.magento;

import cnv.magento.model.MagentoCredentials;
import cnv.magento.service.MagentoBaseService;
import java.util.HashMap;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

/**
 *
 * @author Owner
 */
public class MagentoClient {
    
    private HashMap<Class, MagentoBaseService> services;
    private MagentoCredentials creds;
    String baseUrl;
    private boolean debug = false;

    public void enableDebug() {
        debug = true;
    }

    public void disableDebug() {
        debug = false;
    }

    public boolean isDebugEnabled() {
        return debug;
    }
    
    public MagentoClient(MagentoCredentials credentails) {
        this.creds = credentails;
        baseUrl = creds.getShopUrl()+"/api/rest";
//        if (creds.hasAccessToken()) {
//            baseUrl = "https://" + creds.getShopName() + ".myshopify.com";
//        } else {
//            baseUrl = "https://" + creds.getApiKey()
//                    + ":" + creds.getPassword() + "@" + creds.getShopName() + ".myshopify.com";
//        }
    }
    
    public MagentoCredentials getCredentials() {
        return creds;
    }
    
    public String getBaseUrl() {
        return baseUrl;
    }

    static void log(String msg) {
        System.out.println("[Magento] " + msg);
    }
    /**
     * This method will create the Given Service
     *
     * @param <T>
     * @param cls Service Class
     * @return the Service Object
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public <T extends MagentoBaseService> T getService(Class<T> cls) throws InstantiationException, IllegalAccessException {
        T obj = cls.newInstance();
        obj.setClient(this);
        return obj;
    }
    
    /**
     * This method will give the temporary tokens
     * @param MAGENTO_API_KEY
     * @param MAGENTO_API_SECRET
     * @return 
     */
    public static Token getRequestToken(String MAGENTO_API_KEY, String MAGENTO_API_SECRET) {
        OAuthService service = new ServiceBuilder().provider(MagentoThreeLeggedOAuth.class).
                apiKey(MAGENTO_API_KEY).
                apiSecret(MAGENTO_API_SECRET).
                build();

        System.out.println("=== Mage v1.7.0.2 OAuth Workflow ===");
        System.out.println();

        // Obtain the Request Token
        System.out.println("Fetching the Request Token...");
        Token requestToken = service.getRequestToken();
        System.out.println("token: " + requestToken.getRawResponse());
        System.out.println();

        System.out.println("Now go and authorize Scribe here:");
        System.out.println(service.getAuthorizationUrl(requestToken));
        return requestToken;
    }
    
//    public static void getAcessToken(String token, String tokenSecret, String verifierStr) {
//        Token requestToken = new Token(token, tokenSecret);
//        OAuthService service = new ServiceBuilder().provider(MagentoThreeLeggedOAuth.class).
//                apiKey(MAGENTO_API_KEY).
//                apiSecret(MAGENTO_API_SECRET).
//                build();
//        Verifier verifier = new Verifier(verifierStr);
//        Token accessToken = service.getAccessToken(requestToken, verifier);
//        System.out.println("accessToken = " + accessToken);
//    }
}
