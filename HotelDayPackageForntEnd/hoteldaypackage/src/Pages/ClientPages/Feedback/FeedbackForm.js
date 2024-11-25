import React, { useState, useEffect } from "react";
import axios from "axios";
import "./FeedbackForm.css";
import ClientNav from "../../../Nav/ClientNav";

const FeedbackForm = () => {
    const [feedbacks, setFeedbacks] = useState([]);
    const [feedback, setFeedback] = useState({
        userNic: "",
        userName: "",
        userEmail: "",
        userNumber: "",
        comment: "",
        rating: 0,
    });
    const [searchNic, setSearchNic] = useState(""); // For searching by NIC
    const [isEditing, setIsEditing] = useState(false);
    const [editId, setEditId] = useState(null);
    const [message, setMessage] = useState(""); // For success or error messages

    // Fetch all feedbacks
    const fetchFeedbacks = async () => {
        try {
            const response = await axios.get("http://localhost:8080/api/feedback/all");
            setFeedbacks(response.data);
        } catch (error) {
            console.error("Error fetching feedbacks:", error);
        }
    };

    // Search feedback by NIC
    const handleSearch = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.get(`http://localhost:8080/api/feedback/user/nic/${searchNic}`);
            setFeedbacks(response.data);
        } catch (error) {
            console.error("Error searching feedback by NIC:", error);
            setFeedbacks([]);
        }
    };

    // Handle input change
    const handleChange = (e) => {
        const { name, value } = e.target;
        setFeedback({ ...feedback, [name]: value });
    };

    // Handle rating change
    const handleRatingChange = (ratingValue) => {
        setFeedback({ ...feedback, rating: ratingValue });
    };

    // Add or update feedback
    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            if (isEditing) {
                await axios.put(`http://localhost:8080/api/feedback/update/${editId}`, feedback);
                setMessage("Feedback updated successfully!");
                setIsEditing(false);
                setEditId(null);
            } else {
                await axios.post("http://localhost:8080/api/feedback/add", feedback);
                setMessage("Feedback submitted successfully!");
            }
            fetchFeedbacks();
            setFeedback({
                userNic: "",
                userName: "",
                userEmail: "",
                userNumber: "",
                comment: "",
                rating: 0,
            });
        } catch (error) {
            console.error("Error submitting feedback:", error);
            setMessage("Error submitting feedback. Please try again.");
        }
    };

    // Delete feedback
    const handleDelete = async (id) => {
        try {
            await axios.delete(`http://localhost:8080/api/feedback/delete/${id}`);
            fetchFeedbacks();
            setMessage("Feedback deleted successfully!");
        } catch (error) {
            console.error("Error deleting feedback:", error);
            setMessage("Error deleting feedback. Please try again.");
        }
    };

    // Edit feedback
    const handleEdit = (feedback) => {
        setIsEditing(true);
        setEditId(feedback.id);
        setFeedback(feedback);
    };

    useEffect(() => {
        fetchFeedbacks();
    }, []);

    return (
        <div>
            <ClientNav/>
        <div className="feedback-container">
            <h1>Feedback Management</h1>
            
            {/* Success or Error Message */}
            {message && <div className="message">{message}</div>}

            {/* Feedback Form */}
            <form onSubmit={handleSubmit} className="feedback-form">
                <input
                    type="text"
                    name="userNic"
                    placeholder="NIC"
                    value={feedback.userNic}
                    onChange={handleChange}
                    required
                />
                <input
                    type="text"
                    name="userName"
                    placeholder="Name"
                    value={feedback.userName}
                    onChange={handleChange}
                    required
                />
                <input
                    type="email"
                    name="userEmail"
                    placeholder="Email"
                    value={feedback.userEmail}
                    onChange={handleChange}
                    required
                />
                <input
                    type="text"
                    name="userNumber"
                    placeholder="Phone Number"
                    value={feedback.userNumber}
                    onChange={handleChange}
                    required
                />
                <input
                    name="comment"
                    placeholder="Comment"
                    value={feedback.comment}
                    onChange={handleChange}
                ></input>
                <label>Rating:</label>
                <div className="star-rating">
                    {[1, 2, 3, 4, 5].map((star) => (
                        <span
                            key={star}
                            className={`star ${feedback.rating >= star ? "selected" : ""}`}
                            onClick={() => handleRatingChange(star)}
                        >
                            &#9733;
                        </span>
                    ))}
                </div>
                <button type="submit">{isEditing ? "Update Feedback" : "Submit Feedback"}</button>
            </form>

            {/* Search Feedback by NIC */}
            <div className="search-bar">
                <form onSubmit={handleSearch}>
                    <input
                        type="text"
                        placeholder="Search by NIC"
                        value={searchNic}
                        onChange={(e) => setSearchNic(e.target.value)}
                    />
                    <button type="submit">Search</button>
                </form>
            </div>

            {/* Feedback List */}
            <div className="feedback-list">
                <h2>All Feedback</h2>
                {feedbacks.map((f) => (
                    <div key={f.id} className="feedback-item">
                        <p><strong>Name:</strong> {f.userName}</p>
                        <p><strong>Email:</strong> {f.userEmail}</p>
                        <p><strong>Comment:</strong> {f.comment}</p>
                        <p><strong>Rating:</strong> {f.rating}</p>
                        <button onClick={() => handleEdit(f)} className="update-btn">Edit</button>
                        <button onClick={() => handleDelete(f.id)} className="delete-btn">Delete</button>
                    </div>
                ))}
            </div>
        </div>
        </div>
    );
};

export default FeedbackForm;
