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
        <td><button type="button" className="btn btn-warning btn-sm" onClick={() => handlerSelectUser(user) }><i className="bi bi-pencil"></i> Modifier</button></td>

        <td><button type="button" className="btn btn-success btn-sm" onClick={() => handlerDelateUser(user.id) }><i className="bi bi-zoom-in"></i> Détail</button></td>
          </>  
        }

     </>
        
    

    );
};