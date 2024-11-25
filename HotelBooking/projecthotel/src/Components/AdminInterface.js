import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './AdminInterface.css';

const AdminInterface = () => {
    const [rooms, setRooms] = useState([]);
    const [roomDetails, setRoomDetails] = useState({
        room_id: '',
        room_number: '',
        room_type: '',
        room_facilities: '',
        status: '',
        booking_date:'',
    });
    const [deleteMessage, setDeleteMessage] = useState(''); //delete confirmation

    // Fetch rooms from the backend
    const fetchRooms = async () => {
        try {
            const response = await axios.get('http://localhost:8080/rooms/viewroom');
            console.log('Fetched rooms:', response.data);
            setRooms(response.data);
        } catch (error) {
            console.error('Error fetching rooms:', error);
        }
    };

    useEffect(() => {
        fetchRooms();
    }, []);

    // Handle input changes for room details
    const handleChange = (e) => {
        setRoomDetails({ ...roomDetails, [e.target.name]: e.target.value });
    };

    // Save or update room details
    const saveOrUpdateRoom = async () => {
        try {
            if (roomDetails.room_id) {
                await axios.put('http://localhost:8080/rooms/updateroom', roomDetails);
                console.log('Room updated:', roomDetails);
            } else {
                await axios.post('http://localhost:8080/rooms/saveroom', roomDetails);
                console.log('New room saved:', roomDetails);
            }
            clearForm();
            await fetchRooms();
        } catch (error) {
            console.error('Error saving/updating room:', error);
        }
    };

    // Clear form fields
    const clearForm = () => {
        setRoomDetails({ room_id: '', room_number: '', room_type: '', room_facilities: '', status: '' });
    };

    // Prepare room details for editing
    const editRoom = (room) => {
        setRoomDetails({
            room_id: room.room_id,
            room_number: room.room_number,
            room_type: room.room_type,
            room_facilities: room.room_facilities,
            status: room.status, 
        });
    };

    // Delete room
    const deleteRoom = async (roomId) => {
        try {
            await axios.delete(`http://localhost:8080/rooms/deleteroom/${roomId}`);
            console.log('Room deleted:', roomId);
            setDeleteMessage(`Room with ID ${roomId} has been deleted successfully.`); // Set delete message
            fetchRooms();
            setTimeout(() => setDeleteMessage(''), 5000); // Clear message after 5 seconds
        } catch (error) {
            console.error('Error deleting room:', error);
        }
    };

    return (
        <div className="admin-container">
            <h1>Admin Interface</h1>
            <div className="room-form">
                <input 
                    type="text" 
                    name="room_number" 
                    placeholder="Room Number" 
                    value={roomDetails.room_number}
                    onChange={handleChange} 
                />
                <input 
                    type="text" 
                    name="room_type" 
                    placeholder="Room Type" 
                    value={roomDetails.room_type}
                    onChange={handleChange} 
                />
                <input 
                    type="text" 
                    name="room_facilities" 
                    placeholder="Facilities" 
                    value={roomDetails.room_facilities}
                    onChange={handleChange} 
                />
                <input 
                type="text" 
                name="status" 
                placeholder="Room Status" 
                value={roomDetails.status} 
                onChange={handleChange} 
                />

                <button onClick={saveOrUpdateRoom}>
                    {roomDetails.room_id ? 'Update Room' : 'Save Room'}
                </button>
                {roomDetails.room_id && <button onClick={clearForm}>Cancel</button>}
            </div>

            {/* Display delete message if available */}
            {deleteMessage && <p className="delete-message">{deleteMessage}</p>}

            <h2>Room List</h2>
            {rooms.length > 0 ? (
                <table className="room-table">
                    <thead>
                        <tr>
                            <th>Room Number</th>
                            <th>Room Type</th>
                            <th>Facilities</th>
                            <th>Status</th>
                            <th>Booking Date</th>
                            <th>Actions</th>
                          
                        </tr>
                    </thead>
                    <tbody>
                        {rooms.map(room => (
                            <tr key={room.room_id}>
                                <td>{room.room_number}</td>
                                <td>{room.room_type}</td>
                                <td>{room.room_facilities}</td>
                                <td>{room.status}</td>
                                <td>{room.booking_date}</td>
                                <td>
                                    <button className="edit-btn" onClick={() => editRoom(room)}>Edit</button>
                                    <button className="delete-btn" onClick={() => deleteRoom(room.room_id)}>Delete</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            ) : (
                <p>No rooms available.</p>
            )}
        </div>
    );
};

export default AdminInterface;
