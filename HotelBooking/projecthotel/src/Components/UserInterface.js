import React, { useState } from 'react';
import axios from 'axios';
import './UserInterface.css';

const UserInterface = () => {
    const [bookingDate, setBookingDate] = useState('');
    const [rooms, setRooms] = useState([]);
    const [error, setError] = useState(null);

    const fetchRoomsByDate = async () => {
        console.log('Fetching rooms for date:', bookingDate); // Debug log
        if (!bookingDate) {
            alert('Please select a booking date');
            return;
        }

        try {
            const response = await axios.get(`http://localhost:8080/rooms/viewroombydate/${bookingDate}`);
            console.log('Response:', response.data); // Debug log

            if (Array.isArray(response.data)) {
                if (response.data.length > 0) {
                    // Map the response to the expected format
                    const formattedRooms = response.data.map(room => ({
                        roomId: room.room_id,             // Map room_id to roomId
                        roomNumber: room.room_number,      // Map room_number to roomNumber
                        roomType: room.room_type,          // Map room_type to roomType
                        facilities: room.room_facilities    // Map room_facilities to facilities
                    }));
                    
                    setRooms(formattedRooms); // Set the formatted rooms
                    setError(null); // Clear any previous error
                } else {
                    setError(`No available rooms for ${bookingDate}`); // Set error message for empty array
                    setRooms([]); // Clear previous rooms
                }
            } else {
                // If response is not an array, set an error
                setError('Unexpected response format');
                setRooms([]);
            }
        } catch (error) {
            console.error('Error fetching rooms:', error); // Debug log
            const errorMessage = error.response?.data || 'An error occurred while fetching rooms';
            setError(errorMessage);
            setRooms([]); // Clear previous rooms
        }
    };

    // Placeholder for booking function
    const handleBooking = () => {
        // Add your booking logic here
        alert('Booking functionality not implemented yet!');
    };

    return (
        <div className="user-container">
            <h1>User Interface</h1>
            <div className="search-bar">
                <input 
                    type="date" 
                    onChange={(e) => setBookingDate(e.target.value)} 
                />
                <button onClick={fetchRoomsByDate}>Search Rooms</button>
            </div>

            {error && <div className="error-message">{error}</div>} {/* Show error if any */}

            {rooms.length > 0 && (
                <div>
                    <table className="room-table">
                        <thead>
                            <tr>
                                <th>Room Number</th>
                                <th>Room Type</th>
                                <th>Facilities</th>
                            </tr>
                        </thead>
                        <tbody>
                            {rooms.map(room => (
                                <tr key={room.roomId}>
                                    <td>{room.roomNumber}</td>
                                    <td>{room.roomType}</td>
                                    <td>{room.facilities}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                    {/* Booking Button */}
                    <div className="booking-container">
                        <button className="booking-button" onClick={handleBooking}>Book Hear</button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default UserInterface;
