import React, { useState } from 'react';
import API from '../api/axiosConfig.js';

export default function CreateRide() {

    const [form, setForm] = useState({
        source: '',
        destination: '',
        departure_time: '',
        expected_arrival_time: '',
        available_seats: '',
        price: ''
    });

    const token = localStorage.getItem('token');
    const userId = localStorage.getItem('userId');

    const submit = async (e) => {
        e.preventDefault();

        if (
            !form.source ||
            !form.destination ||
            !form.departure_time ||
            !form.expected_arrival_time ||
            !form.available_seats ||
            !form.price
        ) {
            alert("Please fill all fields");
            return;
        }

        const payload = {
            source: form.source,
            destination: form.destination,
            departure_time: form.departure_time,
            expected_arrival_time: form.expected_arrival_time,
            available_seats: Number(form.available_seats),
            price: Number(form.price),
            rider: { id: localStorage.getItem("riderId")}
        };

        try {
            await API.post('/rides/create', payload, {
                headers: { Authorization: `Bearer ${token}` }
            });

            alert("Ride published successfully!");

            setForm({
                source: '',
                destination: '',
                departure_time: '',
                expected_arrival_time: '',
                available_seats: '',
                price: ''
            });

        } catch (err) {
            console.log(payload);
            alert("Failed! Check if you are a registered rider.");
        }
    };

    return (
        <div className="container">
            <div className="card" style={{ maxWidth: '600px', margin: 'auto' }}>
                <h2 style={{ marginBottom: '1.5rem' }}>Offer a Ride</h2>

                <form onSubmit={submit}>
                    <div className="grid" style={{ gridTemplateColumns: '1fr 1fr' }}>
                        <div>
                            <label>Source</label>
                            <input
                                value={form.source}
                                onChange={e => setForm({ ...form, source: e.target.value })}
                                required
                            />
                        </div>

                        <div>
                            <label>Destination</label>
                            <input
                                value={form.destination}
                                onChange={e => setForm({ ...form, destination: e.target.value })}
                                required
                            />
                        </div>

                        <div>
                            <label>Departure Time</label>
                            <input
                                placeholder="10:00 AM"
                                value={form.departure_time}
                                onChange={e => setForm({ ...form, departure_time: e.target.value })}
                                required
                            />
                        </div>

                        <div>
                            <label>Arrival Time</label>
                            <input
                                placeholder="02:00 PM"
                                value={form.expected_arrival_time}
                                onChange={e => setForm({ ...form, expected_arrival_time: e.target.value })}
                                required
                            />
                        </div>

                        <div>
                            <label>Seats</label>
                            <input
                                type="number"
                                min="1"
                                value={form.available_seats}
                                onChange={e => setForm({ ...form, available_seats: e.target.value })}
                                required
                            />
                        </div>

                        <div>
                            <label>Price (₹)</label>
                            <input
                                type="number"
                                min="0"
                                value={form.price}
                                onChange={e => setForm({ ...form, price: e.target.value })}
                                required
                            />
                        </div>
                    </div>

                    <button className="btn btn-p" style={{ marginTop: '1.5rem' }}>
                        Publish Ride
                    </button>
                </form>
            </div>
        </div>
    );
}
