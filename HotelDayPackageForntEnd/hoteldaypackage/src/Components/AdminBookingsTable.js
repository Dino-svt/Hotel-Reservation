import React from "react";

const AdminBookingsTable = ({ bookings, onDelete }) => {
  return (
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
              <button onClick={() => onDelete(booking.id)}>Delete</button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default AdminBookingsTable;
