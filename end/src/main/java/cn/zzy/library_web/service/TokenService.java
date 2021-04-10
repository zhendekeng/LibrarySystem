package cn.zzy.library_web.service;

import cn.zzy.library_web.entity.User;

public interface TokenService {
    public String getToken(User user);
}
