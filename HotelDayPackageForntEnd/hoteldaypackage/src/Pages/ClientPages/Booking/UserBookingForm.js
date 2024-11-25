import React, { useState, useEffect } from "react";
import axios from "axios";

const UserBookingForm = () => {
  const [formData, setFormData] = useState({
    customerName: "",
    address: "",
    nic: "",
    bookingDate: "",
    roomId: "",
  });

  const [rooms, setRooms] = useState([]);

  useEffect(() => {
    fetchRooms();
  }, []);

  const fetchRooms = async () => {
    try {
      const response = await axios.get("http://localhost:8080/rooms/all");
      setRooms(response.data); // Assuming the backend returns a list of rooms
    } catch (error) {
      console.error("Error fetching rooms:", error);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const { roomId, ...bookingData } = formData;

    try {
      await axios.post(
        `http://localhost:8080/bookings/create?roomId=${roomId}`,
        bookingData
      );
      alert("Booking created successfully!");
    
      setFormData({
        customerName: "",
        address: "",
        nic: "",
        bookingDate: "",
        roomId: "",
      }); // Clear form
    } catch (error) {
      console.error("Error creating booking:", error);
      alert("Failed to create booking. Please check your input.");
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Room Booking Form</h2>
      <div>
        <label>Customer Name:</label>
        <input
          type="text"
          name="customerName"
          value={formData.customerName}
          onChange={handleChange}
          required
        />
      </div>
      <div>
        <label>Address:</label>
        <input
          type="text"
          name="address"
          value={formData.address}
          onChange={handleChange}
          required
        />
      </div>
      <div>
        <label>NIC:</label>
        <input
          type="text"
          name="nic"
          value={formData.nic}
          onChange={handleChange}
          required
        />
      </div>
      <div>
        <label>Booking Date:</label>
        <input
          type="date"
          name="bookingDate"
          value={formData.bookingDate}
          onChange={handleChange}
          required
        />
      </div>
      <div>
          <label>Room ID:</label>
          <input
            type="number"
            name="roomId"
            value={formData.roomId}
            onChange={handleChange}
            required
          />
        </div>
      <button type="submit">Submit Booking</button>
    </form>
  );
};

export default UserBookingForm;
