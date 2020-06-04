package com.moses.blog.exception;

/**
 * @author Moses
 */
public class ArticleAttributesMissException extends ServiceException {
    public ArticleAttributesMissException() {
    }

    public ArticleAttributesMissException(String message) {
        super(message);
    }

    public ArticleAttributesMissException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArticleAttributesMissException(Throwable cause) {
        super(cause);
    }

    public ArticleAttributesMissException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
