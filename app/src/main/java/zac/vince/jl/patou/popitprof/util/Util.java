package zac.vince.jl.patou.popitprof.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by patrick on 3/23/18.
 */

public class Util {

    /**
     * Renvoi une map à partir d'un objet JSON
     *
     * @param json
     *            qui doit être JSON valide: {"bla":"bla"}
     * @return Map<String, String>
     * @throws JSONException
     */
    public static Map<String, String> jsonToMap(String json) throws JSONException {

        HashMap<String, String> map = new HashMap<String, String>();
        JSONObject jObject = new JSONObject(json);
        Iterator<?> keys = jObject.keys();

        while (keys.hasNext()) {
            String key = (String) keys.next();
            String value = jObject.getString(key);
            map.put(key, value);
        }
        return map;
    }
}
