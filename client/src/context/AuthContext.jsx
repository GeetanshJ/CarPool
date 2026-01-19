import React, { createContext, useState } from 'react';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [token, setToken] = useState(localStorage.getItem('token'));
    const [userId, setUserId] = useState(localStorage.getItem('userId'));

    const login = (jwtToken, id) => {
        setToken(jwtToken);
        setUserId(id);
        localStorage.setItem('token', jwtToken);
        localStorage.setItem('userId', id);
    };

    const logout = () => {
        setToken(null);
        setUserId(null);
        localStorage.clear();
        window.location.href = '/login';
    };

    return (
        <AuthContext.Provider value={{ token, userId, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};