import React from 'react'
import { Link} from 'react-router-dom';

const ClientNav = () => {
  return (
    <nav className="navbar navbar-expand-lg nav2 shadow">
        <div className="container-fluid">
            <a className="navbar-brand">
                HReserV
            </a>
            <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>

            <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                    <li className="nav-item">
                        <Link className="nav-link active" aria-current="page" to ={"/"}>HOME</Link>
                    </li>

                    <li className="nav-item">
                        <Link className="nav-link active" to ={"/user"}>BOOKING</Link>
                    </li>


                    <li className="nav-item">
                        <Link className="nav-link active" to={"/vfp"}>FOOD PACKAGES</Link>
                    </li>
                   
                    <li className="nav-item">
                        <Link className="nav-link active" to ={"/feedbackuser"}>FEEDBACK</Link>
                    </li>
                </ul>
                
                <form className="d-flex" role="search">
                
                    <Link to="/" className="btn btn-dark" type="submit">LOGOUT</Link>
                
                </form>
            </div> 
        </div>
    </nav> 
  )
}

export default ClientNav
