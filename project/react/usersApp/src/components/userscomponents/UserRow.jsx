import { useContext } from "react";
import { NavLink } from "react-router-dom";
import { UserContext } from "../../context/UserContext";
import { AuthContext } from "../../auth/context/AuthContext";

export const UserRow = ({ user }) => {

    const { handlerDelateUser, handlerSelectUser } = useContext(UserContext);
    const { login } = useContext(AuthContext);

    return(
    <>           
        <td>{ user.id }</td>
        <td>{ user.nameU }</td>
        <td>{ user.lastName }</td>
        <td>{ user.email }</td>
        {!login.isAdmin ||<>
        <td><button type="button" className="btn btn-secondary btn-sm" onClick={() => handlerSelectUser(user) }>Actualiser</button></td>
        <td>
            <NavLink className={'btn btn-secondary btn-sm'} 
            to={'/users/edit/'+user.id}> Actualiser URL</NavLink> 
        </td>
        <td><button type="button" className="btn btn-danger btn-sm" onClick={() => handlerDelateUser(user.id) }>Supprimer</button></td>
          </>  
        }

     </>
        
    

    );
};