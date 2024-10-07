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
    <div>  
        <form onSubmit={saveFoodPack}>
        <h1> Food Package Registration</h1>
            <div>
                <label htmlFor='day'> Day </label>
                <input type='text' id='day' name='day' required value={day} onChange={handleInputChange}></input>
            </div>
            <div>
                <label htmlFor='packageName'> Package Name </label>
                <input type='text' id='packName' name='packageName' required value={packageName} onChange={handleInputChange}></input>
            </div>
            <div>
                <label htmlFor='ingredients'> Ingrediants</label>
                <input type='text' id='ingredients' name='ingredients' required value={ingredients} onChange={handleInputChange}></input>
            </div>

                <button type='submit'>save</button>
                <Link to={"/fpt"}><button>Cancel</button></Link>
      
        </form>
      
    </div>
  )
}

export default AddFoodPack
