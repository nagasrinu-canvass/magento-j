/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cnv.magento.service;

import cnv.magento.MagentoResponseParser;
import cnv.magento.model.Order;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.scribe.model.Response;

/**
 *
 * @author Owner
 */
public class OrderService extends MagentoBaseService {    

    public List<Order> getOrders(int page, int limit) throws Exception {
        List<Order> orders = null;
        if (page < 1) {
            throw new IllegalArgumentException("Page should be a positive integer and > 0");
        }
        if (limit < 1) {
            throw new IllegalArgumentException("Limit should be a positive integer and > 0");
        }
        String queryString = "/firecart/orders?page="+page+"&limit="+limit;
        Response response = execute(queryString);        
        String responseBody = response.getBody();
        System.out.println();
        System.out.println(response.getCode());
        System.out.println();
        try{
        Type type = new TypeToken<HashMap<String, Order>>() {
        }.getType();
        HashMap<String, Order> parse = MagentoResponseParser.parser().parse(responseBody, type);
             orders = new ArrayList<Order>(parse.values());
            System.out.println("Orders = " + orders);
        } catch(Exception e){
            e.printStackTrace(System.out);
        }
        return orders;
    }
}
