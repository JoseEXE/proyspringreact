import { useContext } from "react";
import { UserForm } from "./UserForm";
import { UserContext } from "../../context/UserContext";

export const UserModalForm = () =>{
    const { selectUser, handerFormClose } = useContext(UserContext);
    return(<>
    <div className="abrir-modal animacion fadeIn">
        <div className="modal" style={ {display: 'block'} } tabIndex="-1">
            <div className="modal-dialog" role="document">
                <div className="modal-content">
                    <div className="modal-header">
                        <h5 className="modal-title">
                            { selectUser.id > 0 ? 'Créer' : 'modifier' } Modal Utilisateur
                        </h5>
                    </div>
                    <div className="modal-body">

                        <UserForm
                  
                        
                        selectUser={ selectUser } 
                        handerFormClose={ handerFormClose }
                        />

                    </div>
                </div>
            </div>
        </div>
    </div>
    </>);


};