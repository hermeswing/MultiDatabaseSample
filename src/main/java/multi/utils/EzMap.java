package multi.utils;

import java.util.LinkedHashMap;

public class EzMap extends LinkedHashMap<String, Object> {

    /**
     *
     */
    private static final long serialVersionUID = 1235619947606311249L;

    @Override
    public Object put(String key, Object value) {
        if (Utilities.isNotEmpty(key))
            key = Utilities.convert2CamelCase(key);
        return super.put(key, value);
    }

    public String getString(String key) {
        return Utilities.nullCheck(get(key));
    }

    public int getInt(String key) {
        return NumberUtils.parseInt(get(key));
    }

    public long getLong(String key) {
        return NumberUtils.parseLong(get(key));
    }

    public boolean getBoolean(String key) {
        return Utilities.parseBoolean(get(key));
    }

    public float getFloat(String key) {
        return NumberUtils.parseFloat(get(key));
    }

    public double getDouble(String key) {
        return NumberUtils.parseDouble(get(key));
    }

    public void setString(String key, Object value) {
        put(key, Utilities.nullCheck(value));
    }

    public void setInt(String key, Object value) {
        put(key, NumberUtils.parseInt(value));
    }

    public void setLong(String key, Object value) {
        put(key, NumberUtils.parseLong(value));
    }

    public void setBoolean(String key, Object value) {
        put(key, Utilities.parseBoolean(value));
    }

    public void setFloat(String key, Object value) {
        put(key, NumberUtils.parseFloat(value));
    }

    public void setDouble(String key, Object value) {
        put(key, NumberUtils.parseDouble(value));
    }


}
