package util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Олеся
 */
public class ReverseEnumMap<V extends Enum<V> & EnumConverter> {
    private Map<Integer, V> map = new HashMap<>();

    public ReverseEnumMap(Class<V> valueType) {
        for (V v : valueType.getEnumConstants()) {
            map.put(v.convert(), v);
        }
    }

    public V get(int num) {
        return map.get(num);
    }
}
