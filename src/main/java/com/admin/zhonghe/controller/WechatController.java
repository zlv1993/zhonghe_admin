package com.admin.zhonghe.controller;

import com.admin.zhonghe.config.SocketSessionConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;


@Controller
@ServerEndpoint(value = "/websocket/{room}",configurator = SocketSessionConfig.class)
public class WechatController {
    private HttpSession httpSession;
    private Session session;
    private Logger log= LoggerFactory.getLogger(this.getClass());
    //用来记录总人数
    private  static AtomicInteger onlinePersons=new AtomicInteger(0);
    //用来记录房间里的人数
    private static Map<String, Set<WechatController>> roomMap=new ConcurrentHashMap<>(8);

    @OnOpen
    public void open(@PathParam("room") String room, Session session, EndpointConfig config) throws IOException {
        httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        this.session=session;
        Set<WechatController> set = roomMap.get(room);
        // 如果是新的房间，则创建一个映射，如果房间已存在，则把用户放进去
        if(set == null){
            set = new CopyOnWriteArraySet();
            set.add(this);
            roomMap.put(room,set);
        }else{
            set.add(this);
        }
        // 房间人数+1
        onlinePersons.incrementAndGet();

        // 给房间内所有用户推送信息
        for(WechatController s : set){
            s.session.getBasicRemote().sendText("我来了");

        }
        log.info("新用户{}进入聊天,房间人数:{}",httpSession.getId(),onlinePersons);
    }

    @OnMessage
    public void reveiveMessage(@PathParam("room") String room, Session session,String message) throws IOException {
        log.info("接受到用户{}的数据:{}",session.getId(),message);
        // 拼接一下用户信息
        String msg = session.getId()+" : "+ message;
        Set<WechatController> sockets = roomMap.get(room);
        // 给房间内所有用户推送信息
        for(WechatController s : sockets){
            s.session.getBasicRemote().sendText(msg);

        }
    }
    @OnClose
    public void close(@PathParam("room") String room, Session session){
        // 如果某个用户离开了，就移除相应的信息
        if(roomMap.containsKey(room)){
            roomMap.get(room).remove(this);
        }
        // 房间人数-1
        onlinePersons.decrementAndGet();
        log.info("用户{}退出聊天,房间人数:{}",session.getId(),onlinePersons);
    }
    @OnError
    public void error(Throwable throwable){
        try {
            throw throwable;
        } catch (Throwable e) {
            log.error("未知错误");
        }
    }


}
