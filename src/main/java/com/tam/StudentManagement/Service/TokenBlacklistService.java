package com.tam.StudentManagement.Service;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.tam.StudentManagement.Service.Interface.ITokenBlacklistService;

import java.util.Map;

@Service
public class TokenBlacklistService implements ITokenBlacklistService {
    private final Map<String, Long> blacklist = new ConcurrentHashMap<>();

    public void addToBlacklist(String token, long expirationTime) {
        blacklist.put(token, expirationTime);
    }

    public boolean isBlacklisted(String token) {
        return blacklist.containsKey(token);
    }

    public void removeExpiredTokens() {
        long now = System.currentTimeMillis();
        blacklist.entrySet().removeIf(entry -> entry.getValue() < now);
    }
}