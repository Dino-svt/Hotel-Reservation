import React, { useEffect, useState } from 'react'
import axios from 'axios';
import ClientNav from '../../../Nav/ClientNav';

const ViewFoodPack = () => {
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

  return (
    <div>
        <ClientNav/>
    <section>
        
        <h1>Food Packages</h1>
       
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
                        
                    </tr>
                ))}
                
            </tbody>
        </table>
    </section>
    </div>
  )
}

export default ViewFoodPack
