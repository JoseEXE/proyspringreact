import { useContext } from "react";
import dayjs from "dayjs";
import { ProduitContext } from "../../context/produit/ProduitContext";

export const ProduitFormDetail = ({ useForm, handerFormClose, onCloseForm }) =>{
    const { registresCatProd, listTypePlat } = useContext(ProduitContext);
    const { id, catProduit, user, code, nom, description, prix, type_plat, createdOn, updatedOn, statut} = useForm;

    return(<>
    <div className="row">
          </div>
          <div className="row align-items-center">
            <div className="col mt-1">
              <label className="col-sm-6 control-label small">Nom: </label>
              <input type="text" className="form-control" placeholder="Nom" name="nom" value={nom} disabled />
            </div>
          </div>
          <div className="row align-items-center mt-1">
            <div className="col">
              <label className="col-sm-6 control-label small">Description: </label>
              <input type="text" className="form-control" placeholder="Description" name="description" value={description}  disabled />
            </div>
          </div>
          <div className="row align-items-center mt-1">
            <div className="col">
              <label className="col-sm-12 control-label small">Catégorie de produits: </label>
               
              <select className="form-select" aria-label="Default select example" name="catProduit"  disabled>    
              <option value={catProduit} selected>
                {
                  registresCatProd.map(registre =>{
                    if(registre.id == catProduit){
                      return registre?.nom ;
                    }
                  })
                }</option>
              </select> 



            </div>
            <div className="col">
              <label className="col-sm-12 control-label small">Type de plat: </label>
              <select className="form-select" aria-label="Default select example" name="type_plat" disabled >
               <option value={type_plat} selected>{type_plat}</option> 
              </select>
            </div>
          </div>

          <div className="row align-items-center mt-1">
            <div className="col">
              <label className="col-sm-6 control-label small">Date de création: </label>
              <input type="text" className="form-control" placeholder="Date de création" name="createdOn" value={dayjs(createdOn).format("DD/MM/YYYY HH:mm:ss A")  }  disabled />
            </div>
            <div className="col">
              <label className="col-sm-8 control-label small">Date de modification: </label>
              <input type="text" className="form-control" placeholder="Date de modification" name="updatedOn" value={dayjs(updatedOn).format("DD/MM/YYYY HH:mm:ss A") }  disabled  />
            </div>
          </div>

          <div className="row align-items-center mt-1">
            <div className="col">
              <label className="col-sm-6 control-label small">Code Produit: </label>
              <input type="text" className="form-control" placeholder="Code Produit" name="code" value={code}  disabled/>
            </div>
            <div className="col">
              <label className="col-sm-6 control-label small">Prix: </label>
              <input type="text" className="form-control" placeholder="Prix" name="prix" value={prix} disabled/>
            </div>
          </div>
       
 


          <div className="row justify-content-start mt-1">
            <div className="col">
              <div className="form-check">
              <input type="checkbox" name="statut"  className="form-check-input" checked={statut}  disabled />  {(statut == true) ? 'Produit Active' : 'Catègorie Produit Inactif'}
            
              </div>

    
              <hr/>

        {!handerFormClose || <button className="btn btn-primary mx-2 btn-sm" type="button" onClick={ onCloseForm }><i className="bi bi-box-arrow-in-right"></i> &nbsp; Fermer</button>}
            </div>



          </div>
    </>);
}