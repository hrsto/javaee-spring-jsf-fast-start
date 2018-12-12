package com.webarity.fileUpload;

import java.io.IOException;

import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

public class WSFileUploader extends Endpoint {

    @Override
    public void onOpen(Session session, EndpointConfig config) {
		session.addMessageHandler(new MessageHandler.Whole<String>() {
            @Override
            public void onMessage(String msg) {
               try {
                  session.getBasicRemote().sendText(msg);
               } catch (IOException e) {

               }
            }
         });
	}

}