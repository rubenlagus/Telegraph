package org.telegram.telegraph.jsonutilities;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import org.telegram.telegraph.api.objects.Node;
import org.telegram.telegraph.api.objects.NodeElement;
import org.telegram.telegraph.api.objects.NodeText;

import java.io.IOException;
import java.util.*;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class NodeDeserializer extends StdDeserializer<Node> {
    public NodeDeserializer() {
        super(Node.class);
    }

    @Override
    public Node deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode node = mapper.readTree(jsonParser);
        if (node instanceof TextNode) {
            return new NodeText(node.asText());
        } else {
            JsonNode childrenNode = node.get(NodeElement.CHILDREN_FIELD);
            JsonNode tagNode = node.get(NodeElement.TAG_FIELD);
            JsonNode attrsNode = node.get(NodeElement.ATTRS_FIELD);

            NodeElement element = new NodeElement();
            element.setTag(tagNode.asText());

            if (attrsNode != null && attrsNode instanceof ObjectNode) {
                Map<String, String> attributes = new HashMap<>();
                for (Iterator<String> it = attrsNode.fieldNames(); it.hasNext(); ) {
                    String field = it.next();
                    attributes.put(field, attrsNode.get(field).asText());
                }
                element.setAttrs(attributes);
            }

            if (childrenNode != null && childrenNode instanceof ArrayNode) {
                List<Node> childNodes = new ArrayList<>();
                for (Iterator<JsonNode> it = childrenNode.elements(); it.hasNext(); ) {
                    childNodes.add(mapper.treeToValue(it.next(), Node.class));
                }
                element.setChildren(childNodes);
            }

            return element;
        }
    }
}
