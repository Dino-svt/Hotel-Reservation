import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';


const AddFoodPack = () => {
    const navigate = useNavigate();
    const [foodPack, setFoodPack] = useState({
        day: '',
        packageName: '',
        ingredients: '' 
    });

    const { day, packageName, ingredients } = foodPack;

    const handleInputChange = (e) => {
        setFoodPack({ ...foodPack, [e.target.name]: e.target.value });
    };

    const saveFoodPack = async (e) => {
        e.preventDefault(); 
        try{
            await axios.post("http://localhost:8080/fp/afp", foodPack);
            navigate("/fpt");
        }catch(error){
            console.error ("Error saving food package :",error);
            alert("This food package day already excist. Change the day and try again")
        }
           
    };

  return (
    <div className="container d-flex align-items-center justify-content-center" style={{ minHeight: '100vh' }}>
    <div className="col-sm-8 py-2 px-5 shadow-lg">
        <form onSubmit={saveFoodPack} className="form-inline">
            <h1 className="text-center">Food Package Registration</h1>
            
            <div className="form-group row mb-2">
                <label htmlFor='day' className="col-sm-2 col-form-label">Day</label>
                <div className="col-sm-10">
                    <select 
                    className="form-control" 
                    id='day' 
                    name='day' 
                    required 
                    value={day} 
                    onChange={handleInputChange}
                >
                    <option value={""}></option>    
                    <option value={"Sunday"}>Sunday</option>    
                    <option value={"Monday"}>Monday</option>
                    <option value={"Tuesday"}>Tuesday</option>
                    <option value={"Wednesday"}>Wednesday</option>
                    <option value={"Thursday"}>Thursday</option>
                    <option value={"Friday"}>Friday</option>
                    <option value={"Saturday"}>Saturday</option>
                    </select>
                </div>
            </div>
            <div className="form-group row mb-2">
                <label htmlFor='packageName' className="col-sm-2 col-form-label">Package_Name</label>
                <div className="col-sm-10">
                    <input 
                    type='text' 
                    className="form-control" 
                    id='packName' 
                    name='packageName' 
                    required 
                    value={packageName} 
                    onChange={handleInputChange}
                    ></input>
                </div>
            </div>
            <div className="form-group row mb-2">
                <label htmlFor='ingredients' className="col-sm-2 col-form-label">Ingredients</label>
                <div className="col-sm-10">
                    <input 
                    type='text' 
                    className="form-control" 
                    id='ingredients' 
                    name='ingredients' 
                    required 
                    value={ingredients} 
                    onChange={handleInputChange}
                    ></input>
                </div>
            </div>
            <button type='submit' className='btn btn-dark'>Save</button>
            <Link className='btn btn-dark mx-sm-3' to={"/fpt"}>Cancel</Link>
        </form>
    </div>
</div>

  )
}

export default AddFoodPack

