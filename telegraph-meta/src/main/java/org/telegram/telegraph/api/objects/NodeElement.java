package org.telegram.telegraph.api.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * This object represents a DOM element node.
 */
public class NodeElement extends Node {
    public static final String TAG_FIELD = "tag";
    public static final String ATTRS_FIELD = "attrs";
    public static final String CHILDREN_FIELD = "children";

    @JsonProperty(TAG_FIELD)
    private String tag; ///< Name of the DOM element. Available tags: a, aside, b, blockquote, br, code, em, figcaption, figure, h3, h4, hr, i, iframe, img, li, ol, p, pre, s, strong, u, ul, video.
    @JsonProperty(ATTRS_FIELD)
    private Map<String, String> attrs; ///< Attributes of the DOM element. Key of object represents name of attribute, value represents value of attribute. Available attributes: href, src.
    @JsonProperty(CHILDREN_FIELD)
    private List<Node> children; ///< List of child nodes for the DOM element.

    public NodeElement() {
    }

    public NodeElement(String tag, Map<String, String> attrs, List<Node> children) {
        this.tag = tag;
        this.attrs = attrs;
        this.children = children;
    }

    public String getTag() {
        return tag;
    }

    public NodeElement setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public Map<String, String> getAttrs() {
        return attrs;
    }

    public NodeElement setAttrs(Map<String, String> attrs) {
        this.attrs = attrs;
        return this;
    }

    public List<Node> getChildren() {
        return children;
    }

    public NodeElement setChildren(List<Node> children) {
        this.children = children;
        return this;
    }

    @Override
    public String toString() {
        return "NodeElement{" +
                "tag='" + tag + '\'' +
                ", attrs=" + attrs +
                ", children=" + children +
                '}';
    }
}
