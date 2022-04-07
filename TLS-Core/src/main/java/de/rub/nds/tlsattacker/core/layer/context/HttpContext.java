/**
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.tlsattacker.core.layer.context;

import de.rub.nds.tlsattacker.core.state.Context;
import de.rub.nds.tlsattacker.transport.ConnectionEndType;

public class HttpContext extends LayerContext {

    private String lastRequestPath;

    /**
     * Add a cookie with this name to HTTP header if config.isAddHttpCookie is set.
     */
    private String httpCookieName = null;

    /**
     * Add a cookie with this value to HTTP header if config.isAddHttpCookie is set.
     */
    private String httpCookieValue = null;

    public HttpContext(Context context) {
        super(context);
        context.setHttpContext(this);
    }

    public String getLastRequestPath() {
        return lastRequestPath;
    }

    public void setLastRequestPath(String lastRequestPath) {
        this.lastRequestPath = lastRequestPath;
    }

    public String getHttpCookieName() {
        return httpCookieName;
    }

    public void setHttpCookieName(String httpCookieName) {
        this.httpCookieName = httpCookieName;
    }

    public String getHttpCookieValue() {
        return httpCookieValue;
    }

    public void setHttpCookieValue(String httpCookieValue) {
        this.httpCookieValue = httpCookieValue;
    }

}
