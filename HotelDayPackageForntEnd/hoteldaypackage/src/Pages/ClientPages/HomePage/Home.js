import React from 'react';
import './home.css';
import ClientNav from '../../../Nav/ClientNav';
import { Link } from 'react-router-dom';

const Home = () => {
  return (
    <div>
        <ClientNav/>
      <div className='section-top'>
        <div>
            <br/>
          <p className='cafe'>HReserV</p>
          <br /><br />
          <div>
            <button className='bot07'>LOG In</button>
            <Link to={"/register"}><button className='bot08'>REGISTER</button></Link>
          </div>
          <br /><br /><br />
          <br /><br /><br />
        </div>
      </div>
    </div>
  );
}

export default Home;
