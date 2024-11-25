import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import AdminInterface from './Components/AdminInterface';
import UserInterface from './Components/UserInterface';

const App = () => {
    return (
        <Router>
            <Routes>
                {/* Default route: redirect to /admin or /user */}
                <Route path="/" element={<Navigate to="/admin" />} />
                <Route path="/admin" element={<AdminInterface />} />
                <Route path="/user" element={<UserInterface />} />
            </Routes>
        </Router>
    );
};

export default App;
