package util;

import com.google.gson.Gson;

import spark.ResponseTransformer;

// I dont like util calsses, but this seems to be the way to do it serialization with SPARK
public class JsonUtil<T> {

    final Class<T> typeOfT;

    public JsonUtil(Class<T> typeParameterClass) {
        this.typeOfT = typeParameterClass;
    }

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static ResponseTransformer json() {
        return JsonUtil::toJson;
    }

    public T fromJson(String jsonString) {
        Gson gson = new Gson();
        T response = gson.fromJson(jsonString, typeOfT);
        return response;
    }
}
