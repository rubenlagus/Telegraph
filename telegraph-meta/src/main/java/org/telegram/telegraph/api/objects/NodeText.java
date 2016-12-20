package org.telegram.telegraph.api.objects;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.telegram.telegraph.jsonutilities.NodeTextSerializer;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
@JsonSerialize(using = NodeTextSerializer.class)
public class NodeText extends Node {
    private String content;

    public NodeText(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public NodeText setContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public String toString() {
        return "NodeText{" +
                "content='" + content + '\'' +
                '}';
    }
}
