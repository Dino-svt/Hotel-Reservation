//bootstrap
import "/node_modules/bootstrap/dist/css/bootstrap.min.css";
import "/node_modules/bootstrap/dist/js/bootstrap.min.js";
import './App.css';
import { BrowserRouter as Router,Route,Routes } from 'react-router-dom';

import Home from "./Pages/ClientPages/HomePage/Home";
//Food package
import FoodPackTable from './Pages/AdminPages/FoodPackage/FoodPackTable';
import AddFoodPack from './Pages/AdminPages/FoodPackage/AddFoodPack';
import UpdateFoodPack from './Pages/AdminPages/FoodPackage/UpdateFoodPack';
import ViewFoodPack from "./Pages/ClientPages/FoodPackage/ViewFoodPack";
//Room avauilability
import AdminInterface from './Pages/AdminPages/RoomAvailability/AdminInterface';
import UserInterface from './Pages/ClientPages/RoomAvailability/UserInterface';
//Booking
import AdminBooKingPage from './Pages/AdminPages/Booking/AdminBookingPage'
import UserPage from './Pages/ClientPages/Booking/UserPage'
//Feedback
import FeedbackForm from './Pages/ClientPages/Feedback/FeedbackForm'
import FeedbackView from './Pages/AdminPages/Feedback/FeedbackView'

import AdminRegister from "./Pages/Registration/UserRegister";

function App() {
  return (
    <div className="App">

     <Router>
         <Routes>
            <Route exact path='/' element ={<Home/>}></Route>
            <Route exact path='/fpt' element ={<FoodPackTable/>}></Route>
            <Route exact path='/afp' element ={<AddFoodPack/>}></Route>
            <Route exact path='/vfp' element ={<ViewFoodPack/>}></Route>
            <Route exact path='/ufp/:day' element={<UpdateFoodPack/>}></Route>

            <Route path="/admin" element={<AdminInterface />} />
            <Route path="/user" element={<UserInterface />} />

            <Route path="/ab" element={<AdminBooKingPage/>} />
            <Route path="/ub" element={<UserPage/>} />

            <Route path="/feedbackuser" element={<FeedbackForm />} />
            <Route path="/feedbackadmin" element={<FeedbackView />} />
               
            <Route path="/register" element={<AdminRegister />} />

         </Routes>
     </Router>
    </div>
  );
}

export default App;
