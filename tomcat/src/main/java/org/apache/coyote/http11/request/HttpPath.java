package org.apache.coyote.http11.request;

import java.util.HashMap;
import java.util.Map;

public class HttpPath {

    private static final String QUERY_PARAM_DELIMITER = "?";
    private static final String QUERY_PARAM_AND_DELIMITER = "&";
    private static final String QUERY_PARAM_VALUE_DELIMITER = "=";
    private static final int PARAM_NAME_INDEX = 0;
    private static final int PARAM_VALUE_INDEX = 1;
    private static final String DEFAULT_URL = "/";
    private static final String EXTENSION_DELIMITER = ".";
    private static final String DEFAULT_EXTENSION = ".html";

    private final String path;
    private final Map<String, String> params;

    public HttpPath(final String path, final Map<String, String> params) {
        this.path = path;
        this.params = params;
    }

    public static HttpPath from(final String uri) {
        final String path = parsePath(uri);
        final Map<String, String> params = parseParams(uri);
        return new HttpPath(path, params);
    }

    private static String parsePath(final String uri) {
        final String filePath = getFilePath(uri);
        if (isDefaultPath(filePath) || filePath.contains(EXTENSION_DELIMITER)) {
            return filePath;
        }
        return filePath.concat(DEFAULT_EXTENSION);
    }

    private static String getFilePath(final String uri) {
        if (uri.contains(QUERY_PARAM_DELIMITER)) {
            return uri.substring(0, uri.indexOf(QUERY_PARAM_DELIMITER));
        }
        return uri;
    }

    private static boolean isDefaultPath(final String filePath) {
        return filePath.equals(DEFAULT_URL);
    }

    private static Map<String, String> parseParams(final String uri) {
        final Map<String, String> params = new HashMap<>();
        if (uri.contains(QUERY_PARAM_DELIMITER)) {
            final String[] allParams = uri.substring(uri.indexOf(QUERY_PARAM_DELIMITER) + 1)
                    .split(QUERY_PARAM_AND_DELIMITER);
            addParams(params, allParams);
        }
        return params;
    }

    private static void addParams(final Map<String, String> params, final String[] allParams) {
        for (String paramNameAndValue : allParams) {
            final String[] param = paramNameAndValue.split(QUERY_PARAM_VALUE_DELIMITER);
            params.put(param[PARAM_NAME_INDEX], param[PARAM_VALUE_INDEX]);
        }
    }

    public String getPath() {
        return path;
    }

    public String getPathWithOutExtension() {
        if (path.contains(EXTENSION_DELIMITER)) {
            return path.substring(0, path.indexOf(EXTENSION_DELIMITER));
        }
        return path;
    }
}
