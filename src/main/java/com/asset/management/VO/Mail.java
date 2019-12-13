package com.asset.management.VO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mail {
	private String from;
    private String to;
    private String subject;
    private String content;
    private String token;
    public Mail() {
    }

    public Mail(String from, String to, String subject, String content) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
    }
    @Override
    public String toString() {
        return "Mail{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
