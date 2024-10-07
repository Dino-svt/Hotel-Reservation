import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';
import axios from 'axios';

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
    <section>
        <h1>Food Packages</h1>
        <Link to={"/afp"}><button>AddItem</button></Link>

        <table className='table table-bordered table-hover shadow'>
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
                        <Link to={`/ufp/${foodPack.day}`}><td>Edit</td></Link>
                        <Link onClick={() => handleDelete(foodPack.day)}><td>Delete</td></Link>
                        
                    </tr>
                ))}
                
            </tbody>
        </table>
    </section>
  )
}

export default FoodPackTable
