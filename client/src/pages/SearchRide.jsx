import React, { useState } from 'react';
import API from '../api/axiosConfig';

export default function SearchRide() {

    const [rides, setRides] = useState([]);
    const [q, setQ] = useState({ source: '', destination: '' });
    const [searched, setSearched] = useState(false);

    const token = localStorage.getItem('token');
    const userId = localStorage.getItem('userId');

    const handleSearch = async () => {

        if (!q.source || !q.destination) {
            alert("Please enter both Source and Destination");
            return;
        }



        setSearched(true);

        try {
            const res = await API.get(
                `/rides/search?source=${q.source}&destination=${q.destination}`,
                { headers: { Authorization: `Bearer ${token}` } }
            );
            setRides(res.data);
        } catch (err) {
            alert("Error fetching rides");
        }
    };

    const handleBook = async (rideId) => {

        const data = {
            bookingTime: new Date().toLocaleString(),
            ride: { id: rideId },
            user: { id: userId }
        };

        try {
            await API.post(
                '/bookings/book',
                data,
                { headers: { Authorization: `Bearer ${token}` } }
            );

            alert("Success! Ride Booked.");
            handleSearch();

        } catch {
            alert("No seats left!");
        }
    };

    return (
        <div className="container">

            <div
                className="card"
                style={{ display: 'flex', gap: '15px', alignItems: 'flex-end' }}
            >
                <div style={{ flex: 1 }}>
                    <label>From</label>
                    <input
                        placeholder="Source City"
                        value={q.source}
                        onChange={e =>
                            setQ({ ...q, source: e.target.value })
                        }
                    />
                </div>

                <div style={{ flex: 1 }}>
                    <label>To</label>
                    <input
                        placeholder="Destination City"
                        value={q.destination}
                        onChange={e =>
                            setQ({ ...q, destination: e.target.value })
                        }
                    />
                </div>

                <button
                    className="btn btn-p"
                    onClick={handleSearch}
                    style={{ width: 'auto' }}
                >
                    Find Rides
                </button>
            </div>

            {/* RESULTS */}
            <div className="grid">
                {searched ? (
                    rides.length > 0 ? (
                        rides.map(r => (
                            <div className="card" key={r.id}>
                                <div
                                    style={{
                                        display: 'flex',
                                        justifyContent: 'space-between'
                                    }}
                                >
                                    <h3>{r.source} ➔ {r.destination}</h3>
                                    <span
                                        style={{
                                            fontWeight: 800,
                                            fontSize: '1.2rem'
                                        }}
                                    >
                                        ₹{r.price}
                                    </span>
                                </div>

                                <div style={{ margin: '1rem 0', fontSize: '0.9rem' }}>
                                    <p>🕒 Departure: <b>{r.departure_time}</b></p>
                                    <p>🏁 Arrival: <b>{r.expected_arrival_time}</b></p>
                                    <p>💺 Available Seats: <b>{r.available_seats}</b></p>

                                    <hr style={{ margin: '0.6rem 0', opacity: 0.3 }} />

                                    <p>👤 Rider: <b>{r.rider.user.name}</b></p>
                                    <p>📞 Phone: <b>{r.rider.user.phone}</b></p>
                                    <p>🚗 Car: <b>{r.rider.carName}</b> ({r.rider.carColor})</p>
                                    <p>🔢 Car No: <b>{r.rider.carNumber}</b></p>
                                </div>


                                <button
                                    className="btn btn-p "
                                    onClick={() => handleBook(r.id)}
                                >
                                    Confirm Booking
                                </button>
                            </div>
                        ))
                    ) : (
                        <p
                            style={{
                                textAlign: 'center',
                                width: '100%',
                                marginTop: '2rem'
                            }}
                        >
                            No rides found. Try different locations.
                        </p>
                    )
                ) : null}
            </div>

        </div>
    );
}
