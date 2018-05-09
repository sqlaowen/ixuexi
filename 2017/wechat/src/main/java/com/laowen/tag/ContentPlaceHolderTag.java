package com.laowen.tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by iyou on 2017-06-25.
 */
public class ContentPlaceHolderTag extends TagSupport {

    private static final long serialVersionUID = 1L;
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public int doEndTag() throws JspException {
        JspWriter out = pageContext.getOut();
        Object obj = this.pageContext.getRequest().getAttribute(this.getId());
        try {
            if (null != obj) {
                out.write(obj.toString());
            }
        } catch (IOException e) {
            log.error(">>> com.laowen.tag.ContentPlaceHolderTag#doEndTag error", e);
        }

        return EVAL_PAGE;
    }
}
