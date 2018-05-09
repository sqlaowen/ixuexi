package com.laowen.wxmsg.passive;

import java.io.Serializable;
import java.util.List;

/**
 * 回复图文消息
 *
 * @author wensw
 */
public class NewsAnswerMsg extends PassiveBase implements Serializable {

    private static final long serialVersionUID = -2322790705602253387L;
    private List<ArticleAnswerMsg> articles;

    public List<ArticleAnswerMsg> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleAnswerMsg> articles) {
        this.articles = articles;
    }


}
