package com.simpleserver.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simpleserver.web.utils.JsonUtil;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.ws.rs.core.MediaType;
import java.util.Locale;

/**
 * @ClassName BaseController
 * @Description
 * @Author huangyb
 * @Date 2019/2/27 18:00
 */
public class BaseController {

    public Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected ObjectMapper mapper = new ObjectMapper();
    /** 自定义 UTF-8 */
    protected static final String APPLICATION_JSON_UTF_8 = MediaType.APPLICATION_JSON + "; " + MediaType.CHARSET_PARAMETER + "=UTF-8";
    protected static final String TEXT_XML_UTF_8 = MediaType.TEXT_XML + "; " + MediaType.CHARSET_PARAMETER + "=UTF-8";
    protected static final String TEXT_PLAIN_UTF_8 = MediaType.TEXT_PLAIN + "; " + MediaType.CHARSET_PARAMETER + "=UTF-8";

    /** dubbo rest服务版本号定义 */
    public static final String DUBBO_VERSION = "0.0.1";

    @Autowired
    private MessageSource msgSource;

    /**
     * Build a success message
     *
     * @param code
     * @return
     */
    protected String buildSuccessMessage(String code) {
        return buildMessage(ResultModal.SUCCESS, code, null, null);
    }

    /**
     * Build a success message with parameter
     *
     * @param code
     * @return
     */
    protected String buildSuccessMessage(String code, Object[] args) {
        return buildMessage(ResultModal.SUCCESS, code, null, args);
    }

    /**
     * Build a success message with data
     *
     * @param code
     * @param data
     * @return
     */
    protected String buildSuccessMessage(String code, Object data) {
        return buildMessage(ResultModal.SUCCESS, code, data, null);
    }

    /**
     * Build a fail message
     *
     * @param code
     * @return
     */
    protected String buildFailMessage(String code) {
        return buildMessage(ResultModal.FAIL, code, null, null);
    }

    /**
     * Build a fail message with parameter
     *
     * @param code
     * @return
     */
    protected String buildFailMessage(String code, Object data) {
        return buildMessage(ResultModal.FAIL, code, data, null);
    }

    /**
     * Build fail message with data
     *
     * @param code
     * @param data
     * @return
     */
    protected String buildFailMessage(String code, Object data, Object[] args) {
        return buildMessage(ResultModal.FAIL, code, data, args);
    }

    protected String buildParamFailMessage(String code) {
        return buildMessage(ResultModal.PARAM_FAIL, code, null, null);
    }

    /**
     * Build message
     *
     * @param status
     * @param code
     * @param data
     * @return
     */

    private String buildMessage(String status, String code, Object data, Object[] args) {
        try {
            Locale locale = LocaleContextHolder.getLocale();
            String msg = msgSource.getMessage(code, args, code, locale);
            ResultModal resultModal = new ResultModal(status, msg, data);
            return JsonUtil.toJson(resultModal);
        } catch (Exception e) {
            logger.error("server err", e);
        }
        return null;
    }


    @Getter
    @Setter
    public class ResultModal {
        public static final String SUCCESS = "200";
        public static final String FAIL = "500";
        public static final String PARAM_FAIL = "400";
        public static final String SUCCESS_MSG = "success";
        public static final String FAIL_MSG = "error";
        public static final int DIALOG = 0;
        public static final int MESSAGE = 1;
        private String statusCode;
        private String message;
        private Object data;

        public ResultModal(String state, String message, Object data) {
            this.data = data;
            this.statusCode = state;
            this.message = message;
        }

        public ResultModal() {
        }

    }

}
