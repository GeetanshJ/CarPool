import React, { useState } from 'react';
import API from '../api/axiosConfig.js';

const BecomeRider = () => {
    const [rider, setRider] = useState({ carName: '', carColor: '', carNumber: '' });
    const token = localStorage.getItem('token');
    const userId = localStorage.getItem('userId');

    const handle = async () => {
        try {


            const res = await API.post('/riders/become', { ...rider, user: { id: userId } }, {
                headers: { Authorization: `Bearer ${token}` }
            });
            alert("Rider Registered");  
            localStorage.setItem("riderId", res.data.id);
        } catch (err) {
            alert("already registered or some error from our side");
        };
    }

    return (
        <div className="card">
            <h2>Become a Rider</h2>
            <input placeholder="carName" onChange={e => setRider({ ...rider, carName: e.target.value })} />
            <input placeholder="carColor" onChange={e => setRider({ ...rider, carColor: e.target.value })} />
            <input placeholder="carNumber" onChange={e => setRider({ ...rider, carNumber: e.target.value })} />
            <button className="btn-p" onClick={handle}>Submit</button>
        </div>
    );
};
export default BecomeRider;