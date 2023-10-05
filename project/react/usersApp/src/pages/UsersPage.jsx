
import { UsersList } from "../components/userscomponents/UsersList";
import { useUsers } from "../hooks/useUsers";
import { UserModalForm } from "../components/userscomponents/UserModalForm";
import { useContext, useEffect } from "react";
import { UserContext } from "../context/UserContext";
import { AuthContext } from "../auth/context/AuthContext";
import { useParams } from "react-router-dom";
import { Paginator } from "../components/Paginator";

export const UsersPage = () =>{
    
    const { page } = useParams();

    console.log("page: "+page);
    const {
        users,
        paginator,
        visibleForm,
        handlerFormOpen,
        getUsers,
    } = useContext(UserContext);


    const { login } = useContext(AuthContext);

    useEffect(()=>{
        getUsers(page);
    },[,page])
   
    

    return(<>
    { !visibleForm || 
        <UserModalForm />
    }

    <div className="container my-4 border p-4">
        <h3>Hola Users</h3><hr/>
        <div className="d-grid gap-2 d-md-flex justify-content-md-end">

                { (visibleForm || !login.isAdmin ) || <button className="btn btn-primary btn-sm" onClick={ handlerFormOpen }><i className="bi bi-plus-circle"></i> Ajouter Utilisateur</button>}
            
            </div>
            <hr />
                {
                    users.length === 0 
                    ? <div className="alert alert-warning">Il n'y a pas d'utilisateurs sur la liste</div> 
                    : 
                    <>
                    <UsersList />
                    <Paginator url="/users/page" paginator={paginator} />
                    </>
                }

            

    </div>
    </>);
};