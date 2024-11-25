import React, { useEffect, useState } from "react";
import axios from "axios";
import UpdateBookingForm from "./UpdateBookingForm";

const UserBookingsTable = () => {
  const [bookings, setBookings] = useState([]);
  const [editingBooking, setEditingBooking] = useState(null);

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
      fetchBookings();
    } catch (error) {
      console.error("Error deleting booking:", error);
    }
  };

  const handleEditClick = (booking) => {
    setEditingBooking(booking);
  };

  const handleUpdate = async (updatedBooking) => {
    try {
      await axios.put(
        `http://localhost:8080/bookings/update/${updatedBooking.id}`,
        updatedBooking
      );
      fetchBookings();
      setEditingBooking(null);
    } catch (error) {
      console.error("Error updating booking:", error);
    }
  };

  return (
    <div>
      <h3>Your Bookings</h3>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Customer Name</th>
            <th>Booking Date</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {bookings.map((booking) => (
            <tr key={booking.id}>
              <td>{booking.id}</td>
              <td>{booking.customerName}</td>
              <td>{booking.bookingDate}</td>
              <td>
                <button onClick={() => deleteBooking(booking.id)}>Delete</button>
                <button onClick={() => handleEditClick(booking)}>Update</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      {editingBooking && (
        <UpdateBookingForm
          booking={editingBooking}
          onUpdate={handleUpdate}
          onCancel={() => setEditingBooking(null)}
        />
      )}
    </div>
  );
};

export default UserBookingsTable;
