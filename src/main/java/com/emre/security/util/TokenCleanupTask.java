package com.emre.security.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TokenCleanupTask {

    @Autowired
    private JwtUtil jwtUtil;

    // Scheduled task to run every minute to delete expired tokens from the blacklist
    @Scheduled(fixedRate = 60000 )
    public void cleanupTokens() {
        jwtUtil.deleteExpiredTokensFromBlacklist();
    }
}
