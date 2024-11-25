import React, { useEffect, useState } from "react";
import axios from "axios";
import AdminNav from "../../../Nav/AdminNav";

const AdminPage = () => {
  const [bookings, setBookings] = useState([]);

  useEffect(() => {
    fetchBookings();
  }, []);

  const fetchBookings = async () => {
    try {
      const response = await axios.get("http://localhost:8080/bookings/all");
      setBookings(response.data);
    } catch (error) {
      console.error("Error fetching bookings:", error);
    }
  };

  const deleteBooking = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/bookings/cancel/${id}`);
      fetchBookings(); // Refresh the list
    } catch (error) {
      console.error("Error deleting booking:", error);
    }
  };

  return (
    <div>
      <AdminNav/>
    <div>
      <h2>All Bookings</h2>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Customer Name</th>
            <th>Address</th>
            <th>NIC</th>
            <th>Booking Date</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {bookings.map((booking) => (
            <tr key={booking.id}>
              <td>{booking.id}</td>
              <td>{booking.customerName}</td>
              <td>{booking.address}</td>
              <td>{booking.nic}</td>
              <td>{booking.bookingDate}</td>
              <td>
                <button onClick={() => deleteBooking(booking.id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
    </div>
  );
};

export default AdminPage;
