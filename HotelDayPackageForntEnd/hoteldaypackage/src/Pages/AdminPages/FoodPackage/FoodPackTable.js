import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';
import axios from 'axios';
import AdminNav from '../../../Nav/AdminNav';

const FoodPackTable = () => {
    const [FoodPack, setFoodPack] = useState ([]) ;

    useEffect(()=>{
        loadFoodPack();
    },[]);

    const loadFoodPack =async () => {
        try {
            const result = await axios.get("http://localhost:8080/fp/gfp");
            if (result.status === 200) {
                setFoodPack(result.data);
            }
        } catch (error) {
            console.error('Error fetching vaccines:', error);
        }

    };

    const handleDelete =async (day) => {
        await axios.delete(`http://localhost:8080/fp/dfp/${day}`);
        loadFoodPack();
    };

  return (
    <div>
        <AdminNav/>
    <section>
        <h1>Food Packages</h1>
        <Link className='btn btn-success mb-2' to={"/afp"}>AddItem</Link>

        <table className='table table-bordered table-hover shadow container'>
            <thead>
                <tr>
                    <th>day</th>
                    <th>Package Name</th>
                    <th>Ingrediants</th>
                </tr>
            </thead>

            <tbody>
                {FoodPack.map((foodPack)=>(
                    <tr key={foodPack.day}>
                        
                        <td>{foodPack.day}</td>
                        <td>{foodPack.packageName}</td>
                        <td>{foodPack.ingredients}</td>
                        <td><Link className='btn btn-primary' to={`/ufp/${foodPack.day}`}>Edit</Link></td>
                        <td><Link className='btn btn-danger' onClick={() => handleDelete(foodPack.day)}>Delete</Link></td>
                        
                    </tr>
                ))}
                
            </tbody>
        </table>
    </section>
    </div>
  )
}

export default FoodPackTable
