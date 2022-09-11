package org.apache.coyote.http11.handler;

import java.util.HashMap;
import java.util.Map;
import nextstep.jwp.controller.StaticResourceController;
import nextstep.jwp.controller.HomeController;
import nextstep.jwp.controller.LoginController;
import nextstep.jwp.controller.RegisterController;
import org.apache.coyote.http11.request.HttpRequest;

public class RequestMapping {

    private static final Map<String, Controller> values = new HashMap<>();

    static {
        values.put("/login", new LoginController());
        values.put("/register", new RegisterController());
        values.put("/", new HomeController());
    }

    public Controller getController(final HttpRequest request) {
        final String path = request.getHttpPath();
        final String pathWithOutExtension = path.substring(0, path.indexOf("."));
        if (values.containsKey(pathWithOutExtension)) {
            return values.get(pathWithOutExtension);
        }
        return new StaticResourceController();
    }
}
