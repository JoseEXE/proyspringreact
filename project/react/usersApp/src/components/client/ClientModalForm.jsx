import { useContext } from "react";

import { ClientForm } from "./ClientForm";
import { ClientContext } from "../../context/client/ClientContext";

export const ClientModalForm = () =>{
    const { selectRegistre, handerFormClose, optionForm } = useContext(ClientContext);

    return(<>
    <div className="abrir-modal animacion fadeIn">
        <div className="modal" style={ {display: 'block'} } tabIndex="-1">
            <div className="modal-dialog" role="document">
                <div className="modal-content">
                    <div className="modal-header">
                        <h3 className="modal-title">
                            { selectRegistre.id <= 0 ? 'Créer' : 'Modifier' } Client
                        </h3><hr/>
                    </div>
                    <div className="modal-body">

                    <ClientForm  selectRegistre={ selectRegistre }  optionForm={ optionForm }/>
                  
                        
      

                    </div>
                </div>
            </div>
        </div>
    </div>

    </>);
};