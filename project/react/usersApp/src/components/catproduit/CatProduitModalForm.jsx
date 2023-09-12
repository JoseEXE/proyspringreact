import { useContext } from "react";
import { CatProduitContext } from "../../context/catproduit/CatProduitContext";
import { CatProduitForm } from "./CatProduitForm";

export const CatProduitModalForm = () =>{

    const { selectRegistre, handerFormClose, optionForm } = useContext(CatProduitContext);

    console.log("selectRegistre.id: "+selectRegistre.id);
    return(<>
    <div className="abrir-modal animacion fadeIn">
        <div className="modal" style={ {display: 'block'} } tabIndex="-1">
            <div className="modal-dialog" role="document">
                <div className="modal-content">
                    <div className="modal-header">
                        <h3 className="modal-title">
                            { selectRegistre.id <= 0 ? 'Créer' : 'Modifier' } Catègorie Produit
                        </h3><hr/>
                    </div>
                    <div className="modal-body">

                    <CatProduitForm  selectRegistre={ selectRegistre } handerFormClose={ handerFormClose } optionForm={ optionForm }/>
                  
                        
      

                    </div>
                </div>
            </div>
        </div>
    </div>

    </>);

}