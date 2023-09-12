export const CatProduitFormDetail = ({ useForm, handerFormClose, onCloseForm }) =>{


    const { id, nom, description, statut } = useForm;

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

 


          <div className="row justify-content-start mt-1">
            <div className="col">
              <div className="form-check">
              <input type="checkbox" name="statut"  className="form-check-input" checked={statut}  disabled />  {(statut == true) ? 'Catègorie Produit Active' : 'Catègorie Produit Inactif'}
            
              </div>

    
              <hr/>

        {!handerFormClose || <button className="btn btn-primary mx-2 btn-sm" type="button" onClick={ onCloseForm }><i className="bi bi-box-arrow-in-right"></i> &nbsp; Fermer</button>}
            </div>



          </div>
    </>);
}