import { useContext, useEffect, useState } from "react";
import { UserForm } from "../components/userscomponents/UserForm";
import { useParams } from "react-router-dom";
import { UserContext } from "../context/UserContext";

export const RegisterPage = () =>{
    const { users=[], userEstructure } = useContext(UserContext);
    const [selectUser, setSelectUser] = useState(userEstructure);
    const { id } = useParams();
    useEffect(() =>{
        console.log("id: "+id);
        if(id){
            const userx = users.find(u => u.id == id ) || userEstructure;
            setSelectUser(userx);
        }

    }, [id])
    return(<>
    <div className="container my-4">
	<h4>{ (selectUser.id > 0) ? 'Edition' : 'Enregistrement' } de l'utilisateur</h4>
	<div className="row">
		<div className="col"> 
            <UserForm  selectUser={ selectUser } />
		</div>
	</div>
</div>
    </>);
};

