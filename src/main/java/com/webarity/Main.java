package com.webarity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.HttpRequestHandler;

public class Main implements HttpRequestHandler {

        private String someParam;

        public Main() {

        }

        public String getSomeParam() {return someParam;}
        public void setSomeParam(String someParam) {this.someParam = someParam;}

        @Override
	public void handleRequest(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        rs.getOutputStream().print(someParam);
        rs.flushBuffer();
        rs.getOutputStream().close();

	}
}