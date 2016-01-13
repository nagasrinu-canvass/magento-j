/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cnv.magento;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

/**
 *
 * @author Owner
 */
public class MagentoThreeLeggedOAuth extends DefaultApi10a {

    private static final String BASE_URL = "http://localhost/magento-1.x.x/";

    @Override
    public String getRequestTokenEndpoint() {
        return BASE_URL + "oauth/initiate";

    }

    @Override
    public String getAccessTokenEndpoint() {
        return BASE_URL + "oauth/token";
    }

    @Override
    public String getAuthorizationUrl(Token requestToken) {
//            return BASE_URL + "admin/oauth_authorize?oauth_token="
        return BASE_URL + "admin/oauth_authorize?oauth_token="
                + requestToken.getToken(); //this implementation is for admin roles only...
    }
}
