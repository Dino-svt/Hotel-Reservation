import React, { useEffect, useState } from 'react'
import axios from 'axios';
import {Link, useNavigate, useParams } from 'react-router-dom'


const UpdateFoodPack = () => {
    const {day} = useParams();
    let navigate =useNavigate();

    const [foodPack, setFoodPack] = useState({
        packageName: '',
        ingredients : '' 
    });
    
   
    

    const loadFoodPack = async () => {
        try{
            const response = await axios.get(`http://localhost:8080/fp/gfp/${day}`);
            console.log('food package loaded:',response.data);
            setFoodPack(response.data)
        }catch(error){
            console.error ("Error saving food package :",error);
            alert("Error loading food package.")
        }    
    };

    useEffect(()=>{
        if(day){
            loadFoodPack();
        }else{
            console.log('no day provided in the URL')
        }
    },[day])

    const handleInputChanges = (event) => {
        const {name, value} = event.target;
        setFoodPack({ ...foodPack, [name]: value });
    };

    const updateFoodPack = async (event) => {
        event.preventDefault();
        
        try{
            const response = await axios.put(`http://localhost:8080/fp/ufp/${day}`, foodPack);
            if(response.status===200){
                navigate("/fpt")
            }
        }catch (error) {
            console.error("Error update foodpackage:");
            alert("Failed to update food package. please try again.")
        }
    };

    const { packageName, ingredients} =foodPack;
   

  return (
    <div>  
        <form onSubmit={updateFoodPack}>
        <h1>Update Food Package </h1>
            <div>
                <label htmlFor='day'> Day </label>
                <input 
                type='text' 
                id='day' 
                name='day' 
                required
                value={day}
                readOnly>
                </input>
            </div>
            <div>
                <label htmlFor='package_name'> Package Name </label>
                <input type='text' 
                id='package_name' 
                name='packageName'
                value={packageName}
                onChange={handleInputChanges}>
                </input>
            </div>
            <div>
                <label htmlFor='ingredients'> Ingrediants</label>
                <input type='text' 
                id='ingredients'
                value={ingredients}
                onChange={handleInputChanges} 
                name='ingredients'>
                </input>
            </div>
            <button type='submit'>Save</button>
            <Link to='/fpt'><button>Cancel</button></Link>
            
        </form>
      
    </div>
  )
}

export default UpdateFoodPack
