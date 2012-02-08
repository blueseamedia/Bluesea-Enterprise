package vn.topmedia.monitor.yahoo;

import org.junit.Test;
import org.openymsg.network.Session;

/**
 *
 * @author Anh Tuan <tuanta@topmedia.vn>
 */
public class YahooChat {

    @Test
    public void testYahooLoginAndSendMessage() {
        Session session = new Session();
        try {
            session.login("exmonitor", "bluesea8x77");
            session.sendMessage("tk1cntt", "test yahoo login and send message");
            session.logout();
        } catch (Exception ex) {
        }
    }
}
