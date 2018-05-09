package com.laowen.tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

/**
 * Created by iyou on 2017-06-25.
 */
public class MasterPageTag extends BodyTagSupport {

    private static final long serialVersionUID = 1L;
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public int doAfterBody() throws JspException {
        return SKIP_BODY;
    }


    @Override
    public int doStartTag() throws JspException {
        // 执行子标签
        return EVAL_BODY_BUFFERED;
    }

    @Override
    public int doEndTag() throws JspException {
        JspWriter out = pageContext.getOut();
        if (bodyContent != null) {
            if (out instanceof BodyContent) {
                out = ((BodyContent) out).getEnclosingWriter();
            }
        }
        String content = this.bodyContent.getString();
        try {
            this.bodyContent.clear();
            out.write(content);
        } catch (IOException e) {
            log.error(">>> com.laowen.tag.MasterPageTag#doEndTag error", e);
        }
        return SKIP_PAGE; // 不执行标签之后的内容
    }
}
