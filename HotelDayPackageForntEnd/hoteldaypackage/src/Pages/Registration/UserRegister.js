import React, { useEffect, useState } from "react";
import axios from "axios";
import './Register.css';
import ClientNav from "../../Nav/ClientNav";

const AdminRegister = () => {
  const [users, setUsers] = useState([]);
  const [userForm, setUserForm] = useState({
    userId: null,
    firstName: "",
    lastName: "",
    address1: "",
    address2: "",
    city: "",
    state: "",
    zipCode: "",
    phone: "",
    emailAddress: "",
    role: "USER", // Default role
  });

  const [isEditing, setIsEditing] = useState(false);
  const [formErrors, setFormErrors] = useState({});

  const API_BASE_URL = "http://localhost:8080/user"; // Base URL for all API calls

  // Fetch all users on page load
  useEffect(() => {
    fetchUsers();
  }, []);

  const fetchUsers = async () => {
    try {
      const response = await axios.get(`${API_BASE_URL}/getAll`); // Use '/getAll' to fetch all users
      setUsers(response.data);
    } catch (error) {
      console.error("Error fetching users:", error);
      alert("Could not fetch user data.");
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validateForm()) return;

    try {
      if (isEditing) {
        // Update an existing user
        await axios.put(`${API_BASE_URL}/${userForm.userId}`, userForm);
        alert("User updated successfully!");
      } else {
        // Create a new user
        await axios.post(`${API_BASE_URL}/postData`, userForm); // Corrected endpoint for POST
        alert("User created successfully!");
      }

      // Re-fetch users to update the table
      fetchUsers();
      resetForm();
    } catch (error) {
      console.error("Error saving user:", error);
      alert("Failed to save user.");
    }
  };

  const validateForm = () => {
    let errors = {};
    if (!userForm.firstName) errors.firstName = "First name is required";
    if (!userForm.lastName) errors.lastName = "Last name is required";
    if (!userForm.emailAddress || !/\S+@\S+\.\S+/.test(userForm.emailAddress)) {
      errors.emailAddress = "Valid email is required";
    }
    setFormErrors(errors);
    return Object.keys(errors).length === 0;
  };

  const handleDelete = async (userId) => {
    if (!window.confirm("Are you sure you want to delete this user?")) return;
    try {
      await axios.delete(`${API_BASE_URL}/${userId}`);
      alert("User deleted successfully!");
      fetchUsers(); // Re-fetch the user list after deletion
    } catch (error) {
      console.error("Error deleting user:", error);
      alert("Failed to delete user.");
    }
  };

  const handleEdit = (user) => {
    setUserForm(user);
    setIsEditing(true);
    setFormErrors({});
  };

  const resetForm = () => {
    setUserForm({
      userId: null,
      firstName: "",
      lastName: "",
      address1: "",
      address2: "",
      city: "",
      state: "",
      zipCode: "",
      phone: "",
      emailAddress: "",
      role: "USER",
    });
    setIsEditing(false);
    setFormErrors({});
  };

  return (
    <div>
      <ClientNav/>
    <div className="admin-register">
      <h2>{isEditing ? "Edit User" : "Add New User"}</h2>

      <form onSubmit={handleSubmit} className="user-form">
        <input
          type="text"
          placeholder="First Name"
          value={userForm.firstName}
          onChange={(e) => setUserForm({ ...userForm, firstName: e.target.value })}
        />
        {formErrors.firstName && <div className="error">{formErrors.firstName}</div>}

        <input
          type="text"
          placeholder="Last Name"
          value={userForm.lastName}
          onChange={(e) => setUserForm({ ...userForm, lastName: e.target.value })}
        />
        {formErrors.lastName && <div className="error">{formErrors.lastName}</div>}

        <input
          type="email"
          placeholder="Email Address"
          value={userForm.emailAddress}
          onChange={(e) => setUserForm({ ...userForm, emailAddress: e.target.value })}
        />
        {formErrors.emailAddress && <div className="error">{formErrors.emailAddress}</div>}

        <select
          value={userForm.role}
          onChange={(e) => setUserForm({ ...userForm, role: e.target.value })}
        >
          <option value="USER">User</option>
          <option value="ADMINISTRATOR">Administrator</option>
        </select>

        <button type="submit">{isEditing ? "Update User" : "Create User"}</button>
        {isEditing && <button type="button" onClick={resetForm}>Cancel</button>}
      </form>

      <h3>User List</h3>
      <table className="user-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Role</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {users.length > 0 ? (
            users.map((user) => (
              <tr key={user.userId}>
                <td>{user.userId}</td>
                <td>{`${user.firstName} ${user.lastName}`}</td>
                <td>{user.emailAddress}</td>
                <td>{user.role}</td>
                <td>
                    <button className="edit-button" onClick={() => handleEdit(user)}>Edit</button>
                    <button className="delete-button" onClick={() => handleDelete(user.userId)}>Delete</button>
                    </td>

              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="5">No users available.</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
    </div>
    

  );
};

export default AdminRegister;
