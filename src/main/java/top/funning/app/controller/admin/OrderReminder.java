package top.funning.app.controller.admin;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import top.funning.app.service.index.count.M1017;
import top.knxy.library.config.Code;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/admin/order/reminder")
@Component
public class OrderReminder {

    private static CopyOnWriteArraySet<OrderReminder> connections = new CopyOnWriteArraySet<>();
    private Session session;


    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        this.session = session;
        connections.add(this);
        String data = getData();
        if (data != null) {
            sendMessage(this, data);
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        connections.remove(this);
    }


    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }


    public static String getData() {
        /*M1017 service = new M1017();
        service.start();
        Gson gson = new Gson();
        if (service.code == Code.Service.SUCCESS) {
            return gson.toJson(service.data);
        }*/
        return null;
    }


    public static void broadcast() {
        String data = getData();
        if (data != null) {
            for (OrderReminder client : connections) {
                sendMessage(client, data);
            }
        }
    }

    public static void sendMessage(OrderReminder client, String data) {
        try {
            synchronized (client) {
                client.session.getBasicRemote().sendText(data);
            }
        } catch (IOException e) {
            connections.remove(client);
            try {
                client.session.close();
            } catch (IOException e1) {
            }
        }
    }

}
