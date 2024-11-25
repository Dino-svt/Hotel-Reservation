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
    <div className="container d-flex align-items-center justify-content-center" style={{ minHeight: '100vh' }}>
    <div className="col-sm-8 py-2 px-5 shadow ">  
        <form onSubmit={updateFoodPack} className='form-inline'>
        <h1 className="text-center">Update Food Package </h1>
            <div className="form-group row mb-2">
                <label htmlFor='day' className="col-sm-2 col-form-label"> Day </label>
                <div className="col-sm-10">
                    <input type='text' className="form-control" id='day' name='day' required value={day} readOnly></input>
                </div>
            </div>
            <div className="form-group row mb-2">
                <label htmlFor='package_name' className="col-sm-2 col-form-label"> Package_Name </label>
                <div className="col-sm-10">
                <input type='text' className="form-control" id='package_name' name='packageName' value={packageName} onChange={handleInputChanges}></input>
                </div>
            </div>
            <div className="form-group row mb-2" >
                <label htmlFor='ingredients' className="col-sm-2 col-form-label"> Ingrediants</label>
                <div className="col-sm-10">
                <input type='text' className="form-control" id='ingredients' value={ingredients} onChange={handleInputChanges} name='ingredients'></input>
                </div>
            </div>
            <button className='btn btn-dark mx-sm-3' type='submit'>Save</button>
            <Link className='btn btn-dark mx-sm-3' to='/fpt'>Cancel</Link>
            
        </form>
      
    </div>
    </div>
  )
}

export default UpdateFoodPack
