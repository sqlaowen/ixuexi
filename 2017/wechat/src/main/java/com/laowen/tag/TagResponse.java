package com.laowen.tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by iyou on 2017-06-25.
 */
public class TagResponse extends HttpServletResponseWrapper {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private StringWriter stringWriter = new StringWriter();
    private PrintWriter printWriter = new PrintWriter(stringWriter);

    public TagResponse(HttpServletResponse response) {
        super(response);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return printWriter;
    }

    public String getContent() {
        return stringWriter.toString();
    }
}
