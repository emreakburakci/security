package com.emre.security.util;

import java.util.Set;

public interface TokenBlacklist {
    void addToBlacklist(String token);
    boolean isBlacklisted(String token);

    Set<String> getBlacklist();
}
