import { useContext } from "react";
import { ProduitContext } from "../../context/produit/ProduitContext";
import { ProduitForm } from "./ProduitForm";

export const ProduitModalForm = () =>{

    const { selectRegistre, handerFormClose, optionForm } = useContext(ProduitContext);



    return(<>
    <div className="abrir-modal animacion fadeIn">
        <div className="modal" style={ {display: 'block'} } tabIndex="-1">
            <div className="modal-dialog" role="document">
                <div className="modal-content">
                    <div className="modal-header">
                        <h3 className="modal-title">
                            { selectRegistre.id <= 0 ? 'Créer' : 'Modifier' } Produit
                        </h3><hr/>
                    </div>
                    <div className="modal-body">

                    <ProduitForm  selectRegistre={ selectRegistre } handerFormClose={ handerFormClose } optionForm={ optionForm }/>
                  
                        
      

                    </div>
                </div>
            </div>
        </div>
    </div>

    </>);
}