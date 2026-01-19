import React, { useState, useContext } from 'react';
import API from '../api/axiosConfig.js';
import { AuthContext } from '../context/AuthContext';
import { useNavigate, Link } from 'react-router-dom';

export const Login = () => {
    const [creds, setCreds] = useState({ email: '', password: '' });
    const { login } = useContext(AuthContext);
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const res = await API.post('/auth/login', creds);
            login(res.data.token, res.data.userId);
            navigate('/search');
        } catch (err) {
            alert("Login Failed");
        }
    };


    return (
        <div className="auth-container">
            <div className="card">
                <h2>Login</h2>
                <form onSubmit={handleLogin}>
                    <input type="email" placeholder="Email" onChange={e => setCreds({ ...creds, email: e.target.value })} required />
                    <input type="password" placeholder="Password" onChange={e => setCreds({ ...creds, password: e.target.value })} required />
                    <button type="submit" className="btn-p">Login</button>
                </form>
                <p>Don't have an account? <Link to="/register">Register here</Link></p>
            </div>
        </div>
    );
};

export const Register = () => {
    const [user, setUser] = useState({ name: '', email: '', password: '', phone: '' });
    const navigate = useNavigate();

    const handleRegister = async (e) => {
        e.preventDefault();
        try {
            await API.post('/auth/register', user);
            alert("Registration Successful!");
            navigate('/login');
        } catch (err) {
            alert(err);
        }
    };

    return (
        <div className="auth-container">
            <div className="card">
                <h2>Register</h2>
                <form onSubmit={handleRegister}>
                    <input placeholder="Name" onChange={e => setUser({ ...user, name: e.target.value })} required />
                    <input type="email" placeholder="Email" onChange={e => setUser({ ...user, email: e.target.value })} required />
                    <input type="password" placeholder="Password" onChange={e => setUser({ ...user, password: e.target.value })} required />
                    <input placeholder="Phone" onChange={e => setUser({ ...user, phone: e.target.value })} required />
                    <button type="submit" className="btn-p">Register</button>
                </form>
                <p>Already have an account? <Link to="/login">Login here</Link></p>
            </div>
        </div>
    );
};