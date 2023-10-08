import { useContext } from "react";
import { AdresseContext } from "../../context/adresse/AdresseContext";
import { AdresseForm } from "./AdresseForm";

export const AdresseModalForm = () =>{

    const { selectRegistre, handerFormClose, optionForm } = useContext(AdresseContext);



    return(<>
    <div className="abrir-modal animacion fadeIn">
        <div className="modal" style={ {display: 'block'} } tabIndex="-1">
            <div className="modal-dialog" role="document">
                <div className="modal-content">
                    <div className="modal-header">
                        <h3 className="modal-title">
                            { selectRegistre.id <= 0 ? 'Créer' : 'Modifier' } Adresse Client
                        </h3><hr/>
                    </div>
                    <div className="modal-body">

                    <AdresseForm  selectRegistre={ selectRegistre } handerFormClose={ handerFormClose } optionForm={ optionForm }/>
                  
                        
      

                    </div>
                </div>
            </div>
        </div>
    </div>
    </>);
}