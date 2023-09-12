import { useContext } from "react";
import { useParams, NavLink } from "react-router-dom";
import { AuthContext } from "../auth/context/AuthContext";
import { UserContext } from "../context/UserContext";
import { useEffect, useState } from "react";
import { UserPassInfo } from "../components/userscomponents/pass/UserPassInfo";
import { UserPassPass } from "../components/userscomponents/pass/UserPassPass";
import Swal from "sweetalert2";

export const UserChangePassPage = () =>{
    const { login } = useContext(AuthContext);
    const { id } = useParams();

    console.log("My id Params: "+id);
    console.log("My id Sess: "+ login.user.id);

    const {
        selectMyUser,
        getMyUser,
    } = useContext(UserContext);


    useEffect(()=>{
        getMyUser(id);
    },[,id])
   




    return(<>
    <div className="container">


	

        <div className="row">
            <div className="col-lg-12 col-md-9">
                <h1 className="title"></h1>
            </div>
        </div>

        <div className="row">
            <div className="col-lg-12 col-md-12">
                <h2>Changement de mot de password</h2>
                <p></p>

            </div>
        </div>

            <div className='container border p-4'>

                <div className='row'>
                    <div className="col-lg-12">
                        <div className="form-heading">
                            <span className="prg"> </span>
                        </div>
                    </div>
                </div>
                <div className='row'>
                    { ( id == login.user.id && id == selectMyUser.id) ?
                    <>
                        <UserPassInfo selectMyUser = { selectMyUser } />
                        <UserPassPass selectMyUser = { selectMyUser } />    
                    
                    </> 
                    :
                    <>
                        <h3>Error de validation...</h3>
                        
                        <div class="alert alert-danger" role="alert">
                        <NavLink  className="nav-link" to="/users">Returner  </NavLink>
                        </div>
                    </>




                    }
                               
                
                

                    
                </div>
                
            </div>
 

    </div>
    </>);
};