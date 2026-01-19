import React, { useContext } from 'react';
import { BrowserRouter as Router, Routes, Route, Link, Navigate } from 'react-router-dom';
import { AuthProvider, AuthContext } from './context/AuthContext.jsx';
import { Login, Register } from './pages/Auth.jsx';
import SearchRide from './pages/SearchRide.jsx';
import CreateRide from './pages/CreateRide.jsx';
import MyBookings from './pages/MyBookings.jsx';
import BecomeRider from './pages/BecomeRider.jsx';
import './App.css';

const Navbar = () => {
    const { token, logout } = useContext(AuthContext);

    return (
        <nav>
            <Link to="/" className="logo">CARPOOL</Link>
            <div className="nav-links">
                {token ? (
                    <>
                        <Link to="/search">Find Ride</Link>
                        <Link to="/create-ride">Offer Ride</Link>
                        <Link to="/bookings">My Trips</Link>
                        <Link to="/become-rider">Register Car</Link>
                        <button onClick={logout} style={{color: 'var(--danger)'}}>Logout</button>
                    </>
                ) : (
                    <>
                        <Link to="/login">Login</Link>
                        <Link to="/register">Register</Link>
                    </>
                )}
            </div>
        </nav>
    );
};

const ProtectedRoute = ({ children }) => {
    const { token } = useContext(AuthContext);
    return token ? children : <Navigate to="/login" />;
};

function App() {
    return (
        <AuthProvider>
            <Router>
                <Navbar />
                <Routes>
                    <Route path="/login" element={<Login />} />
                    <Route path="/register" element={<Register />} />
                    
                    <Route path="/search" element={
                        <ProtectedRoute><SearchRide /></ProtectedRoute>
                    } />
                    <Route path="/create-ride" element={
                        <ProtectedRoute><CreateRide /></ProtectedRoute>
                    } />
                    <Route path="/bookings" element={
                        <ProtectedRoute><MyBookings /></ProtectedRoute>
                    } />
                    <Route path="/become-rider" element={
                        <ProtectedRoute><BecomeRider /></ProtectedRoute>
                    } />

                    <Route path="/" element={<Navigate to="/search" />} />
                </Routes>
            </Router>
        </AuthProvider>
    );
}

export default App;