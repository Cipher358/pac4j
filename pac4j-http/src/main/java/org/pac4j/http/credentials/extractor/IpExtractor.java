/*
  Copyright 2012 - 2015 pac4j organization

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package org.pac4j.http.credentials.extractor;

import org.pac4j.core.context.WebContext;
import org.pac4j.http.credentials.TokenCredentials;

/**
 * To extract a remote IP address.
 *
 * @author Jerome Leleu
 * @since 1.8.0
 */
public class IpExtractor implements Extractor<TokenCredentials> {

    private final String clientName;

    private String alternateIpHeader;

    public IpExtractor(final String clientName) {
        this.clientName = clientName;
    }

    public TokenCredentials extract(WebContext context) {
        final String ip;
        if (alternateIpHeader == null) {
            ip = context.getRemoteAddr();
        } else {
            ip = context.getRequestHeader(alternateIpHeader);
        }

        if (ip == null) {
            return null;
        }

        return new TokenCredentials(ip, clientName);
    }

    public String getAlternateIpHeader() {
        return alternateIpHeader;
    }

    public void setAlternateIpHeader(final String alternateIpHeader) {
        this.alternateIpHeader = alternateIpHeader;
    }
}