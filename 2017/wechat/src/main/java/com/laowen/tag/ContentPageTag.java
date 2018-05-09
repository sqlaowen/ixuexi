package com.laowen.tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by iyou on 2017-06-25.
 */
public class ContentPageTag extends BodyTagSupport {

    private static final long serialVersionUID = 1L;

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private final String masterFolderPath = "/WEB-INF/_layout/";
    private final String masterPageSuffix = ".jsp";

    @Override
    public void doInitBody() throws JspException {
        try {
            this.pageContext.getRequest().setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error(">>> com.laowen.tag.ContentPageTag#doInitBody error", e);
        }
        super.doInitBody();
    }

    @Override
    public int doAfterBody() throws JspException {
        return SKIP_BODY;
    }

    @Override
    public int doStartTag() throws JspException {
        //执行子标签
        return EVAL_BODY_BUFFERED;
    }

    @Override
    public int doEndTag() throws JspException {
        JspWriter out = pageContext.getOut();
        TagResponse bufferedResponse = new TagResponse((HttpServletResponse) this.pageContext.getResponse());
        try {
            //渲染页面
            this.pageContext.getServletContext().getRequestDispatcher(this.getMasterPageUrl()).include(this.pageContext.getRequest(), bufferedResponse);
            //jspWriter.clearBuffer();
            out.write(bufferedResponse.getContent());
        } catch (ServletException e) {
            log.error(">>> com.laowen.tag.ContentPageTag#doEndTag error", e);
        } catch (IOException e) {
            log.error(">>> com.laowen.tag.ContentPageTag#doEndTag error", e);
        }
        return SKIP_PAGE;
    }

    /**
     * TODO  获取母版
     */
    private String getMasterPageUrl() {
        return this.masterFolderPath + this.materPageId + this.masterPageSuffix;
    }

    private String materPageId;

    public String getMaterPageId() {
        return materPageId;
    }

    public void setMaterPageId(String materPageId) {
        this.materPageId = materPageId;
    }
}
