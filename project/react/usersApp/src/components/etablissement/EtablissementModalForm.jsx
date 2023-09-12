import { useContext } from "react";
import { EtablissementContext } from "../../context/etablissement/EtablissementContext";
import { EtablissementForm } from "./EtablissementForm";

export const EtablissementModalForm = () =>{

    const { selectEtablissement, handerFormClose, optionForm } = useContext(EtablissementContext);

    console.log("selectEtablissement.id: "+selectEtablissement.id);
    return(<>
    <div className="abrir-modal animacion fadeIn">
        <div className="modal" style={ {display: 'block'} } tabIndex="-1">
            <div className="modal-dialog" role="document">
                <div className="modal-content">
                    <div className="modal-header">
                        <h3 className="modal-title">
                            { selectEtablissement.id <= 0 ? 'Créer' : 'Modifier' } Etablissement
                        </h3><hr/>
                    </div>
                    <div className="modal-body">

                    <EtablissementForm  selectEtablissement={ selectEtablissement } handerFormClose={ handerFormClose } optionForm={ optionForm }/>
                  
                        
      

                    </div>
                </div>
            </div>
        </div>
    </div>

    </>);
}