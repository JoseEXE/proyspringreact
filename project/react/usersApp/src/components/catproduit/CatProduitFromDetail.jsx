export const CatProduitFormDetail = ({ useForm, handerFormClose, onCloseForm }) =>{


    const { id, nom, description, statut } = useForm;

    return(<>
            <div className="row">
            </div>
            <div className="row align-items-center">
              <div className="col mt-1">
                <label className="col-sm-6 control-label small">rue: </label>
                <input type="text" className="form-control" placeholder="Nom" name="nom" value={rue} disabled />
              </div>
            </div>


            <div className="row align-items-center mt-1">
              <div className="col">
                <label className="col-sm-6 control-label small">Cod Postal: </label>
                <input type="text" className="form-control" placeholder="Code Produit" name="cod_postal" value={cod_postal}  disabled/>
              </div>
              <div className="col">
                <label className="col-sm-6 control-label small">Ville: </label>
                <input type="text" className="form-control" placeholder="Ville" name="ville" value={ville}  disabled />
              </div>
            </div>

            <div className="row align-items-center">
              <div className="col mt-1">
                <label className="col-sm-6 control-label small">Complement: </label>
                <input type="text" className="form-control" placeholder="Complement" name="complement" value={complement} disabled />
              </div>
            </div>

            <div className="row justify-content-start mt-1">
              <div className="col">
                <div className="form-check">
                <input type="checkbox" name="statut"  className="form-check-input" checked={statut}    disabled /> {(statut == true) ? 'Adresse Active' : 'Adresse Inactif'}
                </div>     
              </div>
            </div>

    
              <hr/>

        {!handerFormClose || <button className="btn btn-primary mx-2 btn-sm" type="button" onClick={ onCloseForm }><i className="bi bi-box-arrow-in-right"></i> &nbsp; Fermer</button>}

    </>);
}