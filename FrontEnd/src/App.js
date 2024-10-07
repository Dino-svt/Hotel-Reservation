import "/node_modules/bootstrap/dist/css/bootstrap.min.css";
import "/node_modules/bootstrap/dist/js/bootstrap.min.js";
import './App.css';
import { BrowserRouter as Router,Route,Routes } from 'react-router-dom';
import FoodPackTable from './Pages/AdminPages/FoodPackTable';
import AddFoodPack from './Pages/AdminPages/AddFoodPack';
import UpdateFoodPack from './Pages/AdminPages/UpdateFoodPack';

function App() {
  return (
    <div className="App">

     <Router>
         <Routes>
            <Route exact path='/fpt' element ={<FoodPackTable/>}></Route>
            <Route exact path='/afp' element ={<AddFoodPack/>}></Route>
            <Route exact path='/ufp/:day' element={<UpdateFoodPack/>}></Route>

         </Routes>
     </Router>
    </div>
  );
}

export default App;
