import React from "react";
import UserBookingForm from "./UserBookingForm";
import UserBookingsTable from "./UserBookingsTable";

const UserPage = () => (
  <div>
    <h2>User - Bookings</h2>
    <UserBookingForm />
    <UserBookingsTable />
  </div>
);

export default UserPage;
