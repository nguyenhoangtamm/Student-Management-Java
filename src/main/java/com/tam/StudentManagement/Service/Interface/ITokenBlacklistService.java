package com.tam.StudentManagement.Service.Interface;

public interface ITokenBlacklistService {
    void addToBlacklist(String token, long expirationTime);

    boolean isBlacklisted(String token);

    void removeExpiredTokens();
}
