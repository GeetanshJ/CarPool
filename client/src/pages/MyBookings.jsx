import React, { useEffect, useState } from 'react';
import API from '../api/axiosConfig.js';

export default function MyBookings() {
    const [data, setData] = useState([]);
    const token = localStorage.getItem('token');
    const userId = localStorage.getItem('userId');

    useEffect(() => {
        API.get(`/bookings/history/${userId}`, { headers: { Authorization: `Bearer ${token}` } })
           .then(res => setData(res.data));
    }, [userId, token]);

    const cancel = async (id) => {
        if (!window.confirm("Cancel this booking?")) return;
        try {
            await API.post(`/bookings/cancel/${id}`, {}, { headers: { Authorization: `Bearer ${token}` } });
            alert("Booking Cancelled");
            window.location.reload();
        } catch { alert("Error cancelling"); }
    };

    return (
        <div className="container">
            <h2 style={{marginBottom: '1.5rem'}}>My Trips</h2>
            <div className="grid">
                {data.map(b => (
                    <div className="card" key={b.id} style={{borderLeft: `5px solid ${b.status === 'BOOKED' ? 'var(--success)' : 'var(--danger)'}`}}>
                        <div style={{display:'flex', justifyContent:'space-between', alignItems:'center'}}>
                            <h3>{b.ride.source} ➔ {b.ride.destination}</h3>
                            <span className={`badge ${b.status}`}>{b.status}</span>
                        </div>
                        <p style={{margin: '10px 0', fontSize: '0.85rem', color: 'var(--secondary)'}}>Booked on: {b.booking_time}</p>
                        {b.status === 'BOOKED' && <button className="btn btn-p" onClick={() => cancel(b.id)}>Cancel Trip</button>}
                    </div>
                ))}
            </div>
        </div>
    );
}