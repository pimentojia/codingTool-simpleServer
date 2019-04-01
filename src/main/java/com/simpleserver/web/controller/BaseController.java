package com.simpleserver.web.controller;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName BaseController
 * @Description
 * @Author huangyb
 * @Date 2019/2/27 18:00
 */
public class BaseController {

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Getter
    @Setter
    protected class RspContent {
        private int errCode;
        private String message;
        private Object data;

        public RspContent(int errCode, String message) {
            this.errCode = errCode;
            this.message = message;
        }

        public RspContent(int errCode, String message, Object data) {
            this.errCode = errCode;
            this.message = message;
            this.data = data;
        }
    }

}
