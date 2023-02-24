import java.util.Iterator;
import java.util.TreeMap;

public class ObjectNode extends Node implements Iterable<String>{
  private TreeMap<String, Node> nodes;

  public ObjectNode() {
    this.nodes = new TreeMap<String, Node>();
  }

  public Node get(String key) {
    return nodes.get(key);
  }

  public void set(String key, Node node) {
    nodes.put(key, node);
  }

  public int size() {
    return nodes.size();
  }

  public Iterator<String> iterator() {
    return nodes.keySet().iterator();
  }
}
