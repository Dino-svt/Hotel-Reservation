import React, { useState, useEffect } from "react";
import axios from "axios";
import "./FeedbackView.css";
import AdminNav from "../../../Nav/AdminNav";

const FeedbackView = () => {
    const [feedbacks, setFeedbacks] = useState([]);

    const fetchAllFeedbacks = async () => {
        try {
            const response = await axios.get("http://localhost:8080/api/feedback/all");
            setFeedbacks(response.data);
        } catch (error) {
            console.error("Error fetching feedbacks:", error);
        }
    };

    useEffect(() => {
        fetchAllFeedbacks();
    }, []);

    return (
        <div>
            <AdminNav/>
        <div className="admin-feedback-container">
            <h2>Admin Feedback Dashboard</h2>
            <div className="feedback-list">
                {feedbacks.map((f) => (
                    <div key={f.id} className="feedback-item">
                        <p>
                            <strong>Name:</strong> {f.userName}
                        </p>
                        <p>
                            <strong>Email:</strong> {f.userEmail}
                        </p>
                        <p>
                             <strong>UserNumber:</strong> {f.userNumber}
                        </p>
                        <p>
                            <strong>Comment:</strong> {f.comment}
                        </p>
                        <p>
                            <strong>Rating:</strong> {f.rating}
                        </p>
                    </div>
                ))}
            </div>
        </div>
        </div>
    );
};

export default FeedbackView;
